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

package com.exacttarget.fuelsdk;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
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
import com.exacttarget.fuelsdk.annotations.SoapObject;
import com.exacttarget.fuelsdk.internal.APIObject;
import com.exacttarget.fuelsdk.internal.APIProperty;
import com.exacttarget.fuelsdk.internal.Account;
import com.exacttarget.fuelsdk.internal.AccountTypeEnum;
import com.exacttarget.fuelsdk.internal.BounceEvent;
import com.exacttarget.fuelsdk.internal.ClickEvent;
import com.exacttarget.fuelsdk.internal.ContentArea;
import com.exacttarget.fuelsdk.internal.CreateOptions;
import com.exacttarget.fuelsdk.internal.CreateRequest;
import com.exacttarget.fuelsdk.internal.CreateResponse;
import com.exacttarget.fuelsdk.internal.CreateResult;
import com.exacttarget.fuelsdk.internal.DataExtension;
import com.exacttarget.fuelsdk.internal.DataExtensionField;
import com.exacttarget.fuelsdk.internal.DataExtensionFieldType;
import com.exacttarget.fuelsdk.internal.DataExtensionObject;
import com.exacttarget.fuelsdk.internal.DataFolder;
import com.exacttarget.fuelsdk.internal.DataSourceTypeEnum;
import com.exacttarget.fuelsdk.internal.DeleteOptions;
import com.exacttarget.fuelsdk.internal.DeleteRequest;
import com.exacttarget.fuelsdk.internal.DeleteResponse;
import com.exacttarget.fuelsdk.internal.DeleteResult;
import com.exacttarget.fuelsdk.internal.DeliveryProfile;
import com.exacttarget.fuelsdk.internal.DeliveryProfileDomainTypeEnum;
import com.exacttarget.fuelsdk.internal.DeliveryProfileSourceAddressTypeEnum;
import com.exacttarget.fuelsdk.internal.Email;
import com.exacttarget.fuelsdk.internal.EmailSendDefinition;
import com.exacttarget.fuelsdk.internal.EmailType;
import com.exacttarget.fuelsdk.internal.EventType;
import com.exacttarget.fuelsdk.internal.LayoutType;
import com.exacttarget.fuelsdk.internal.ListClassificationEnum;
import com.exacttarget.fuelsdk.internal.ListSubscriber;
import com.exacttarget.fuelsdk.internal.ListTypeEnum;
import com.exacttarget.fuelsdk.internal.ObjectExtension;
import com.exacttarget.fuelsdk.internal.OpenEvent;
import com.exacttarget.fuelsdk.internal.Permission;
import com.exacttarget.fuelsdk.internal.PermissionSet;
import com.exacttarget.fuelsdk.internal.RetrieveRequest;
import com.exacttarget.fuelsdk.internal.RetrieveRequestMsg;
import com.exacttarget.fuelsdk.internal.RetrieveResponseMsg;
import com.exacttarget.fuelsdk.internal.Role;
import com.exacttarget.fuelsdk.internal.SalutationSourceEnum;
import com.exacttarget.fuelsdk.internal.SendClassification;
import com.exacttarget.fuelsdk.internal.SendClassificationTypeEnum;
import com.exacttarget.fuelsdk.internal.SendDefinitionList;
import com.exacttarget.fuelsdk.internal.SendDefinitionListTypeEnum;
import com.exacttarget.fuelsdk.internal.SendPriorityEnum;
import com.exacttarget.fuelsdk.internal.SenderProfile;
import com.exacttarget.fuelsdk.internal.SentEvent;
import com.exacttarget.fuelsdk.internal.Soap;
import com.exacttarget.fuelsdk.internal.Subscriber;
import com.exacttarget.fuelsdk.internal.SubscriberList;
import com.exacttarget.fuelsdk.internal.SubscriberStatus;
import com.exacttarget.fuelsdk.internal.UnsubEvent;
import com.exacttarget.fuelsdk.internal.UpdateOptions;
import com.exacttarget.fuelsdk.internal.UpdateRequest;
import com.exacttarget.fuelsdk.internal.UpdateResponse;
import com.exacttarget.fuelsdk.internal.UpdateResult;
import com.exacttarget.fuelsdk.model.ETAccountType;
import com.exacttarget.fuelsdk.model.ETBounceEvent;
import com.exacttarget.fuelsdk.model.ETClickEvent;
import com.exacttarget.fuelsdk.model.ETContentArea;
import com.exacttarget.fuelsdk.model.ETDataSourceType;
import com.exacttarget.fuelsdk.model.ETDeliveryProfile;
import com.exacttarget.fuelsdk.model.ETDeliveryProfileDomainType;
import com.exacttarget.fuelsdk.model.ETDeliveryProfileSourceAddressType;
import com.exacttarget.fuelsdk.model.ETEmail;
import com.exacttarget.fuelsdk.model.ETEmailSendDefinition;
import com.exacttarget.fuelsdk.model.ETEmailType;
import com.exacttarget.fuelsdk.model.ETEventType;
import com.exacttarget.fuelsdk.model.ETLayoutType;
import com.exacttarget.fuelsdk.model.ETListSubscriber;
import com.exacttarget.fuelsdk.model.ETOpenEvent;
import com.exacttarget.fuelsdk.model.ETOrganization;
import com.exacttarget.fuelsdk.model.ETPermission;
import com.exacttarget.fuelsdk.model.ETPermissionSet;
import com.exacttarget.fuelsdk.model.ETRole;
import com.exacttarget.fuelsdk.model.ETSalutationSource;
import com.exacttarget.fuelsdk.model.ETSendClassification;
import com.exacttarget.fuelsdk.model.ETSendClassificationType;
import com.exacttarget.fuelsdk.model.ETSendDefinitionList;
import com.exacttarget.fuelsdk.model.ETSendDefinitionListType;
import com.exacttarget.fuelsdk.model.ETSendPriority;
import com.exacttarget.fuelsdk.model.ETSenderProfile;
import com.exacttarget.fuelsdk.model.ETSentEvent;
import com.exacttarget.fuelsdk.model.ETSubscriber;
import com.exacttarget.fuelsdk.model.ETSubscriberList;
import com.exacttarget.fuelsdk.model.ETSubscriberStatus;
import com.exacttarget.fuelsdk.model.ETUnsubEvent;

public abstract class ETSoapObject extends ETObject {
    private static Logger logger = Logger.getLogger(ETSoapObject.class);

    @ExternalName("id")
    private Integer id = null;
    @ExternalName("key")
    private String customerKey = null;
    @ExternalName("dateCreated")
    private Date createdDate = null;
    @ExternalName("dateLastModified")
    private Date modifiedDate = null;

    public ETSoapObject() {
        //
        // Register object converters:
        //

        ConvertUtilsBean convertUtils = BeanUtilsBean.getInstance().getConvertUtils();

        // ETAccountType
        convertUtils.register(new EnumConverter(),
                ETAccountType.class);
        convertUtils.register(new EnumConverter(),
                AccountTypeEnum.class);

        // ETBounceEvent
        convertUtils.register(new ExternalObjectConverter(),
                ETBounceEvent.class);
        convertUtils.register(new InternalObjectConverter(),
                BounceEvent.class);

        // ETClickEvent
        convertUtils.register(new ExternalObjectConverter(),
                ETClickEvent.class);
        convertUtils.register(new InternalObjectConverter(),
                ClickEvent.class);

        // ETContentArea
        convertUtils.register(new ExternalObjectConverter(),
                ETContentArea.class);
        convertUtils.register(new InternalObjectConverter(),
                ContentArea.class);

        // ETDataExtension
        convertUtils.register(new ExternalObjectConverter(),
                ETDataExtension.class);
        convertUtils.register(new InternalObjectConverter(),
                DataExtension.class);

        // ETDataExtensionColumn
        convertUtils.register(new ExternalObjectConverter(),
                ETDataExtensionColumn.class);
        convertUtils.register(new InternalObjectConverter(),
                DataExtensionField.class);

        // ETDataExtensionColumnType
        convertUtils.register(new EnumConverter(),
                ETDataExtensionColumnType.class);
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

        // ETDataSourceType
        convertUtils.register(new EnumConverter(),
                ETDataSourceType.class);
        convertUtils.register(new EnumConverter(),
                DataSourceTypeEnum.class);

        // ETDeliveryProfile
        convertUtils.register(new ExternalObjectConverter(),
                ETDeliveryProfile.class);
        convertUtils.register(new InternalObjectConverter(),
                DeliveryProfile.class);

        // ETDeliveryProfileDomainType
        convertUtils.register(new EnumConverter(),
                ETDeliveryProfileDomainType.class);
        convertUtils.register(new EnumConverter(),
                DeliveryProfileDomainTypeEnum.class);

        // ETDeliveryProfileSourceAddressType
        convertUtils.register(new EnumConverter(),
                ETDeliveryProfileSourceAddressType.class);
        convertUtils.register(new EnumConverter(),
                DeliveryProfileSourceAddressTypeEnum.class);

        // ETEmail
        convertUtils.register(new ExternalObjectConverter(),
                ETEmail.class);
        convertUtils.register(new InternalObjectConverter(),
                Email.class);

        // ETEmailSendDefinition
        convertUtils.register(new ExternalObjectConverter(),
                ETEmailSendDefinition.class);
        convertUtils.register(new InternalObjectConverter(),
                EmailSendDefinition.class);

        // ETEmailType
        convertUtils.register(new EnumConverter(),
                ETEmailType.class);
        convertUtils.register(new EnumConverter(),
                EmailType.class);

        // ETEventType
        convertUtils.register(new EnumConverter(),
                ETEventType.class);
        convertUtils.register(new EnumConverter(),
                EventType.class);

        // ETFolder
        convertUtils.register(new ExternalObjectConverter(),
                ETFolder.class);
        convertUtils.register(new InternalObjectConverter(),
                DataFolder.class);

        // ETLayoutType
        convertUtils.register(new EnumConverter(),
                ETLayoutType.class);
        convertUtils.register(new EnumConverter(),
                LayoutType.class);

        // ETList
        convertUtils.register(new ExternalObjectConverter(),
                ETList.class);
        convertUtils.register(new InternalObjectConverter(),
                com.exacttarget.fuelsdk.internal.List.class);

        // ETListClassification
        convertUtils.register(new EnumConverter(),
                ETListClassification.class);
        convertUtils.register(new EnumConverter(),
                ListClassificationEnum.class);

        // ETListSubscriber
        convertUtils.register(new ExternalObjectConverter(),
                ETListSubscriber.class);
        convertUtils.register(new InternalObjectConverter(),
                ListSubscriber.class);

        // ETListType
        convertUtils.register(new EnumConverter(),
                ETListType.class);
        convertUtils.register(new EnumConverter(),
                ListTypeEnum.class);

        // ETOpenEvent
        convertUtils.register(new ExternalObjectConverter(),
                ETOpenEvent.class);
        convertUtils.register(new InternalObjectConverter(),
                OpenEvent.class);

        // ETOrganization
        convertUtils.register(new ExternalObjectConverter(),
                ETOrganization.class);
        convertUtils.register(new InternalObjectConverter(),
                Account.class);

        // ETPermission
        convertUtils.register(new ExternalObjectConverter(),
                ETPermission.class);
        convertUtils.register(new InternalObjectConverter(),
                Permission.class);

        // ETPermissionSet
        convertUtils.register(new ExternalObjectConverter(),
                ETPermissionSet.class);
        convertUtils.register(new InternalObjectConverter(),
                PermissionSet.class);

        // ETRole
        convertUtils.register(new ExternalObjectConverter(),
                ETRole.class);
        convertUtils.register(new InternalObjectConverter(),
                Role.class);

        // ETSalutationSource
        convertUtils.register(new EnumConverter(),
                ETSalutationSource.class);
        convertUtils.register(new EnumConverter(),
                SalutationSourceEnum.class);

        // ETSendClassification
        convertUtils.register(new ExternalObjectConverter(),
                ETSendClassification.class);
        convertUtils.register(new InternalObjectConverter(),
                SendClassification.class);

        // ETSendClassificationType
        convertUtils.register(new EnumConverter(),
                ETSendClassificationType.class);
        convertUtils.register(new EnumConverter(),
                SendClassificationTypeEnum.class);

        // ETSendDefinitionList
        convertUtils.register(new ExternalObjectConverter(),
                ETSendDefinitionList.class);
        convertUtils.register(new InternalObjectConverter(),
                SendDefinitionList.class);

        // ETSendDefinitionListType
        convertUtils.register(new EnumConverter(),
                ETSendDefinitionListType.class);
        convertUtils.register(new EnumConverter(),
                SendDefinitionListTypeEnum.class);

        // ETSendPriority
        convertUtils.register(new EnumConverter(),
                ETSendPriority.class);
        convertUtils.register(new EnumConverter(),
                SendPriorityEnum.class);

        // ETSenderProfile
        convertUtils.register(new ExternalObjectConverter(),
                ETSenderProfile.class);
        convertUtils.register(new InternalObjectConverter(),
                SenderProfile.class);

        // ETSentEvent
        convertUtils.register(new ExternalObjectConverter(),
                ETSentEvent.class);
        convertUtils.register(new InternalObjectConverter(),
                SentEvent.class);

        // ETSubscriber
        convertUtils.register(new ExternalObjectConverter(),
                ETSubscriber.class);
        convertUtils.register(new InternalObjectConverter(),
                Subscriber.class);

        // ETSubscriberList
        convertUtils.register(new ExternalObjectConverter(),
                ETSubscriberList.class);
        convertUtils.register(new InternalObjectConverter(),
                SubscriberList.class);

        // ETSubscriberStatus
        convertUtils.register(new EnumConverter(),
                ETSubscriberStatus.class);
        convertUtils.register(new EnumConverter(),
                SubscriberStatus.class);

        // ETUnsubEvent
        convertUtils.register(new ExternalObjectConverter(),
                ETUnsubEvent.class);
        convertUtils.register(new InternalObjectConverter(),
                UnsubEvent.class);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomerKey() {
        return customerKey;
    }

    public void setCustomerKey(String customerKey) {
        this.customerKey = customerKey;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    protected static <T extends ETSoapObject> ETResponse<ETResult> create(ETClient client,
                                                                          List<T> objects)
        throws ETSdkException
    {
        ETResponse<ETResult> response = new ETResponse<ETResult>();

        if (objects == null || objects.size() == 0) {
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

        Soap soap = connection.getSoap();

        CreateRequest createRequest = new CreateRequest();
        createRequest.setOptions(new CreateOptions());
        for (T object : objects) {
            createRequest.getObjects().add(object.toInternal());
        }

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
        response.setStatusCode(createResponse.getOverallStatus());
        response.setStatusMessage(createResponse.getOverallStatus());
        for (CreateResult createResult : createResponse.getResults()) {
            ETResult status = new ETResult();
            status.setStatusCode(createResult.getStatusCode());
            status.setStatusMessage(createResult.getStatusMessage());
            status.setErrorCode(createResult.getErrorCode());
            status.setId(createResult.getNewID());
            status.setGuid(createResult.getNewObjectID());
            response.addResult(status);
        }

        return response;
    }

    protected static <T extends ETSoapObject> ETResponse<T> retrieve(ETClient client,
                                                                     ETFilter filter,
                                                                     Integer page,
                                                                     Integer pageSize,
                                                                     Class<T> type,
                                                                     String... properties)
        throws ETSdkException
    {
        if ((page != null) || (pageSize != null)) {
            throw new ETSdkException("SOAP objects do not support paginated retrieves");
        }

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

        //
        // Determine properties to retrieve:
        //

        List<String> internalProperties = null;

        if (properties.length > 0) {
            //
            // Only request those properties specified:
            //

            internalProperties = new ArrayList<String>();

            String[] externalProperties = properties; // for code readability

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
        }

        //
        // Perform the SOAP retrieve:
        //

        Soap soap = connection.getSoap();

        RetrieveRequest retrieveRequest = new RetrieveRequest();
        retrieveRequest.setObjectType(internalType.getSimpleName());
        retrieveRequest.getProperties().addAll(internalProperties);
        if (filter != null) {
            retrieveRequest.setFilter(filter.getSoapFilter());
        }

        if (logger.isTraceEnabled()) {
            logger.trace("RetrieveRequest:");
            logger.trace("  objectType = " + retrieveRequest.getObjectType());
            String line = null;
            for (String property : retrieveRequest.getProperties()) {
                if (line == null) {
                    line = "  properties = { " + property;
                } else {
                    line += ", " + property;
                }
            }
            logger.trace(line + " }");
            if (filter != null) {
                logger.trace("  filter = " + filter);
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
        response.setStatusCode(retrieveResponseMsg.getOverallStatus());
        response.setStatusMessage(retrieveResponseMsg.getOverallStatus());
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

            //
            // Convert from internal representation:
            //

            externalObject.fromInternal(internalObject);

            //
            // Add result to the list of results:
            //

            response.getResults().add(externalObject);
        }

        if (retrieveResponseMsg.getOverallStatus().equals("MoreDataAvailable")) {
            response.setMoreResults(true);
        }

        return response;
    }

    protected static <T extends ETSoapObject> ETResponse<ETResult> update(ETClient client,
                                                                          List<T> objects)
        throws ETSdkException
    {
        ETResponse<ETResult> response = new ETResponse<ETResult>();

        if (objects == null || objects.size() == 0) {
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

        Soap soap = connection.getSoap();

        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.setOptions(new UpdateOptions());
        for (T object : objects) {
            updateRequest.getObjects().add(object.toInternal());
        }

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
        response.setStatusCode(updateResponse.getOverallStatus());
        response.setStatusMessage(updateResponse.getOverallStatus());
        for (UpdateResult updateResult : updateResponse.getResults()) {
            ETResult status = new ETResult();
            status.setStatusCode(updateResult.getStatusCode());
            status.setStatusMessage(updateResult.getStatusMessage());
            status.setErrorCode(updateResult.getErrorCode());
            response.addResult(status);
        }

        return response;
    }

    protected static <T extends ETSoapObject> ETResponse<ETResult> delete(ETClient client,
                                                                          List<T> objects)
        throws ETSdkException
    {
        ETResponse<ETResult> response = new ETResponse<ETResult>();

        if (objects == null || objects.size() == 0) {
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

        Soap soap = connection.getSoap();

        DeleteRequest deleteRequest = new DeleteRequest();
        deleteRequest.setOptions(new DeleteOptions());
        for (T object : objects) {
            deleteRequest.getObjects().add(object.toInternal());
        }

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
        response.setStatusCode(deleteResponse.getOverallStatus());
        response.setStatusMessage(deleteResponse.getOverallStatus());
        for (DeleteResult deleteResult : deleteResponse.getResults()) {
            ETResult status = new ETResult();
            status.setStatusCode(deleteResult.getStatusCode());
            status.setStatusMessage(deleteResult.getStatusMessage());
            status.setErrorCode(deleteResult.getErrorCode());
            response.addResult(status);
        }

        return response;
    }

    public class ExternalObjectConverter implements Converter {
        @SuppressWarnings("rawtypes")
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
        public Object convert(Class type, Object value) {
            return Enum.valueOf(type, value.toString());
        }
    }

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
                //
                // An internal field name was specified in
                // the @InternalName annotation:
                //

                if (!internalName.field().equals("")) {
                    internalFieldName = internalName.field();
                } else {
                    // "" is the default value for field(),
                    // included so field() is optional (see
                    // InternalName.java)
                    internalFieldName = externalFieldName;
                }
            } else {
                //
                // An internal field name was not specified
                // in the @InternalName annotation, so assume
                // it's the same as the external field name:
                //

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
                //
                // An internal field name was specified in
                // the @InternalName annotation:
                //

                if (!internalName.field().equals("")) {
                    internalFieldName = internalName.field();
                } else {
                    // "" is the default value for field(),
                    // included so field() is optional (see
                    // InternalName.java)
                    internalFieldName = externalFieldName;
                }
            } else {
                //
                // An internal field name was not specified
                // in the @InternalName annotation, so assume
                // it's the same as the external field name:
                //

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

                // XXX needed?

//                    if (internalFieldName.equals("fields")) {
//                        //
//                        // This list contains data extension columns:
//                        //
//
//                        DataExtension.Fields fields = new DataExtension.Fields();
//                        for (APIObject field : internalList) {
//                            fields.getField().add((DataExtensionField) field);
//                        }
//                        try {
//                            internalField.set(internalObject, fields);
//                        } catch (Exception ex) {
//                            throw new ETSdkException("could not set field \""
//                                    + internalFieldName
//                                    + "\" of object "
//                                    + internalObject, ex);
//                        }

                try {
                    internalField.set(internalObject, internalList);
                } catch (Exception ex) {
                    throw new ETSdkException("could not set field \""
                            + internalFieldName
                            + "\" of object "
                            + internalObject,
                            ex);
                }

                continue;
            }

            try {
                BeanUtils.setProperty(internalObject,
                                      internalFieldName,
                                      externalFieldValue);
            } catch (Exception ex) {
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

    private static String getInternalProperty(Class<? extends ETSoapObject> type,
                                              String name)
        throws ETSdkException
    {
        String internalProperty = null;

        Class<? extends ETSoapObject> externalType = type; // for code readability

        Field externalField = getField(externalType, name);

        InternalName internalNameAnnotation =
                externalField.getAnnotation(InternalName.class);

        if (internalNameAnnotation != null &&
            !internalNameAnnotation.property().equals(""))
        {
            //
            // The internal property name was specified via the
            // @InternalName annotation:
            //

            internalProperty = internalNameAnnotation.property();
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

            String internalFieldName = null;
            if (internalNameAnnotation == null ||
                internalNameAnnotation.field().equals(""))
            {
                internalFieldName = name;
            } else {
                internalFieldName = internalNameAnnotation.field();
            }

            Field internalField = getField(internalType, internalFieldName);

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

    private static List<String> getInternalProperties(Class<? extends ETSoapObject> type)
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

    private static Field getField(Class<?> type, String name)
        throws ETSdkException
    {
        Field field = null;

        for (Class<?> t = type; t != null; t = t.getSuperclass()) {
            try {
                field = t.getDeclaredField(name);
                break;
            } catch (NoSuchFieldException ex) {
                continue;
            }
        }

        if (field == null) {
            throw new ETSdkException("field \""
                    + name
                    + "\" does not exist in class "
                    + type.getName());
        }

        return field;
    }

    private static List<Field> getAllFields(Class<?> type) {
        List<Field> fields = new ArrayList<Field>();

        // account for fields of superclasses too

        List<Class<?>> types = new ArrayList<Class<?>>();
        for (Class<?> t = type; t != null; t = t.getSuperclass()) {
            types.add(t);
        }

        // make sure superclass fields are first, to
        // enhance readability of the SOAP envelopes

        ListIterator<Class<?>> li = types.listIterator(types.size());
        while (li.hasPrevious()) {
            Class<?> t = li.previous();
            for (Field field : t.getDeclaredFields()) {
                fields.add(field);
            }
        }

        return fields;
    }
}
