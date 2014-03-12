//
// This file is part of the Fuel Java SDK.
//
// Copyright (C) 2013, 2014 ExactTarget, Inc.
// All rights reserved.
//
// Permission is hereby granted, free of charge, to any person
// obtaining a copy of this software and associated documentation
// files (the "Software"), to deal in the Software without restriction,
// including without limitation the rights to use, copy, modify,
// merge, publish, distribute, sublicense, and/or sell copies
// of the Software, and to permit persons to whom the Software
// is furnished to do so, subject to the following conditions:
//
// The above copyright notice and this permission notice shall be
// included in all copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY
// KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
// WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
// PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS
// OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES
// OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT
// OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH
// THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
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
import com.exacttarget.fuelsdk.ETObject;
import com.exacttarget.fuelsdk.ETRestConnection;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.ETResponse;
import com.exacttarget.fuelsdk.annotations.InternalRestField;
import com.exacttarget.fuelsdk.annotations.InternalRestType;
import com.exacttarget.fuelsdk.soap.ETResponseImpl;
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

		ETResponse<T> response = new ETResponseImpl<T>();
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

		ETResponse<T> response = new ETResponseImpl<T>();

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
