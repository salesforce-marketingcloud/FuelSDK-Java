//
// ETCrudServiceImpl.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// Author(s): Ian Murdock <imurdock@exacttarget.com>
//

package com.exacttarget.fuelsdk.soap;

import com.exacttarget.fuelsdk.model.converter.ObjectConverter;

import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETCrudService;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.ETServiceResponse;
import com.exacttarget.fuelsdk.annotations.InternalSoapType;
import com.exacttarget.fuelsdk.internal.APIObject;
import com.exacttarget.fuelsdk.internal.CreateOptions;
import com.exacttarget.fuelsdk.internal.CreateRequest;
import com.exacttarget.fuelsdk.internal.CreateResponse;
import com.exacttarget.fuelsdk.internal.DeleteOptions;
import com.exacttarget.fuelsdk.internal.DeleteRequest;
import com.exacttarget.fuelsdk.internal.DeleteResponse;
import com.exacttarget.fuelsdk.internal.Soap;
import com.exacttarget.fuelsdk.internal.UpdateOptions;
import com.exacttarget.fuelsdk.internal.UpdateRequest;
import com.exacttarget.fuelsdk.internal.UpdateResponse;
import com.exacttarget.fuelsdk.model.ETObject;

public abstract class ETCrudServiceImpl extends ETGetServiceImpl implements ETCrudService {
	protected <T extends ETObject> ETServiceResponse<T> post(ETClient client, T object) throws ETSdkException {
    	Soap soap = client.getSOAPConnection().getSoap();
    	
    	InternalSoapType typeAnnotation = object.getClass().getAnnotation(InternalSoapType.class);
        if(typeAnnotation == null) {
            throw new ETSdkException("The type specified does not wrap an internal ET APIObject.");
        }
        
        ETServiceResponse<T> response = new ETServiceResponseImpl<T>();
        
    	APIObject apiObject;
		try {
            apiObject = ObjectConverter.convertFromEtObject(object, typeAnnotation.type(), false);
		}
        catch(Exception e) {
            throw new ETSdkException("Error instantiating object", e);
		}
    	
    	CreateRequest createRequest = new CreateRequest();
		createRequest.setOptions(new CreateOptions());
		createRequest.getObjects().add(apiObject);

		CreateResponse createResponse = soap.create(createRequest);
		response.setRequestId(createResponse.getRequestID());
		response.setStatus(createResponse.getOverallStatus().equals("OK"));
		
		return response;
    }

	protected <T extends ETObject> ETServiceResponse<T> patch(ETClient client, T object) throws ETSdkException {
        
    	Soap soap = client.getSOAPConnection().getSoap();
    	
    	InternalSoapType typeAnnotation = object.getClass().getAnnotation(InternalSoapType.class);
        if(typeAnnotation == null) {
            throw new ETSdkException("The type specified does not wrap an internal ET APIObject.");
        }
        
        ETServiceResponse<T> response = new ETServiceResponseImpl<T>();
    	
        APIObject apiObject;
		try {
            apiObject = ObjectConverter.convertFromEtObject(object, typeAnnotation.type(), true);
		}
        catch(Exception e) {
            throw new ETSdkException("Error instantiating object", e);
		}
        
    	UpdateRequest updateRequest = new UpdateRequest();
		updateRequest.setOptions(new UpdateOptions());
		updateRequest.getObjects().add(apiObject);

		UpdateResponse updateResponse = soap.update(updateRequest);
		response.setRequestId(updateResponse.getRequestID());
		response.setStatus(updateResponse.getOverallStatus().equals("OK"));
		
		return response;
    }

	protected <T extends ETObject> ETServiceResponse<T> delete(ETClient client, T object) throws ETSdkException {
    	Soap soap = client.getSOAPConnection().getSoap();
    	
    	InternalSoapType typeAnnotation = object.getClass().getAnnotation(InternalSoapType.class);
        if(typeAnnotation == null) {
            throw new ETSdkException("The type specified does not wrap an internal ET APIObject.");
        }
        
        ETServiceResponse<T> response = new ETServiceResponseImpl<T>();
        
        APIObject apiObject;
		try {
            apiObject = ObjectConverter.convertFromEtObject(object, typeAnnotation.type(), false);
		}
        catch(Exception e) {
            throw new ETSdkException("Error instantiating object", e);
        }

        final DeleteRequest deleteRequest = new DeleteRequest();
		deleteRequest.setOptions(new DeleteOptions());
		deleteRequest.getObjects().add(apiObject);

		DeleteResponse deleteResponse = soap.delete(deleteRequest);
		response.setRequestId(deleteResponse.getRequestID());
		response.setStatus(deleteResponse.getOverallStatus().equals("OK"));
    	
		return response;
    }
}
