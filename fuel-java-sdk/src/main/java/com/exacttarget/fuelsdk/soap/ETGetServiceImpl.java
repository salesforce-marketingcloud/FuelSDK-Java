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
import java.lang.reflect.InvocationTargetException;

import org.apache.log4j.Logger;

import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETGetService;
import com.exacttarget.fuelsdk.ETObject;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.ETServiceResponse;
import com.exacttarget.fuelsdk.internal.APIObject;
import com.exacttarget.fuelsdk.internal.RetrieveRequest;
import com.exacttarget.fuelsdk.internal.RetrieveRequestMsg;
import com.exacttarget.fuelsdk.internal.RetrieveResponseMsg;
import com.exacttarget.fuelsdk.internal.Soap;

public class ETGetServiceImpl<T extends ETObject>
    extends ETServiceImpl<T> implements ETGetService<T>
{
    private static Logger logger = Logger.getLogger(ETGetServiceImpl.class);

    public ETServiceResponse<T> get(ETClient client, Class<T> type)
        throws ETSdkException
    {
        Soap soap = client.getSOAPConnection().getSoap();

        ETServiceResponse<T> response = new ETServiceResponseImpl<T>();

        // XXX replace with getConstructor() call
        Constructor<T>[] constructors = null;
        constructors = (Constructor<T>[]) type.getConstructors();
        for (Constructor<T> constructor : constructors) {
            System.out.println(constructor);
        }

        String objectType = null;
        try {
            objectType = (String) type.getField("OBJECT_TYPE").get(null);
        } catch (IllegalAccessException ex) {
            throw new ETSdkException("error determining objectType", ex);
        } catch (IllegalArgumentException ex) {
            throw new ETSdkException("error determining objectType", ex);
        } catch (NoSuchFieldException ex) {
            throw new ETSdkException("error determining objectType", ex);
        } catch (SecurityException ex) {
            throw new ETSdkException("error determining objectType", ex);
        }

        logger.debug("objectType: " + objectType);

        String[] properties = null;
        try {
            properties = (String[]) type.getField("PROPERTIES").get(null);
        } catch (IllegalAccessException ex) {
            throw new ETSdkException("error determining properties", ex);
        } catch (IllegalArgumentException ex) {
            throw new ETSdkException("error determining properties", ex);
        } catch (NoSuchFieldException ex) {
            throw new ETSdkException("error determining properties", ex);
        } catch (SecurityException ex) {
            throw new ETSdkException("error determining objectType", ex);
        }

        logger.debug("properties:");
        if (logger.isDebugEnabled()) {
            for (String property : properties) {
                logger.debug("  " + property);
            }
        }

        RetrieveRequest retrieveRequest = new RetrieveRequest();
        retrieveRequest.setObjectType(objectType);
        for (String property : properties) {
            retrieveRequest.getProperties().add(property);
        }
//        if (filter != null) {
//            retrieveRequest.setFilter(filter);
//        }

        RetrieveRequestMsg retrieveRequestMsg = new RetrieveRequestMsg();
        retrieveRequestMsg.setRetrieveRequest(retrieveRequest);

        RetrieveResponseMsg retrieveResponseMsg = soap.retrieve(retrieveRequestMsg);

        response.setRequestId(retrieveResponseMsg.getRequestID());

        for (APIObject apiObject : retrieveResponseMsg.getResults()) {
            T object = null;
            try {
                object = constructors[0].newInstance(apiObject);
            } catch (IllegalAccessException ex) {
                throw new ETSdkException("error instantiating object", ex);
            } catch (IllegalArgumentException ex) {
                throw new ETSdkException("error instantiating object", ex);
            } catch (InstantiationException ex) {
                throw new ETSdkException("error instantiating object", ex);
            } catch (InvocationTargetException ex) {
                throw new ETSdkException("error instantiating object", ex);
            }
            response.getResults().add(object);
        }

        return response;
    }
}
