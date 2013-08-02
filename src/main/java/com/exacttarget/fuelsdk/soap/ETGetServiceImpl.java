//
// ETGetServiceImpl.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// Author(s): Ian Murdock <imurdock@exacttarget.com>
//

package com.exacttarget.fuelsdk.soap;

import java.lang.reflect.Constructor;
import java.util.*;

import org.apache.log4j.Logger;

import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETGetService;
import com.exacttarget.fuelsdk.ETObject;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.ETServiceResponse;
import com.exacttarget.fuelsdk.annotations.InternalType;
import com.exacttarget.fuelsdk.annotations.InternalTypeConstructor;
import com.exacttarget.fuelsdk.internal.APIObject;
import com.exacttarget.fuelsdk.internal.RetrieveRequest;
import com.exacttarget.fuelsdk.internal.RetrieveRequestMsg;
import com.exacttarget.fuelsdk.internal.RetrieveResponseMsg;
import com.exacttarget.fuelsdk.internal.Soap;

public class ETGetServiceImpl
    extends ETServiceImpl implements ETGetService
{
    private static Logger logger = Logger.getLogger(ETGetServiceImpl.class);

    public <T extends ETObject> ETServiceResponse<T> get(ETClient client, Class<T> type)
        throws ETSdkException
    {
        Soap soap = client.getSOAPConnection().getSoap();

        InternalType typeAnnotation = type.getAnnotation(InternalType.class);
        if(typeAnnotation == null) {
            throw new ETSdkException("The type specified does not wrap an internal ET APIObject.");
        }

        RetrieveRequest retrieveRequest = new RetrieveRequest();
        retrieveRequest.setObjectType(typeAnnotation.type().getSimpleName());
        retrieveRequest.getProperties().addAll(Arrays.asList(typeAnnotation.fields()));
//        if (filter != null) {
//            retrieveRequest.setFilter(filter);
//        }

        RetrieveRequestMsg retrieveRequestMsg = new RetrieveRequestMsg();
        retrieveRequestMsg.setRetrieveRequest(retrieveRequest);

        RetrieveResponseMsg retrieveResponseMsg = soap.retrieve(retrieveRequestMsg);

        ETServiceResponse<T> response = new ETServiceResponseImpl<T>();
        response.setRequestId(retrieveResponseMsg.getRequestID());

        Constructor internalConstructor = null;
        for(Constructor c : type.getConstructors()) {
            if(c.getAnnotation(InternalTypeConstructor.class) != null) {
                internalConstructor = c;
                break;
            }
        }
        if(internalConstructor == null) {
            throw new ETSdkException("The type specified does not provide an internal ET APIObject constructor.");
        }

        try {
            for (APIObject apiObject : retrieveResponseMsg.getResults()) {
                response.getResults().add((T) internalConstructor.newInstance(apiObject));
            }
        }
        catch (Exception ex) {
            throw new ETSdkException("Error instantiating object", ex);
        }

        return response;
    }
}
