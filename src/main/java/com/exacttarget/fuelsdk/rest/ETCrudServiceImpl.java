//
// ETCrudServiceImpl.java -
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
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETCrudService;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.ETResponse;
import com.exacttarget.fuelsdk.annotations.InternalRestField;
import com.exacttarget.fuelsdk.annotations.InternalRestType;
import com.exacttarget.fuelsdk.model.ETObject;
import com.exacttarget.fuelsdk.soap.ETServiceResponseImpl;
import com.google.gson.JsonObject;

public class ETCrudServiceImpl extends ETGetServiceImpl implements ETCrudService {
	
	private static Logger logger = Logger.getLogger(ETCrudServiceImpl.class);

	public <T extends ETObject> ETResponse<T> post(ETClient client, T object) throws ETSdkException {
		logger.trace("post: " + object.toString());
		return updateETObject(client, object);
	}

	public <T extends ETObject> ETResponse<T> patch(ETClient client, T object) throws ETSdkException {
		logger.trace("patch: " + object.toString());
		return updateETObject(client, object);
	}

	public <T extends ETObject> ETResponse<T> delete(ETClient client, T object) throws ETSdkException {
		
		logger.trace("delete: " + object.toString());
		
		@SuppressWarnings("unchecked")
		Class<T> type = (Class<T>) object.getClass();
		
		InternalRestType typeAnnotation = type.getAnnotation(InternalRestType.class);
		
		if(typeAnnotation == null) {
            throw new ETSdkException("The type specified does not wrap an internal ET APIObject.");
        }

		ETRestConnection connection = client.getRESTConnection();
		
		String restPath = typeAnnotation.restPath();
		String accessToken = client.getAccessToken();
		String path = buildPath(restPath, accessToken, object, typeAnnotation);
		String json = connection.delete(path);
		
		ETResponse<T> response = new ETServiceResponseImpl<T>();
		response.setStatus( connection.getResponseCode() == 200 );
		
		return createResponseETObject(type, json, response);
	}

	protected <T extends ETObject> ETResponse<T> updateETObject(ETClient client, T object) throws ETSdkException {
		@SuppressWarnings("unchecked")
		Class<T> type = (Class<T>) object.getClass();
		
		InternalRestType typeAnnotation = type.getAnnotation(InternalRestType.class);
		
		if(typeAnnotation == null) {
            throw new ETSdkException("The type specified does not wrap an internal ET APIObject.");
        }

		ETRestConnection connection = client.getRESTConnection();
		
		JsonObject jsonObject = createRequest(object, type);
		
		logger.debug("REQUEST Json: " + jsonObject);
		
		String restPath = typeAnnotation.restPath();
		String accessToken = client.getAccessToken();
		
		String path = buildPath(restPath, accessToken, object, typeAnnotation);
		
		String json = connection.post(path, jsonObject);

		ETResponse<T> response = new ETServiceResponseImpl<T>();
		
		response.setStatus( connection.getResponseCode() == 200 );
		
		return createResponseETObject(type, json, response);
	}

	protected <T extends ETObject> JsonObject createRequest(T object, Class<T> type) throws ETSdkException {
		
		JsonObject jsonObject = new JsonObject();
		
		List<Field> fields = new ArrayList<Field>(Arrays.asList(type.getDeclaredFields()));
		
        if (null != type.getSuperclass()) {
        	fields.addAll(Arrays.asList(type.getSuperclass().getDeclaredFields()));
        }
		
		try {
		
			for(Field f : fields) {
				InternalRestField fld = f.getAnnotation(InternalRestField.class);
	            if(fld != null) {
	            	String propertyValue = BeanUtils.getProperty(object, f.getName());
	            	if( propertyValue != null )
	            		jsonObject.addProperty(fld.jsonKey(), propertyValue);
	            }
	        }
			
		} catch (NoSuchMethodException ex) {
			throw new ETSdkException("Error instantiating object", ex);
		} catch (IllegalAccessException ex) {
			throw new ETSdkException("Error instantiating object", ex);
		} catch (InvocationTargetException ex) {
			throw new ETSdkException("Error instantiating object", ex);
		}
		
		return jsonObject;
	}
	
	protected <T extends ETObject> String getPrimaryKey(T object, Class<T> type) throws ETSdkException {
		
		InternalRestType typeAnnotation = type.getAnnotation(InternalRestType.class);
	
		try {
			
			String primaryValue = BeanUtils.getProperty(object, typeAnnotation.primaryKey());
			return primaryValue;
			
		} catch (NoSuchMethodException ex) {
			throw new ETSdkException("Error instantiating object", ex);
		} catch (IllegalAccessException ex) {
			throw new ETSdkException("Error instantiating object", ex);
		} catch (InvocationTargetException ex) {
			throw new ETSdkException("Error instantiating object", ex);
		}
	}
	
	protected <T> String buildPath(String restPath, String accessToken, T etObject, InternalRestType typeAnnotation) throws ETSdkException
	{
		StringBuilder sb = new StringBuilder(restPath);
		
		for(String prop : typeAnnotation.urlProps()) {
			
			String value = "";
			
				try {
					value = BeanUtils.getProperty(etObject, prop);
				} catch (IllegalAccessException e) {
					throw new ETSdkException("Error instantiating object", e);
				} catch (InvocationTargetException e) {
					throw new ETSdkException("Error instantiating object", e);
				} catch (NoSuchMethodException e) {
					throw new ETSdkException("Error instantiating object", e);
				}
			
			replaceURLPropWithValue(sb, prop, value);
			
		}
		sb.append( "?access_token=" );
		sb.append( accessToken );
		
		return sb.toString();
	}

}
