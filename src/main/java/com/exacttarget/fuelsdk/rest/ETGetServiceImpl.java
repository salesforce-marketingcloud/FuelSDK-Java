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
import com.exacttarget.fuelsdk.ETServiceResponse;
import com.exacttarget.fuelsdk.annotations.InternalRestField;
import com.exacttarget.fuelsdk.annotations.InternalRestType;
import com.exacttarget.fuelsdk.filter.ETFilter;
import com.exacttarget.fuelsdk.filter.ETSimpleFilter;
import com.exacttarget.fuelsdk.model.ETObject;
import com.exacttarget.fuelsdk.soap.ETServiceResponseImpl;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class ETGetServiceImpl implements ETGetService 
{
	private static Logger logger = Logger.getLogger(ETGetServiceImpl.class);
	
	public <T extends ETObject> ETServiceResponse<T> get(ETClient client, Class<T> type) throws ETSdkException {
		return this.get(client, type, null);
	}
	
	public <T extends ETObject> ETServiceResponse<T> get(ETClient client, Class<T> type, ETFilter filter) throws ETSdkException {
		logger.trace("get ");
		ETRestConnection connection = client.getRESTConnection();
		
		InternalRestType typeAnnotation = type.getAnnotation(InternalRestType.class);
		
		if(typeAnnotation == null) {
            throw new ETSdkException("The type specified does not wrap an internal ET APIObject.");
        }
		
		String id = null;
		if( filter != null )
		{
			if( filter instanceof ETSimpleFilter )
			{
				if( "id".equals(((ETSimpleFilter)filter).getProperty()) )
				{
					id = ((ETSimpleFilter)filter).getValues().get(0);
				}
			}
		}
		
		String path = buildPath(typeAnnotation.restPath(), client.getAccessToken(), id);
		String json = connection.get(path);
        
		return createResponseETObject(type, json);
	}

	protected <T extends ETObject> ETServiceResponse<T> createResponseETObject(Class<T> type, String json)  throws ETSdkException {
		
		logger.debug("returned json" + json);
		JsonArray items;
		try {
			
			if( "\"\"".equals(json) )
			{
				ETServiceResponse<T> response = new ETServiceResponseImpl<T>();
				return response;
			}
			
			JsonParser jsonParser = new JsonParser();
			
			JsonElement jsonElement = null;
			
			if( json.startsWith("[") )
			{
				jsonElement = jsonParser.parse(json).getAsJsonArray();
				logger.debug(jsonElement);
			}
			else
			{
				jsonElement = jsonParser.parse(json).getAsJsonObject();
			}
			
			InternalRestType typeAnnotation = type.getAnnotation(InternalRestType.class);
			
			items = null;

			if( jsonElement.isJsonArray() )
				items = (JsonArray)jsonElement;
			else if( jsonElement.isJsonObject() )
			{
				String collectionKey = typeAnnotation.collectionKey();
				if(((JsonObject)jsonElement).get(collectionKey) == null )
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

		return createETObject(type, items);
	}

	private <T extends ETObject> ETServiceResponse<T> createETObject(Class<T> type, JsonArray items) 
			throws ETSdkException {
		
		if( items == null ) return null;
		
		List<Field> fields = new ArrayList<Field>(Arrays.asList(type.getDeclaredFields()));

		if (null != type.getSuperclass()) {
        	fields.addAll(Arrays.asList(type.getSuperclass().getDeclaredFields()));
        }
		
		ETServiceResponse<T> response = new ETServiceResponseImpl<T>();
		
		try {
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
	
	protected String buildPath(String restPath, String accessToken, String id) 
	{
		StringBuilder path = new StringBuilder(restPath);
		
		if( id != null && !"".equals(id) ) {
			path.append("/");
			path.append(id);
		}
		
		path.append( "?access_token=" );
		path.append( accessToken );
		
		return path.toString();
	}
	
}
