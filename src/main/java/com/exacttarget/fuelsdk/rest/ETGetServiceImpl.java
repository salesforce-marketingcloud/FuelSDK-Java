package com.exacttarget.fuelsdk.rest;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETGetService;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.ETServiceResponse;
import com.exacttarget.fuelsdk.annotations.InternalRestField;
import com.exacttarget.fuelsdk.annotations.InternalRestType;
import com.exacttarget.fuelsdk.filter.ETFilter;
import com.exacttarget.fuelsdk.model.ETObject;
import com.exacttarget.fuelsdk.soap.ETServiceResponseImpl;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ETGetServiceImpl implements ETGetService {

	public <T extends ETObject> ETServiceResponse<T> get(ETClient client, Class<T> type) throws ETSdkException {
		return this.get(client, type, null);
	}

	
	public <T extends ETObject> ETServiceResponse<T> get(ETClient client, Class<T> type, ETFilter filter) throws ETSdkException {

		ETRestConnection connection = client.getRESTConnection();
		
		InternalRestType typeAnnotation = type.getAnnotation(InternalRestType.class);
		if(typeAnnotation == null) {
            throw new ETSdkException("The type specified does not wrap an internal ET APIObject.");
        }
		
		ETServiceResponse<T> response = new ETServiceResponseImpl<T>();
		
		String path = typeAnnotation.restPath();
		// TODO add in ETFilter parameters if not null
		// TODO add in accessToken a little smarter
		path += "?access_token=" + client.getAccessToken();
		String json = connection.get(typeAnnotation.restPath());
		
		JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(json).getAsJsonObject();
        
        String collectionKey = typeAnnotation.collectionKey();
        
        JsonArray items = jsonObject.get(collectionKey).getAsJsonArray();
		Iterator<JsonElement> iter = items.iterator();

		
		// TODO START - Move all of this to the ObjectConverter Class - maybe make a RestObjectConverter?  or just put it all in one?
		List<Field> fields = new ArrayList<Field>(Arrays.asList(type.getDeclaredFields()));
        if (null != type.getSuperclass()) {
        	fields.addAll(Arrays.asList(type.getSuperclass().getDeclaredFields()));
        }
		
		try {
			while (iter.hasNext()) {
				JsonObject item = iter.next().getAsJsonObject();
				T etObject = type.newInstance();

				for(Field f : fields) {
					InternalRestField fld = f.getAnnotation(InternalRestField.class);
		            if(fld != null) {
		                BeanUtils.setProperty(etObject, f.getName(), item.get(fld.jsonKey()).getAsString());
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
		// TODO END
		
		return response;
	}

}
