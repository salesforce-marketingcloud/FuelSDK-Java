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
import com.exacttarget.fuelsdk.ETServiceResponse;
import com.exacttarget.fuelsdk.annotations.InternalRestField;
import com.exacttarget.fuelsdk.annotations.InternalRestType;
import com.exacttarget.fuelsdk.model.ETObject;
import com.google.gson.JsonObject;

public class ETCrudServiceImpl extends ETGetServiceImpl implements ETCrudService {
	
	private static Logger logger = Logger.getLogger(ETCrudServiceImpl.class);

	public <T extends ETObject> ETServiceResponse<T> post(ETClient client, T object) throws ETSdkException {
		logger.trace("post: " + object.toString());
		return updateETObject(client, object);
	}

	public <T extends ETObject> ETServiceResponse<T> patch(ETClient client, T object) throws ETSdkException {
		logger.trace("patch: " + object.toString());
		return updateETObject(client, object);
	}

	public <T extends ETObject> ETServiceResponse<T> delete(ETClient client, T object) throws ETSdkException {
		
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
		String id = getPrimaryKey(object, type);
		logger.info("delete - id: " + id);
		String path = buildPath(restPath, accessToken, id);
		logger.info("delete - path: " + path);
		String json = connection.delete(path);
		logger.info("delete - json: " + json);
		return createETObject(type, json, false);
	}


	protected <T extends ETObject> ETServiceResponse<T> updateETObject(ETClient client, T object) throws ETSdkException {
		@SuppressWarnings("unchecked")
		Class<T> type = (Class<T>) object.getClass();
		
		InternalRestType typeAnnotation = type.getAnnotation(InternalRestType.class);
		
		if(typeAnnotation == null) {
            throw new ETSdkException("The type specified does not wrap an internal ET APIObject.");
        }

		ETRestConnection connection = client.getRESTConnection();
		
		JsonObject jsonObject = createJsonObject(object, type);

		jsonObject.addProperty("favorite", Boolean.FALSE);
		
		String restPath = typeAnnotation.restPath();
		String accessToken = client.getAccessToken();
		String id = getPrimaryKey(object, type);
		
		String path = buildPath(restPath, accessToken, id);
		
		String json = connection.post(path, jsonObject);
		
		return createETObject(type, json, false);
	}

	protected <T extends ETObject> JsonObject createJsonObject(T object, Class<T> type) throws ETSdkException {
		
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
}
