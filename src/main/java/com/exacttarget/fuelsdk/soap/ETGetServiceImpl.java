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
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import com.exacttarget.fuelsdk.annotations.InternalField;
import org.apache.log4j.Logger;

import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETGetService;
import com.exacttarget.fuelsdk.ETObject;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.ETServiceResponse;
import com.exacttarget.fuelsdk.annotations.InternalType;
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

        ETServiceResponse<T> response = new ETServiceResponseImpl<T>();

        // XXX replace with getConstructor() call
        Constructor<T>[] constructors = (Constructor<T>[]) type.getConstructors();

        java.util.List<String> properties = new java.util.ArrayList<String>();
        for(Field f : type.getDeclaredFields())
        {
            InternalField fld = f.getAnnotation(InternalField.class);
            if(fld != null)
            {
                properties.add(fld.name());
            }
        }

        logger.debug("properties:");
        if (logger.isDebugEnabled()) {
            for (String property : properties) {
                logger.debug("  " + property);
            }
        }

        RetrieveRequest retrieveRequest = new RetrieveRequest();
        retrieveRequest.setObjectType(type.getAnnotation(InternalType.class).type().getSimpleName());
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
            T object;
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
