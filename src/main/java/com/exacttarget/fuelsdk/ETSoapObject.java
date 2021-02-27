//
// This file is part of the Fuel Java SDK.
//
// Copyright (c) 2013, 2014, 2015, ExactTarget, Inc.
// All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are permitted provided that the following conditions
// are met:
//
// * Redistributions of source code must retain the above copyright
// notice, this list of conditions and the following disclaimer.
//
// * Redistributions in binary form must reproduce the above copyright
// notice, this list of conditions and the following disclaimer in the
// documentation and/or other materials provided with the distribution.
//
// * Neither the name of ExactTarget, Inc. nor the names of its
// contributors may be used to endorse or promote products derived
// from this software without specific prior written permission.
//
// THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
// "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
// LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
// A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
// HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
// SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
// LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
// DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
// THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
// (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
// OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
//

package com.exacttarget.fuelsdk;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;

import com.exacttarget.fuelsdk.annotations.ExternalName;
import com.exacttarget.fuelsdk.annotations.InternalName;
import com.exacttarget.fuelsdk.annotations.InternalProperty;
import com.exacttarget.fuelsdk.annotations.SoapObject;
import com.exacttarget.fuelsdk.internal.APIObject;
import com.exacttarget.fuelsdk.internal.APIProperty;
import com.exacttarget.fuelsdk.internal.Attribute;
import com.exacttarget.fuelsdk.internal.ComplexFilterPart;
import com.exacttarget.fuelsdk.internal.CreateOptions;
import com.exacttarget.fuelsdk.internal.CreateRequest;
import com.exacttarget.fuelsdk.internal.CreateResponse;
import com.exacttarget.fuelsdk.internal.CreateResult;
import com.exacttarget.fuelsdk.internal.DataExtension;
import com.exacttarget.fuelsdk.internal.DataExtensionField;
import com.exacttarget.fuelsdk.internal.DataExtensionFieldType;
import com.exacttarget.fuelsdk.internal.DataExtensionObject;
import com.exacttarget.fuelsdk.internal.DataFolder;
import com.exacttarget.fuelsdk.internal.DeleteOptions;
import com.exacttarget.fuelsdk.internal.DeleteRequest;
import com.exacttarget.fuelsdk.internal.DeleteResponse;
import com.exacttarget.fuelsdk.internal.DeleteResult;
import com.exacttarget.fuelsdk.internal.FilterPart;
import com.exacttarget.fuelsdk.internal.Email;
import com.exacttarget.fuelsdk.internal.EmailType;
import com.exacttarget.fuelsdk.internal.ExtractDescription;
import com.exacttarget.fuelsdk.internal.ListClassificationEnum;
import com.exacttarget.fuelsdk.internal.ListTypeEnum;
import com.exacttarget.fuelsdk.internal.LogicalOperators;
import com.exacttarget.fuelsdk.internal.ObjectExtension;
import com.exacttarget.fuelsdk.internal.RetrieveRequest;
import com.exacttarget.fuelsdk.internal.RetrieveRequestMsg;
import com.exacttarget.fuelsdk.internal.RetrieveResponseMsg;
import com.exacttarget.fuelsdk.internal.SimpleFilterPart;
import com.exacttarget.fuelsdk.internal.SimpleOperators;
import com.exacttarget.fuelsdk.internal.Soap;
import com.exacttarget.fuelsdk.internal.Subscriber;
import com.exacttarget.fuelsdk.internal.SubscriberStatus;
import com.exacttarget.fuelsdk.internal.TriggeredSendDefinition;
import com.exacttarget.fuelsdk.internal.TriggeredSendStatusEnum;
import com.exacttarget.fuelsdk.internal.UpdateOptions;
import com.exacttarget.fuelsdk.internal.UpdateRequest;
import com.exacttarget.fuelsdk.internal.UpdateResponse;
import com.exacttarget.fuelsdk.internal.UpdateResult;

/**
 * An <code>ETSoapObject</code> represents an object
 * available via the SOAP API.
 */

public abstract class ETSoapObject extends ETApiObject {
    private static Logger logger = Logger.getLogger(ETSoapObject.class);

    /**
     * The page size
     */
    public final static int PAGE_SIZE = 2500;

    /** 
    * Class constructor, Initializes a new instance of the class.
    */
    public ETSoapObject() {
        registerConverters();
    }

    /**
     * 
     * @param <T>           The type which extends from ETSoapObject
     * @param client        The ETClient object
     * @param type          The class type to retrieve
     * @param page          The page number
     * @param pageSize      The page size
     * @param filter        The ETFilter object
     * @return              The ETResponse object of type T which extends from ETSoapObject
     * @throws ETSdkException 
     */
    public static <T extends ETSoapObject> ETResponse<T> retrieve(ETClient client,
                                                                  Class<T> type,
                                                                  Integer page,
                                                                  Integer pageSize,
                                                                  ETFilter filter)
        throws ETSdkException
    {
        if (page != null) {
            throw new ETSdkException("page argument not supported on this object type");
        }
        if (pageSize != null) {
            throw new ETSdkException("pageSize argument not supported on this object type");
        }

        if (filter.getOrderBy().size() != 0) {
            throw new ETSdkException("order by argument not supported on this object type");
        }

        return retrieve(client, null, filter, type);
    }

    /**
     * ETSoapObject has additional retrieve methods that take the
     * SOAP object name and request ID for continuing requests.
     * In most cases, the SOAP object name is the same as the
     * internal class name, but in a few cases, it is not (e.g.,
     * DataExtensionObjects require the name of
     * the data extension in brackets: DataExtensionObject[foo]).
     * @param <T>           The type which extends from ETSoapObject
     * @param client        The ETClient object
     * @param soapObjectName The object name to retrieve for SOAP
     * @param filter        The ETFilter object
     * @param type          The class type to retrieve
     * @return              The ETResponse object of type T which extends from ETSoapObject
     * @throws ETSdkException 
     */
    public static <T extends ETSoapObject> ETResponse<T> retrieve(ETClient client,
                                                                     String soapObjectName,
                                                                     ETFilter filter,
                                                                     Class<T> type)
        throws ETSdkException
    {
        return retrieve(client, soapObjectName, filter, null, type);
    }

    /**
     * 
     * @param <T>           The type which extends from ETSoapObject
     * @param client        The ETClient object
     * @param type          The class type to retrieve
     * @param continueRequest The continue request
     * @return              The ETResponse object of type T which extends from ETSoapObject
     * @throws ETSdkException 
     */
    protected static <T extends ETSoapObject> ETResponse<T> retrieve(ETClient client,
                                                                     String continueRequest,
                                                                     Class<T> type)
        throws ETSdkException
    {
        return retrieve(client, null, new ETFilter(), continueRequest, type);
    }

    /**
     * 
     * @param <T>           The type which extends from ETSoapObject
     * @param client        The ETClient object
     * @param soapObjectName The object name to retrieve for SOAP
     * @param filter        The ETFilter object
     * @param continueRequest The continue request
     * @param type          The class type to retrieve
     * @return              The ETResponse object of type T which extends from ETSoapObject
     * @throws ETSdkException 
     */
    protected static <T extends ETSoapObject> ETResponse<T> retrieve(ETClient client,
                                                                     String soapObjectName,
                                                                     ETFilter filter,
                                                                     String continueRequest,
                                                                     Class<T> type)
        throws ETSdkException
    {
        ETResponse<T> response = new ETResponse<T>();

        //
        // Get handle to the SOAP connection:
        //

        ETSoapConnection connection = client.getSoapConnection();

        //
        // Automatically refresh the token if necessary:
        //

        client.refreshToken();

        //
        // Read internal type from the SoapObject annotation:
        //

        Class<T> externalType = type; // for code readability

        SoapObject internalTypeAnnotation
            = externalType.getAnnotation(SoapObject.class);
        assert internalTypeAnnotation != null;
        Class<? extends APIObject> internalType = internalTypeAnnotation.internalType();
        assert internalType != null;

        ETExpression expression = filter.getExpression();

        //
        // Determine properties to retrieve:
        //

        List<String> externalProperties = filter.getProperties();
        List<String> internalProperties = null;

        if (externalProperties.size() > 0) {
            //
            // Only request those properties specified:
            //

            internalProperties = new ArrayList<String>();

            for (String externalProperty : externalProperties) {
                String internalProperty =
                        getInternalProperty(externalType, externalProperty);
                assert internalProperty != null;
                internalProperties.add(internalProperty);
            }
        } else {
            //
            // No properties were explicitly requested:
            //

            internalProperties = getInternalProperties(externalType);

            //
            // Remove properties that are unretrievable:
            //

            for (String property : internalTypeAnnotation.unretrievable()) {
                internalProperties.remove(property);
            }
        }

        //
        // Perform the SOAP retrieve:
        //

        //Soap soap = connection.getSoap();
        Soap soap = null;

        RetrieveRequest retrieveRequest = new RetrieveRequest();

        if (continueRequest == null) {
            // if soapObjectType is specified, use it; otherwise, default
            // to the name of the internal class representing the object:
            if (soapObjectName != null) {
                retrieveRequest.setObjectType(soapObjectName);
                soap = connection.getSoap("retrieve", soapObjectName);
            } else {
                retrieveRequest.setObjectType(internalType.getSimpleName());
                soap = connection.getSoap("retrieve", internalType.getSimpleName());
            }
            retrieveRequest.getProperties().addAll(internalProperties);

            if (expression.getOperator() != null) {
                //
                // Convert the property names to their internal counterparts:
                //

                String property = expression.getProperty();
                if (property != null) {
                    expression.setProperty(getInternalProperty(type, property));
                }
                for (ETExpression subexpression : expression.getSubexpressions()) {
                    String p = subexpression.getProperty();
                    if (p != null) {
                        subexpression.setProperty(getInternalProperty(type, p));
                    }
                }

                retrieveRequest.setFilter(toFilterPart(expression));
            }
        } else {
            if (continueRequest != null) {
                retrieveRequest.setContinueRequest(continueRequest);
            }
        }

        if (logger.isTraceEnabled()) {
            logger.trace("RetrieveRequest:");
            logger.trace("  objectType = " + retrieveRequest.getObjectType());
            StringBuilder line = new StringBuilder("  properties = { ");
            //String line = null;
            for (String property : retrieveRequest.getProperties()) {

            	 line.append(property).append(", ");
            	             }
            	            if (retrieveRequest.getProperties().size() > 0) {
            	                line.setLength(line.length() - 2);	 
                /*if (line == null) {
                    line = "  properties = { " + property;
                } else {
                    line += ", " + property;
                }*/
            }
            line.append(" }");
            logger.trace(line.toString());	            
            //logger.trace(line + " }");
            if (filter != null) {
                logger.trace("  filter = " + toFilterPart(expression));
            }
        }

        logger.trace("calling soap.retrieve...");

        RetrieveRequestMsg retrieveRequestMsg = new RetrieveRequestMsg();
        retrieveRequestMsg.setRetrieveRequest(retrieveRequest);

        RetrieveResponseMsg retrieveResponseMsg = soap.retrieve(retrieveRequestMsg);

        if (logger.isTraceEnabled()) {
            logger.trace("RetrieveResponseMsg:");
            logger.trace("  requestId = " + retrieveResponseMsg.getRequestID());
            logger.trace("  overallStatus = " + retrieveResponseMsg.getOverallStatus());
            logger.trace("  results = {");
            for (APIObject result : retrieveResponseMsg.getResults()) {
                logger.trace("    " + result);
            }
            logger.trace("  }");
        }

        response.setRequestId(retrieveResponseMsg.getRequestID());
        if (retrieveResponseMsg.getOverallStatus().equals("OK")) {
            response.setStatus(ETResult.Status.OK);
        } else if (retrieveResponseMsg.getOverallStatus().equals("Error")) {
            response.setStatus(ETResult.Status.ERROR);
        }
        response.setResponseCode(retrieveResponseMsg.getOverallStatus());
        response.setResponseMessage(retrieveResponseMsg.getOverallStatus());
        for (APIObject internalObject : retrieveResponseMsg.getResults()) {
            //
            // Allocate a new (external) object:
            //

            T externalObject = null;
            try {
                externalObject = externalType.newInstance();
            } catch (Exception ex) {
                throw new ETSdkException("could not instantiate "
                        + externalType.getName(), ex);
            }

            externalObject.setClient(client);

            //
            // Convert from internal representation:
            //

            externalObject.fromInternal(internalObject);

            //
            // Add result to the list of results:
            //

            ETResult<T> result = new ETResult<T>();
            result.setObject(externalObject);
            response.addResult(result);
        }

        if (retrieveResponseMsg.getOverallStatus().equals("MoreDataAvailable")) {
            response.setMoreResults(true);
        }

        return response;
    }

    /**
     * @param <T>                   The type which extends from ETSoapObject
     * @param client                The ETClient object
     * @param objects               The List of objects to create
     * @return                      The ETResponse object of type T which extends from ETSoapObject
     * @throws ETSdkException 
     */
    public static <T extends ETSoapObject> ETResponse<T> create(ETClient client,
                                                                List<T> objects)
        throws ETSdkException
    {
        ETResponse<T> response = new ETResponse<T>();

        if (objects == null || objects.size() == 0) {
            response.setStatus(ETResult.Status.OK);
            return response;
        }

        //
        // Get handle to the SOAP connection:
        //

        ETSoapConnection connection = client.getSoapConnection();

        //
        // Automatically refresh the token if necessary:
        //

        client.refreshToken();

        //
        // Perform the SOAP create:
        //

//        Soap soap = connection.getSoap();
//        Soap soap = connection.getSoap("create");
        String obj = "";
        
        CreateRequest createRequest = new CreateRequest();
        createRequest.setOptions(new CreateOptions());
        for (T object : objects) {
            object.setClient(client);
            createRequest.getObjects().add(object.toInternal());
            obj += object.getClass().getSimpleName().substring(2);
        }
        Soap soap = connection.getSoap("create", obj);

        if (logger.isTraceEnabled()) {
            logger.trace("CreateRequest:");
            logger.trace("  objects = {");
            for (APIObject object : createRequest.getObjects()) {
                logger.trace("    " + object);
            }
            logger.trace("  }");
        }

        logger.trace("calling soap.create...");

        CreateResponse createResponse = soap.create(createRequest);

        if (logger.isTraceEnabled()) {
            logger.trace("CreateResponse:");
            logger.trace("  requestId = " + createResponse.getRequestID());
            logger.trace("  overallStatus = " + createResponse.getOverallStatus());
            logger.trace("  results = {");
            for (CreateResult result : createResponse.getResults()) {
                logger.trace("    " + result);
            }
            logger.trace("  }");
        }

        response.setRequestId(createResponse.getRequestID());
        if (createResponse.getOverallStatus().equals("OK")) {
            response.setStatus(ETResult.Status.OK);
        } else if (createResponse.getOverallStatus().equals("Error")) {
            response.setStatus(ETResult.Status.ERROR);
        }
        response.setResponseCode(createResponse.getOverallStatus());
        response.setResponseMessage(createResponse.getOverallStatus());
        for (CreateResult createResult : createResponse.getResults()) {
            //
            // Allocate a new (external) object:
            //

            @SuppressWarnings("unchecked")
            Class<T> externalType = (Class<T>) objects.get(0).getClass();

            T externalObject = null;
            try {
                externalObject = externalType.newInstance();
            } catch (Exception ex) {
                throw new ETSdkException("could not instantiate "
                        + externalType.getName(), ex);
            }

            externalObject.setClient(client);

            //
            // Convert from internal representation:
            //

            // not all SOAP calls return the object though some do..
            APIObject internalObject = createResult.getObject();
            if (internalObject != null) {
                externalObject.fromInternal(createResult.getObject());
            } else {
                // XXX populate fields from the object passed to the call?
                externalObject.setId(Integer.toString(createResult.getNewID()));
            }

            //
            // Add result to the list of results:
            //

            ETResult<T> result = new ETResult<T>();
            if (createResult.getStatusCode().equals("OK")) {
                result.setStatus(ETResult.Status.OK);
            } else if (createResult.getStatusCode().equals("Error")) {
                result.setStatus(ETResult.Status.ERROR);
            }
            result.setResponseCode(createResult.getStatusCode());
            result.setResponseMessage(createResult.getStatusMessage());
            result.setErrorCode(createResult.getErrorCode());
            if (result.getResponseCode().equals("OK")) { // XXX?
                result.setObject(externalObject);
            }
            response.addResult(result);
        }

        return response;
    }

    /**
     * @param <T>                   The type which extends from ETSoapObject
     * @param client                The ETClient object
     * @param objects               The List of objects to update
     * @return                      The ETResponse object of type T which extends from ETSoapObject
     * @throws ETSdkException 
     */
    public static <T extends ETSoapObject> ETResponse<T> update(ETClient client,
                                                                List<T> objects)
        throws ETSdkException
    {
        ETResponse<T> response = new ETResponse<T>();

        if (objects == null || objects.size() == 0) {
            response.setStatus(ETResult.Status.OK);
            return response;
        }

        //
        // Get handle to the SOAP connection:
        //

        ETSoapConnection connection = client.getSoapConnection();

        //
        // Automatically refresh the token if necessary:
        //

        client.refreshToken();

        //
        // Perform the SOAP update:
        //

//        Soap soap = connection.getSoap();
//        Soap soap = connection.getSoap("update");
        String obj = "";

        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.setOptions(new UpdateOptions());
        for (T object : objects) {
            object.setClient(client);
            updateRequest.getObjects().add(object.toInternal());
            obj += object.getClass().getSimpleName().substring(2);
        }
        Soap soap = connection.getSoap("update", obj);

        if (logger.isTraceEnabled()) {
            logger.trace("UpdateRequest:");
            logger.trace("  objects = {");
            for (APIObject object : updateRequest.getObjects()) {
                logger.trace("    " + object);
            }
            logger.trace("  }");
        }

        logger.trace("calling soap.update...");

        UpdateResponse updateResponse = soap.update(updateRequest);

        if (logger.isTraceEnabled()) {
            logger.trace("UpdateResponse:");
            logger.trace("  requestId = " + updateResponse.getRequestID());
            logger.trace("  overallStatus = " + updateResponse.getOverallStatus());
            logger.trace("  results = {");
            for (UpdateResult result : updateResponse.getResults()) {
                logger.trace("    " + result);
            }
            logger.trace("  }");
        }

        response.setRequestId(updateResponse.getRequestID());
        if (updateResponse.getOverallStatus().equals("OK")) {
            response.setStatus(ETResult.Status.OK);
        } else if (updateResponse.getOverallStatus().equals("Error")) {
            response.setStatus(ETResult.Status.ERROR);
        }
        response.setResponseCode(updateResponse.getOverallStatus());
        response.setResponseMessage(updateResponse.getOverallStatus());
        for (UpdateResult updateResult : updateResponse.getResults()) {
            //
            // Allocate a new (external) object:
            //

            @SuppressWarnings("unchecked")
            Class<T> externalType = (Class<T>) objects.get(0).getClass();

            T externalObject = null;
            try {
                externalObject = externalType.newInstance();
            } catch (Exception ex) {
                throw new ETSdkException("could not instantiate "
                        + externalType.getName(), ex);
            }

            externalObject.setClient(client);

            //
            // Convert from internal representation:
            //

            // not all SOAP calls return the object though some do..
            APIObject internalObject = updateResult.getObject();
            if (internalObject != null) {
                externalObject.fromInternal(updateResult.getObject());
            } else {
                // XXX populate fields from the object passed to the call?
            }

            //
            // Add result to the list of results:
            //

            ETResult<T> result = new ETResult<T>();
            if (updateResult.getStatusCode().equals("OK")) {
                result.setStatus(ETResult.Status.OK);
            } else if (updateResult.getStatusCode().equals("Error")) {
                result.setStatus(ETResult.Status.ERROR);
            }
            result.setResponseCode(updateResult.getStatusCode());
            result.setResponseMessage(updateResult.getStatusMessage());
            result.setErrorCode(updateResult.getErrorCode());
            if (result.getResponseCode().equals("OK")) { // XXX?
                result.setObject(externalObject);
            }
            response.addResult(result);
        }

        return response;
    }

    /**
     * @param <T>                   The type which extends from ETSoapObject
     * @param client                The ETClient object
     * @param objects               The List of objects to delete
     * @return                      The ETResponse object of type T which extends from ETSoapObject
     * @throws ETSdkException 
     */
    public static <T extends ETSoapObject> ETResponse<T> delete(ETClient client,
                                                                List<T> objects)
        throws ETSdkException
    {
        List<APIObject> internalObjects = new ArrayList<APIObject>();

        //
        // Convert the external objects to internal objects:
        //

        for (T object : objects) {
            object.setClient(client);
            internalObjects.add(object.toInternal());
        }

        return delete(client, internalObjects, true);
    }

    /**
     * @param <T>                   The type which extends from ETSoapObject
     * @param client                The ETClient object
     * @param objects               The List of APIObjects to delete
     * @param internal              true if it is internal, false otherwise
     * @return                      The ETResponse object of type T which extends from ETSoapObject
     * @throws ETSdkException 
     */
    protected static <T extends ETSoapObject> ETResponse<T> delete(ETClient client,
                                                                   List<APIObject> objects,
                                                                   boolean internal)
        throws ETSdkException
    {
        ETResponse<T> response = new ETResponse<T>();

        if (objects == null || objects.size() == 0) {
            response.setStatus(ETResult.Status.OK);
            return response;
        }

        //
        // Get handle to the SOAP connection:
        //

        ETSoapConnection connection = client.getSoapConnection();

        //
        // Automatically refresh the token if necessary:
        //

        client.refreshToken();

        //
        // Perform the SOAP delete:
        //
        String obj = "";
        for (APIObject object : objects)
            obj += object.getClass().getSimpleName();//.substring(2);

        Soap soap = connection.getSoap("delete", obj);
        

        DeleteRequest deleteRequest = new DeleteRequest();
        deleteRequest.setOptions(new DeleteOptions());
        deleteRequest.getObjects().addAll(objects);

        if (logger.isTraceEnabled()) {
            logger.trace("DeleteRequest:");
            logger.trace("  objects = {");
            for (APIObject object : deleteRequest.getObjects()) {
                logger.trace("    " + object);
            }
            logger.trace("  }");
        }

        logger.trace("calling soap.delete...");

        DeleteResponse deleteResponse = soap.delete(deleteRequest);

        if (logger.isTraceEnabled()) {
            logger.trace("DeleteResponse:");
            logger.trace("  requestId = " + deleteResponse.getRequestID());
            logger.trace("  overallStatus = " + deleteResponse.getOverallStatus());
            logger.trace("  results = {");
            for (DeleteResult result : deleteResponse.getResults()) {
                logger.trace("    " + result);
            }
            logger.trace("  }");
        }

        response.setRequestId(deleteResponse.getRequestID());
        if (deleteResponse.getOverallStatus().equals("OK")) {
            response.setStatus(ETResult.Status.OK);
        } else if (deleteResponse.getOverallStatus().equals("Error")) {
            response.setStatus(ETResult.Status.ERROR);
        }
        response.setResponseCode(deleteResponse.getOverallStatus());
        response.setResponseMessage(deleteResponse.getOverallStatus());
        for (DeleteResult deleteResult : deleteResponse.getResults()) {
            ETResult<T> result = new ETResult<T>();
            if (deleteResult.getStatusCode().equals("OK")) {
                result.setStatus(ETResult.Status.OK);
            } else if (deleteResult.getStatusCode().equals("Error")) {
                result.setStatus(ETResult.Status.ERROR);
            }
            result.setResponseCode(deleteResult.getStatusCode());
            result.setResponseMessage(deleteResult.getStatusMessage());
            result.setErrorCode(deleteResult.getErrorCode());
            response.addResult(result);
        }

        return response;
    }

    private void registerConverters() {
        //
        // Register converters:
        //

        ConvertUtilsBean convertUtils = BeanUtilsBean.getInstance().getConvertUtils();

        // ETDataExtension
        convertUtils.register(new ExternalObjectConverter(),
                ETDataExtension.class);
        convertUtils.register(new InternalObjectConverter(),
                DataExtension.class);
        
//        convertUtils.register(new ExternalObjectConverter(),
//                ETSendableDataExtension.class);
        convertUtils.register(new ExternalObjectConverter(),
                ETProfileAttribute.class);
        
        // ETDataExtensionColumn
        convertUtils.register(new ExternalObjectConverter(),
                ETDataExtensionColumn.class);
        convertUtils.register(new InternalObjectConverter(),
                DataExtensionField.class);

        // ETExtractDescription
        convertUtils.register(new ExternalObjectConverter(),
                ETExtractDescription.class);
        convertUtils.register(new InternalObjectConverter(),
                ExtractDescription.class);

        // ETDataExtensionColumnType
        convertUtils.register(new EnumConverter(),
                ETDataExtensionColumn.Type.class);
        convertUtils.register(new EnumConverter(),
                DataExtensionFieldType.class);

        // ETDataExtensionRow
        convertUtils.register(new ExternalObjectConverter(),
                ETDataExtensionRow.class);
        convertUtils.register(new InternalObjectConverter(),
                DataExtensionObject.class);
        // data extension row: internal to external
        convertUtils.register(new DataExtensionRowConverter(),
                Map.class);
        // data extension row: external to internal
        convertUtils.register(new DataExtensionRowConverter(),
                ObjectExtension.Properties.class);

        // ETEmail
        convertUtils.register(new ExternalObjectConverter(),
                ETEmail.class);
        convertUtils.register(new InternalObjectConverter(),
                Email.class);

        // ETEmail.Type
        convertUtils.register(new EnumConverter(),
                ETEmail.Type.class);
        convertUtils.register(new EnumConverter(),
                EmailType.class);

        // ETFolder
        convertUtils.register(new ExternalObjectConverter(),
                ETFolder.class);
        convertUtils.register(new InternalObjectConverter(),
                DataFolder.class);

        // ETList
        convertUtils.register(new ExternalObjectConverter(),
                ETList.class);
        convertUtils.register(new InternalObjectConverter(),
                com.exacttarget.fuelsdk.internal.List.class);

        // ETList.Classification
        convertUtils.register(new EnumConverter(),
                ETList.Classification.class);
        convertUtils.register(new EnumConverter(),
                ListClassificationEnum.class);

        // ETList.Type
        convertUtils.register(new EnumConverter(),
                ETList.Type.class);
        convertUtils.register(new EnumConverter(),
                ListTypeEnum.class);

        // ETSubscriber
        convertUtils.register(new ExternalObjectConverter(),
                ETSubscriber.class);
        convertUtils.register(new InternalObjectConverter(),
                Subscriber.class);

        // ETSubscriber.Attribute
        convertUtils.register(new EnumConverter(),
                ETSubscriber.Status.class);
//        convertUtils.register(new EnumConverter(),
//                Attribute.class);
//        convertUtils.register(new InternalObjectConverter(),
//                Attribute.class);

        // ETSubscriber.Status
        convertUtils.register(new EnumConverter(),
                ETSubscriber.Status.class);
        convertUtils.register(new EnumConverter(),
                SubscriberStatus.class);

        // ETTriggeredSend
        convertUtils.register(new EnumConverter(),
                ETTriggeredEmail.class);
        convertUtils.register(new EnumConverter(),
                TriggeredSendDefinition.class);

        // ETTriggeredSend.Status
        convertUtils.register(new EnumConverter(),
                ETTriggeredEmail.Status.class);
        convertUtils.register(new EnumConverter(),
                TriggeredSendStatusEnum.class);
    }

    public class ExternalObjectConverter implements Converter {
        @SuppressWarnings("rawtypes")
        /**
         * @param type      The class type to convert
         * @param value     The value to convert
         * @return          The converted object
         */
        public Object convert(Class type, Object value) {
            ETSoapObject externalObject = null;
            try {
                externalObject = (ETSoapObject) type.newInstance();
            } catch (Exception ex) {
                throw new ConversionException("could not convert object", ex);
            }
            try {
                externalObject.fromInternal((APIObject) value);
            } catch (ETSdkException ex) {
                throw new ConversionException("could not convert object", ex);
            }
            return externalObject;
        }
    }

    public class InternalObjectConverter implements Converter {
        @SuppressWarnings("rawtypes")
        /**
         * @param type      The class type to convert
         * @param value     The value to convert
         * @return          The converted object
         */
        public Object convert(Class type, Object value) {
            APIObject internalObject = null;
            try {
                internalObject = ((ETSoapObject) value).toInternal();
            } catch (ETSdkException ex) {
                throw new ConversionException("could not convert object", ex);
            }
            return internalObject;
        }
    }

    public class DataExtensionRowConverter implements Converter {
        @SuppressWarnings({ "rawtypes", "unchecked" })
        /**
         * @param type      The class type to convert
         * @param value     The value to convert
         * @return          The converted object
         */
        public Object convert(Class type, Object value) {
            if (type == Map.class) {
                // we're converting from internal to external
                ObjectExtension.Properties properties
                    = (ObjectExtension.Properties) value;
                Map<String, String> columns = new HashMap<String, String>();
                for (APIProperty property : properties.getProperty()) {
                    columns.put(property.getName(), property.getValue());
                }
                return columns;
            } else if (type == ObjectExtension.Properties.class) {
                // we're converting from external to internal
                Map<String, String> columns = (Map<String, String>) value;
                ObjectExtension.Properties properties
                    = new ObjectExtension.Properties();
                for (String key : columns.keySet()) {
                    APIProperty property = new APIProperty();
                    property.setName(key); property.setValue(columns.get(key));
                    properties.getProperty().add(property);
                }
                return properties;
            }
            return value;
        }
    }

    public class EnumConverter implements Converter {
        @SuppressWarnings({ "rawtypes", "unchecked" })
        /**
         * @param type      The class type to convert
         * @param value     The value to convert
         * @return          The converted object
         */
        public Object convert(Class type, Object value) {
            return Enum.valueOf(type, value.toString());
        }
    }

    /**
     * 
     * @param internalObject  The internal APIObject
     * @return The ETSoapObject that is create from the internal APIObject parameter
     * @throws ETSdkException 
     */
    public ETSoapObject fromInternal(APIObject internalObject)
        throws ETSdkException
    {
        ETSoapObject externalObject = this; // for code readability

        Class<? extends ETSoapObject> externalType = externalObject.getClass();
        String externalTypeName = externalType.getSimpleName();
        Class<? extends APIObject> internalType = internalObject.getClass();
        String internalTypeName = internalType.getSimpleName();

        logger.trace("converting object from internal type "
                + internalTypeName);
        logger.trace("                    to external type "
                + externalTypeName);

        for (Field externalField : getAllFields(externalType)) {
            //
            // Skip this field if it doesn't have the @ExternalName
            // annotation (it's an internal field):
            //

            ExternalName externalName =
                    externalField.getAnnotation(ExternalName.class);
            if (externalName == null) {
                continue;
            }

            String externalFieldName = externalField.getName();
            String internalFieldName = null;

            InternalName internalName =
                    externalField.getAnnotation(InternalName.class);

            if (internalName != null) {
                internalFieldName = internalName.value();
            } else {
                // internal name is the same as external name
                internalFieldName = externalFieldName;
            }

            Object internalFieldValue = null;
            try {
                internalFieldValue =
                        PropertyUtils.getProperty(internalObject,
                                                  internalFieldName);
            } catch (Exception ex) {
                throw new ETSdkException("could not get property \""
                        + internalFieldName
                        + "\" of object "
                        + internalObject,
                        ex);
            }

            if (internalFieldValue == null) {
                continue;
            }

            if (internalFieldValue instanceof List) {
                externalField.setAccessible(true);

                List<ETSoapObject> externalList = new ArrayList<ETSoapObject>();
                @SuppressWarnings("unchecked")
                List<APIObject> internalList
                    = (List<APIObject>) internalFieldValue;

                Type fieldType = externalField.getGenericType();
                assert fieldType instanceof ParameterizedType;
                ParameterizedType parameterizedType
                    = (ParameterizedType) fieldType;
                assert parameterizedType.getActualTypeArguments().length == 1;
                Class<?> externalItemType
                    = (Class<?>) parameterizedType.getActualTypeArguments()[0];

                for (APIObject internalItem : internalList) {
                    ETSoapObject externalItem = null;
                    try {
                        externalItem = (ETSoapObject) externalItemType.newInstance();
                    } catch (Exception ex) {
                        throw new ETSdkException("could not instantiate "
                                + externalItemType.getName(), ex);
                    }
                    externalList.add(externalItem.fromInternal(internalItem));
                }

                try {
                    externalField.set(externalObject, externalList);
                } catch (Exception ex) {
                    throw new ETSdkException("could not set field \""
                            + externalFieldName
                            + "\" of object "
                            + externalObject,
                            ex);
                }

                continue;
            }

            try {
                BeanUtils.setProperty(externalObject,
                                      externalFieldName,
                                      internalFieldValue);
            } catch (Exception ex) {
                throw new ETSdkException("could not set property \""
                        + externalFieldName
                        + "\" of object "
                        + externalObject,
                        ex);
            }

            if (logger.isTraceEnabled()) {
                Field internalField = getField(internalType,
                                               internalFieldName);

                Object externalFieldValue = null;
                try {
                    externalFieldValue =
                            PropertyUtils.getProperty(externalObject,
                                                      externalFieldName);
                } catch (Exception ex) {
                    throw new ETSdkException("could not get property \""
                            + externalFieldName
                            + "\" of object "
                            + externalObject,
                            ex);
                }

                logger.trace("  converted field "
                        + internalTypeName + "." + internalFieldName
                        + " (type="
                        + internalField.getType().getSimpleName()
                        + ", value="
                        + internalFieldValue
                        + ")");
                logger.trace("         to field "
                        + externalTypeName + "." + externalFieldName
                        + " (type="
                        + externalField.getType().getSimpleName()
                        + ", value="
                        + externalFieldValue
                        + ")");
            }
        }

        return externalObject;
    }

    /**
     * 
     * @return The internal APIObject
     * @throws ETSdkException 
     */
    public APIObject toInternal()
        throws ETSdkException
    {
        ETSoapObject externalObject = this; // for code readability

        Class<? extends ETSoapObject> externalType = externalObject.getClass();

        //
        // Use the @SoapObject annotation to determine internalType:
        //

        SoapObject internalTypeAnnotation
            = externalType.getAnnotation(SoapObject.class);
        assert internalTypeAnnotation != null;
        Class<? extends APIObject> internalType = internalTypeAnnotation.internalType();
        assert internalType != null;

        String externalTypeName = externalType.getSimpleName();
        String internalTypeName = internalType.getSimpleName();

        logger.trace("converting object from external type "
                + externalTypeName);
        logger.trace("                    to internal type "
                + internalTypeName);

        APIObject internalObject = null;
        try {
            internalObject = internalType.newInstance();
        } catch (Exception ex) {
            throw new ETSdkException("could not instantiate "
                    + internalType.getName(), ex);
        }

        for (Field externalField : getAllFields(externalType)) {
            //
            // Skip this field if it doesn't have the @ExternalName
            // annotation (it's an internal field):
            //

            ExternalName externalName =
                    externalField.getAnnotation(ExternalName.class);
            if (externalName == null) {
                continue;
            }

            String externalFieldName = externalField.getName();
            String internalFieldName = null;

            InternalName internalName =
                    externalField.getAnnotation(InternalName.class);

            if (internalName != null) {
                internalFieldName = internalName.value();
            } else {
                // internal name is the same as external name
                internalFieldName = externalFieldName;
            }

            Object externalFieldValue = null;
            try {
                externalFieldValue =
                        PropertyUtils.getProperty(externalObject,
                                                  externalFieldName);
            } catch (Exception ex) {
                throw new ETSdkException("could not get property \""
                        + externalFieldName
                        + "\" of object "
                        + externalObject,
                        ex);
            }

            if (externalFieldValue == null) {
                continue;
            }

            if (externalFieldValue instanceof List) {
                Field internalField = getField(internalType,
                                               internalFieldName);

                internalField.setAccessible(true);

                List<APIObject> internalList = new ArrayList<APIObject>();
                @SuppressWarnings("unchecked")
                List<ETSoapObject> externalList
                    = (List<ETSoapObject>) externalFieldValue;

                for (ETSoapObject externalItem : externalList) {
                    internalList.add(externalItem.toInternal());
                }

                if (internalFieldName.equals("fields")) {
                    //
                    // This list contains data extension columns:
                    //

                    DataExtension.Fields fields = new DataExtension.Fields();
                    for (APIObject field : internalList) {
                        fields.getField().add((DataExtensionField) field);
                    }
                    try {
                        internalField.set(internalObject, fields);
                    } catch (Exception ex) {
                        throw new ETSdkException("could not set field \""
                                + internalFieldName
                                + "\" of object "
                                + internalObject,
                                ex);
                    }
                } else {
                    try {
                        internalField.set(internalObject, internalList);
                    } catch (Exception ex) {
                        throw new ETSdkException("could not set field \""
                                + internalFieldName
                                + "\" of object "
                                + internalObject,
                                ex);
                    }
                }

                continue;
            }

            try {
                BeanUtils.setProperty(internalObject,
                                      internalFieldName,
                                      externalFieldValue);
            } catch (Exception ex) {
                //ex.printStackTrace();
                throw new ETSdkException("could not set property \""
                        + internalFieldName
                        + "\" of object "
                        + internalObject,
                        ex);
            }

            if (logger.isTraceEnabled()) {
                Field internalField = getField(internalType,
                                               internalFieldName);

                Object internalFieldValue = null;
                try {
                    internalFieldValue =
                            PropertyUtils.getProperty(internalObject,
                                                      internalFieldName);
                } catch (Exception ex) {
                    throw new ETSdkException("could not get property \""
                            + internalFieldName
                            + "\" of object "
                            + internalObject,
                            ex);
                }

                logger.trace("  converted field "
                        + externalTypeName + "." + externalFieldName
                        + " (type="
                        + externalField.getType().getSimpleName()
                        + ", value="
                        + externalFieldValue
                        + ")");
                logger.trace("         to field "
                        + internalTypeName + "." + internalFieldName
                        + " (type="
                        + internalField.getType().getSimpleName()
                        + ", value="
                        + internalFieldValue
                        + ")");
            }
        }

        return internalObject;
    }

    protected static String getInternalProperty(Class<? extends ETSoapObject> type,
                                                String name)
        throws ETSdkException
    {
        String internalProperty = null;

        Class<? extends ETSoapObject> externalType = type; // for code readability

        Field externalField = null;
        try {
            externalField = getField(externalType, name);
        } catch (ETSdkException ex) {
            return name; // XXX
        }

        InternalProperty internalPropertyAnnotation =
                externalField.getAnnotation(InternalProperty.class);

        if (internalPropertyAnnotation != null) {
            internalProperty = internalPropertyAnnotation.value();
        } else {
            //
            // The internal property name can be found in the
            // @XmlElement (or @XmlElementRef) annotation on the
            // NAME internal field of the CXF generated class:
            //

            //
            // Use the @SoapObject annotation to determine internalType:
            //

            SoapObject internalTypeAnnotation
                = externalType.getAnnotation(SoapObject.class);
            assert internalTypeAnnotation != null;
            Class<? extends APIObject> internalType = internalTypeAnnotation.internalType();
            assert internalType != null;

            InternalName internalNameAnnotation =
                    externalField.getAnnotation(InternalName.class);

            String internalName = null;
            if (internalNameAnnotation != null) {
                internalName = internalNameAnnotation.value();
            } else {
                // internal name is the same as external name
                internalName = externalField.getName();
            }

            Field internalField = getField(internalType, internalName);

            XmlElement element =
                    internalField.getAnnotation(XmlElement.class);
            if (element != null) {
                internalProperty = element.name();
            } else {
                //
                // Optional dateTimes are annotated with @XmlElementRef:
                //

                XmlElementRef elementRef =
                        internalField.getAnnotation(XmlElementRef.class);
                if (elementRef != null) {
                    internalProperty = elementRef.name();
                }
            }
        }

        return internalProperty;
    }

    // XXX private?
    protected static List<String> getInternalProperties(Class<? extends ETSoapObject> type)
        throws ETSdkException
    {
        List<String> internalProperties = new ArrayList<String>();

        Class<? extends ETSoapObject> externalType = type; // for code readability

        //
        // Use the @SoapObject annotation to determine internalType:
        //

        SoapObject internalTypeAnnotation
            = externalType.getAnnotation(SoapObject.class);
        assert internalTypeAnnotation != null;
        Class<? extends APIObject> internalType = internalTypeAnnotation.internalType();
        assert internalType != null;

        //
        // Build a list of fields from externalType and all superclasses:
        //

        List<Field> externalFields = getAllFields(externalType);

        //
        // Walk the list of external fields, building a list of the
        // corresponding property names:
        //

        for (Field externalField : externalFields) {
            //
            // Skip this field if it doesn't have the @ExternalName
            // annotation (it's an internal field):
            //

            ExternalName externalName =
                    externalField.getAnnotation(ExternalName.class);
            if (externalName == null) {
                continue;
            }

            String internalProperty = getInternalProperty(externalType,
                                                          externalField.getName());
            assert internalProperty != null;
            internalProperties.add(internalProperty);
        }

        return internalProperties;
    }

    /**
     * 
     * @param expression The ETExpression to create FilterPart
     * @return The FilterPart object, can be SimpleFilterPart or ComplexFilterPart
     */
    public static FilterPart toFilterPart(ETExpression expression) {
        ETExpression.Operator operator = expression.getOperator();
        if (operator == null) {
            return null;
        }
        if (operator == ETExpression.Operator.AND ||
            operator == ETExpression.Operator.OR)
        {
            List<ETExpression> subexpressions = expression.getSubexpressions();
            ComplexFilterPart complexFilterPart = new ComplexFilterPart();
            complexFilterPart.setLeftOperand(toFilterPart(subexpressions.get(0)));
            if (operator == ETExpression.Operator.AND) {
                complexFilterPart.setLogicalOperator(LogicalOperators.AND);
            } else if (operator == ETExpression.Operator.OR) {
                complexFilterPart.setLogicalOperator(LogicalOperators.OR);
            }
            complexFilterPart.setRightOperand(toFilterPart(subexpressions.get(1)));
            return complexFilterPart;
        } else {
            String property = expression.getProperty();
            List<String> values = expression.getValues();
            SimpleFilterPart simpleFilterPart = new SimpleFilterPart();
            simpleFilterPart.setProperty(property);
            switch (operator) {
              case EQUALS:
                simpleFilterPart.setSimpleOperator(SimpleOperators.EQUALS);
                break;
              case NOT_EQUALS:
                simpleFilterPart.setSimpleOperator(SimpleOperators.NOT_EQUALS);
                break;
              case LESS_THAN:
                simpleFilterPart.setSimpleOperator(SimpleOperators.LESS_THAN);
                break;
              case LESS_THAN_OR_EQUALS:
                simpleFilterPart.setSimpleOperator(SimpleOperators.LESS_THAN_OR_EQUAL);
                break;
              case GREATER_THAN:
                simpleFilterPart.setSimpleOperator(SimpleOperators.GREATER_THAN);
                break;
              case GREATER_THAN_OR_EQUALS:
                simpleFilterPart.setSimpleOperator(SimpleOperators.GREATER_THAN_OR_EQUAL);
                break;
              case IS_NULL:
                simpleFilterPart.setSimpleOperator(SimpleOperators.IS_NULL);
                break;
              case IS_NOT_NULL:
                simpleFilterPart.setSimpleOperator(SimpleOperators.IS_NOT_NULL);
                break;
              case BETWEEN:
                simpleFilterPart.setSimpleOperator(SimpleOperators.BETWEEN);
                break;
              case IN:
                simpleFilterPart.setSimpleOperator(SimpleOperators.IN);
                break;
              case LIKE:
                simpleFilterPart.setSimpleOperator(SimpleOperators.LIKE);
                break;
              default:
                break;
            }
            for (String value : values) {
                simpleFilterPart.getValue().add(value);
            }
            return simpleFilterPart;
        }
    }
}
