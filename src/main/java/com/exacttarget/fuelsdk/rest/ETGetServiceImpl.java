//
// ETGetServiceImpl.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// @COPYRIGHT@
//

package com.exacttarget.fuelsdk.rest;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETGetService;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.ETResponse;
import com.exacttarget.fuelsdk.annotations.InternalRestField;
import com.exacttarget.fuelsdk.annotations.InternalRestType;
import com.exacttarget.fuelsdk.filter.ETComplexFilter;
import com.exacttarget.fuelsdk.filter.ETFilter;
import com.exacttarget.fuelsdk.filter.ETSimpleFilter;
import com.exacttarget.fuelsdk.model.ETObject;
import com.exacttarget.fuelsdk.soap.ETServiceResponseImpl;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class ETGetServiceImpl implements ETGetService {
	
	private static Logger logger = Logger.getLogger(ETGetServiceImpl.class);
	
	private static List<Integer> successfulResponses = Arrays.asList(200,201,202);
	
	public <T extends ETObject> ETResponse<T> get(ETClient client, Class<T> type) throws ETSdkException {
		return this.get(client, type, null);
	}

	public <T extends ETObject> ETResponse<T> get(ETClient client, Class<T> type, ETFilter filter) throws ETSdkException 
	{
		logger.trace("get ");
		ETRestConnection connection = client.getRESTConnection();
		
		InternalRestType typeAnnotation = type.getAnnotation(InternalRestType.class);
		
		if(typeAnnotation == null) {
            throw new ETSdkException("The type specified does not wrap an internal ET APIObject.");
        }		
		
		String path = null;
		
		try {
			path = buildPath(typeAnnotation.restPath(), client.getAccessToken(), typeAnnotation, filter);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		ETResponse<T> response = new ETServiceResponseImpl<T>();
		
		String json = connection.get(path);
		
		response.setStatus(successfulResponses.contains(connection.getResponseCode()));
		
		if( !response.getStatus() )
			return getErrorResponse( json, response );
		
		return createResponseETObject(type, json, response);
	}

	protected <T extends ETObject> ETResponse<T> getErrorResponse( String json, ETResponse<T> response )
	{
		try 
		{
			if( json == null ) return response;
			
			JsonParser jsonParser = new JsonParser();
			JsonObject jObject = jsonParser.parse(json).getAsJsonObject();
			
			if( jObject.get("message") != null )
			{
				if( jObject.get("errorcode") == null )
				{
					response.setMessage(jObject.get("message").getAsString());
				}
				else
				{
					String message = jObject.get("message").getAsString();
					String errorcode = jObject.get("errorcode").getAsString();
					response.setMessage(message + " - ErrorCode: " + errorcode);
				}
			}
		} 
		catch (JsonSyntaxException e) 
		{
			logger.error("Error parsing Error Response: " + e);
		}
		
		return response;
	}
	
	protected <T extends ETObject> ETResponse<T> createResponseETObject(Class<T> type, String json, ETResponse<T> response)  throws ETSdkException 
	{
		logger.debug("Returned Raw Json:" + json);
		
		JsonArray items;
		try {
			
			if( "\"\"".equals(json) )
			{
				return response;
			}
			
			JsonParser jsonParser = new JsonParser();
			
			JsonElement jsonElement = null;

			InternalRestType typeAnnotation = type.getAnnotation(InternalRestType.class);
			
			items = null;
			
			if( json.startsWith("[") )
			{
				jsonElement = jsonParser.parse(json).getAsJsonArray();
				logger.debug(jsonElement);
				items = (JsonArray)jsonElement;
			}
			else
			{
				jsonElement = jsonParser.parse(json).getAsJsonObject();
				JsonObject jobject = (JsonObject)jsonElement;
				
				if( jobject.get("page") != null && jobject.get("pageSize") != null && jobject.get("count") != null )
				{
					int page = jobject.get("page").getAsInt();
					int pageSize = jobject.get("pageSize").getAsInt();
					int count = jobject.get("count").getAsInt();
					
					response.setMoreResults( count > page*pageSize );
					logger.debug("HAS MORE RESULTS: " + response.hasMoreResults());
				}
				
				String collectionKey = typeAnnotation.collectionKey();
				if(jobject.get(collectionKey) == null )
				{
					items = new JsonArray();
					items.add(jsonElement);
				}
				else
				{
					items = ((JsonObject)jsonElement).get(collectionKey).getAsJsonArray();
				}
			}
		} catch (JsonSyntaxException e) {
			throw new ETSdkException(e);
		}

		return createETObject(type, items, response);
	}

	private <T extends ETObject> ETResponse<T> createETObject(Class<T> type, JsonArray items, ETResponse<T> response) throws ETSdkException 
	{	
		if( items == null ) return null;
		
		List<Field> fields = new ArrayList<Field>(Arrays.asList(type.getDeclaredFields()));

		if (null != type.getSuperclass()) 
		{
        	fields.addAll(Arrays.asList(type.getSuperclass().getDeclaredFields()));
        }
		
		try 
		{
			Iterator<JsonElement> iter = items.iterator();

			while (iter.hasNext()) {
				JsonObject item = iter.next().getAsJsonObject();
				T etObject = type.newInstance();

				for(Field f : fields) {
					InternalRestField fld = f.getAnnotation(InternalRestField.class);
		            if(fld != null) {
		            	String jsonKey = fld.jsonKey();
		            	if( item.get(jsonKey) == null ) continue;
		            	String jsonValue = item.get(jsonKey).getAsString();
		            	String fieldName = f.getName();
		                BeanUtils.setProperty(etObject, fieldName, jsonValue);
		            }
		        }
				response.getResults().add(etObject);
			}
		} catch (InstantiationException ex) {
			throw new ETSdkException("Error instantiating object", ex);
		} catch (IllegalAccessException ex) {
			throw new ETSdkException("Error instantiating object", ex);
		} catch (InvocationTargetException ex) {
			throw new ETSdkException("Error instantiating object", ex);
		}
		
		return response;
	}
	
	protected String buildPath(String restPath, String accessToken, InternalRestType typeAnnotation, ETFilter filter) 
	{
		List<String> props = Arrays.asList(typeAnnotation.urlProps());
		List<String> params = Arrays.asList(typeAnnotation.urlParameters());
		
		StringBuilder path = new StringBuilder(restPath);
		path.append( "?" );
		
		if( filter != null )
		{
			if( filter instanceof ETSimpleFilter )
			{
				buildPathFromSimpletFilter(path, (ETSimpleFilter) filter, props, params);
			}
			else if( filter instanceof ETComplexFilter )
			{
				ETComplexFilter complexFilter = (ETComplexFilter) filter;
				List<ETFilter> filters = new ArrayList<ETFilter>();

				filters.add(complexFilter.getLeftOperand());
				filters.add(complexFilter.getRightOperand());
				
				if( !complexFilter.getAdditionalOperands().isEmpty() )
					filters.addAll(complexFilter.getAdditionalOperands());
				
				for( ETFilter f: filters)
				{
					if( f instanceof ETSimpleFilter )
					{
						buildPathFromSimpletFilter(path, (ETSimpleFilter) f, props, params);
					}
				}
			}
		}
		
		// Remove all remaining URL Props
		for(String prop : props)
			replaceURLPropWithValue(path, prop, "");
		
		path.append( "access_token=" );
		path.append( accessToken );
		
		logger.debug("PATH: " + path.toString());
		
		return path.toString();
	}
	
	protected void buildPathFromSimpletFilter( StringBuilder path, ETSimpleFilter simpleFilter, List<String> props, List<String> params )
	{
		if (props.contains(simpleFilter.getProperty()) && simpleFilter.getValues().size() > 0) 
		{
			replaceURLPropWithValue(path, simpleFilter.getProperty(), simpleFilter.getValues().get(0));
		}
		else if( params.contains(simpleFilter.getProperty()) )
		{
			path.append("$");
			path.append(simpleFilter.getProperty());
			path.append("=");
			path.append(simpleFilter.getValues().get(0));
			path.append("&");
		}
	}
	
	protected void replaceURLPropWithValue(StringBuilder sb, String prop, String value) 
	{
		if (sb.indexOf("{" + prop + "}") > -1) 
		{
			value = (value == null) ? "" : value;
			
			int startIndex = sb.indexOf("{" + prop + "}");
			int endIndex = startIndex + new String("{" + prop + "}").length();
			
			if( "".equals(value) ) startIndex -= 1;
			
			sb.replace(startIndex, endIndex, value);
		}
	}
}
