//
// ETCrudServiceImpl.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// @COPYRIGHT@
//

package com.exacttarget.fuelsdk.soap;

import java.util.Arrays;
import java.util.List;

import com.exacttarget.fuelsdk.model.converter.ObjectConverter;
import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETCrudService;
import com.exacttarget.fuelsdk.ETObject;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.ETResponse;
import com.exacttarget.fuelsdk.annotations.InternalSoapType;
import com.exacttarget.fuelsdk.internal.APIObject;
import com.exacttarget.fuelsdk.internal.CreateOptions;
import com.exacttarget.fuelsdk.internal.CreateRequest;
import com.exacttarget.fuelsdk.internal.CreateResponse;
import com.exacttarget.fuelsdk.internal.CreateResult;
import com.exacttarget.fuelsdk.internal.DeleteOptions;
import com.exacttarget.fuelsdk.internal.DeleteRequest;
import com.exacttarget.fuelsdk.internal.DeleteResponse;
import com.exacttarget.fuelsdk.internal.DeleteResult;
import com.exacttarget.fuelsdk.internal.Soap;
import com.exacttarget.fuelsdk.internal.UpdateOptions;
import com.exacttarget.fuelsdk.internal.UpdateRequest;
import com.exacttarget.fuelsdk.internal.UpdateResponse;
import com.exacttarget.fuelsdk.internal.UpdateResult;

public abstract class ETCrudServiceImpl<T extends ETObject> extends ETGetServiceImpl<T> implements ETCrudService {

	@SuppressWarnings("unchecked")
	protected ETResponse<T> post(ETClient client, T object) throws ETSdkException {
		return post(client, Arrays.asList(object));
	}
	
	@SuppressWarnings("unchecked")
	protected ETResponse<T> post(ETClient client, List<T> objects) throws ETSdkException {
	
		ETResponse<T> response = new ETServiceResponseImpl<T>();
		
		if (objects == null || objects.size() == 0) {
			return response;
		}
		
    	Soap soap = client.getSOAPConnection().getSoap();

    	InternalSoapType typeAnnotation = objects.get(0).getClass().getAnnotation(InternalSoapType.class);
        if(typeAnnotation == null) {
            throw new ETSdkException("The type specified does not wrap an internal ET APIObject.");
        }

    	CreateRequest createRequest = new CreateRequest();
		createRequest.setOptions(new CreateOptions());
        
        for(T object : objects) {
        
	    	APIObject apiObject;
			try {
	            apiObject = ObjectConverter.convertFromEtObject(object, typeAnnotation.type(), false);
	            createRequest.getObjects().add(apiObject);
			}
	        catch(Exception e) {
	            throw new ETSdkException("Error instantiating object", e);
			}
        }

		CreateResponse createResponse = soap.create(createRequest);
		response.setRequestId(createResponse.getRequestID());
		response.setStatus(createResponse.getOverallStatus().equals("OK"));
		StringBuffer message = new StringBuffer(createResponse.getOverallStatus());
		

		try {
            for (CreateResult createResult : createResponse.getResults()) {
                response.getResults().add((T) ObjectConverter.convertToEtObject(createResult.getObject(), objects.get(0).getClass(), false));
                message.append(", ");
                message.append(createResult.getStatusMessage());
            }
        }
        catch (Exception ex) {
            throw new ETSdkException("Error instantiating object", ex);
        }
		response.setMessage(message.toString());
		
		return response;
    }

	@SuppressWarnings("unchecked")
	protected ETResponse<T> patch(ETClient client, T object) throws ETSdkException {
		return patch(client, Arrays.asList(object));
	}
	
	protected ETResponse<T> patch(ETClient client, List<T> objects) throws ETSdkException {

		ETResponse<T> response = new ETServiceResponseImpl<T>();
		
		if (objects == null || objects.size() == 0) {
			return response;
		}
		
		
    	Soap soap = client.getSOAPConnection().getSoap();

    	InternalSoapType typeAnnotation = objects.get(0).getClass().getAnnotation(InternalSoapType.class);
        if(typeAnnotation == null) {
            throw new ETSdkException("The type specified does not wrap an internal ET APIObject.");
        }

        UpdateRequest updateRequest = new UpdateRequest();
		updateRequest.setOptions(new UpdateOptions());
		
        for (T object : objects) {
	        APIObject apiObject;
			try {
	            apiObject = ObjectConverter.convertFromEtObject(object, typeAnnotation.type(), true);
	            updateRequest.getObjects().add(apiObject);
			}
	        catch(Exception e) {
	            throw new ETSdkException("Error instantiating object", e);
			}
        }

        UpdateResponse updateResponse = soap.update(updateRequest);
		response.setRequestId(updateResponse.getRequestID());
		response.setStatus(updateResponse.getOverallStatus().equals("OK"));
		
		StringBuffer message = new StringBuffer(updateResponse.getOverallStatus());
		for (UpdateResult result : updateResponse.getResults()) {
			message.append(", ");
			message.append(result.getStatusMessage());
		}
		response.setMessage(message.toString());
		

		return response;
    }

	@SuppressWarnings("unchecked")
	protected ETResponse<T> delete(ETClient client, T object) throws ETSdkException {
		return delete(client, Arrays.asList(object));
	}
	
	protected ETResponse<T> delete(ETClient client, List<T> objects) throws ETSdkException {
    	
		ETResponse<T> response = new ETServiceResponseImpl<T>();
		
		if (objects == null || objects.size() == 0) {
			return response;
		}
		
		Soap soap = client.getSOAPConnection().getSoap();

    	InternalSoapType typeAnnotation = objects.get(0).getClass().getAnnotation(InternalSoapType.class);
        if(typeAnnotation == null) {
            throw new ETSdkException("The type specified does not wrap an internal ET APIObject.");
        }

        final DeleteRequest deleteRequest = new DeleteRequest();
		deleteRequest.setOptions(new DeleteOptions());
		
        for (T object : objects) {
	        APIObject apiObject;
			try {
	            apiObject = ObjectConverter.convertFromEtObject(object, typeAnnotation.type(), false);
	            deleteRequest.getObjects().add(apiObject);
			}
	        catch(Exception e) {
	            throw new ETSdkException("Error instantiating object", e);
	        }
        }

		DeleteResponse deleteResponse = soap.delete(deleteRequest);
		response.setRequestId(deleteResponse.getRequestID());
		response.setStatus(deleteResponse.getOverallStatus().equals("OK"));
		
		StringBuffer message = new StringBuffer(deleteResponse.getOverallStatus());
		for (DeleteResult result : deleteResponse.getResults()) {
			message.append(", ");
			message.append(result.getStatusMessage());
		}
		response.setMessage(message.toString());

		return response;
    }
}
