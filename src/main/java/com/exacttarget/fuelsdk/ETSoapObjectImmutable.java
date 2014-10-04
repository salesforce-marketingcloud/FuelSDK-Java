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
import java.util.Arrays;
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

import com.exacttarget.fuelsdk.annotations.InternalSoapField;
import com.exacttarget.fuelsdk.annotations.InternalSoapType;
import com.exacttarget.fuelsdk.internal.APIObject;
import com.exacttarget.fuelsdk.internal.APIProperty;
import com.exacttarget.fuelsdk.internal.Account;
import com.exacttarget.fuelsdk.internal.AccountTypeEnum;
import com.exacttarget.fuelsdk.internal.BounceEvent;
import com.exacttarget.fuelsdk.internal.ClickEvent;
import com.exacttarget.fuelsdk.internal.ContentArea;
import com.exacttarget.fuelsdk.internal.DataExtension;
import com.exacttarget.fuelsdk.internal.DataExtensionField;
import com.exacttarget.fuelsdk.internal.DataExtensionFieldType;
import com.exacttarget.fuelsdk.internal.DataExtensionObject;
import com.exacttarget.fuelsdk.internal.DataFolder;
import com.exacttarget.fuelsdk.internal.DataSourceTypeEnum;
import com.exacttarget.fuelsdk.internal.DeliveryProfile;
import com.exacttarget.fuelsdk.internal.DeliveryProfileDomainTypeEnum;
import com.exacttarget.fuelsdk.internal.DeliveryProfileSourceAddressTypeEnum;
import com.exacttarget.fuelsdk.internal.Email;
import com.exacttarget.fuelsdk.internal.EmailSendDefinition;
import com.exacttarget.fuelsdk.internal.EmailType;
import com.exacttarget.fuelsdk.internal.EventType;
import com.exacttarget.fuelsdk.internal.FilterPart;
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
import com.exacttarget.fuelsdk.model.ETList;
import com.exacttarget.fuelsdk.model.ETListClassification;
import com.exacttarget.fuelsdk.model.ETListSubscriber;
import com.exacttarget.fuelsdk.model.ETListType;
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

public abstract class ETSoapObjectImmutable extends ETObject {
    private static Logger logger = Logger.getLogger(ETSoapObjectImmutable.class);

    @InternalSoapField(name = "id")
    private Integer id = null;
    @InternalSoapField(name = "customerKey")
    private String customerKey = null;
    @InternalSoapField(name = "createdDate")
    private Date createdDate = null;
    @InternalSoapField(name = "modifiedDate")
    private Date modifiedDate = null;

    public class ExternalObjectConverter implements Converter {
        @SuppressWarnings("rawtypes")
        public Object convert(Class type, Object value) {
            ETSoapObjectImmutable externalObject = null;
            try {
                externalObject = (ETSoapObjectImmutable) type.newInstance();
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
                internalObject = ((ETSoapObjectImmutable) value).toInternal();
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

    public ETSoapObjectImmutable() {
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

    protected static <T extends ETSoapObjectImmutable> ETResponse<T> retrieve(ETClient client,
                                                                              FilterPart filter,
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

        Class<T> externalClass = type; // for code readability

        //
        // Use the @InternalSoapType annotation to determine internalType:
        //

        InternalSoapType internalClassAnnotation
            = externalClass.getAnnotation(InternalSoapType.class);
        assert internalClassAnnotation != null;
        Class<? extends APIObject> internalClass = internalClassAnnotation.type();
        assert internalClass != null;

        //
        // Convert external property names to internal property names:
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
                        ETSoapObjectImmutable.getInternalProperty(externalClass, externalProperty);
                assert internalProperty != null;
                internalProperties.add(internalProperty);
            }
        } else {
            //
            // No properties were explicitly requested:
            //

            internalProperties = ETSoapObjectImmutable.getInternalProperties(externalClass);
        }

        //
        // Automatically refresh the token if necessary:
        //

        client.refreshToken();

        //
        // Perform the SOAP retrieve:
        //

        Soap soap = client.getSoapConnection().getSoap();

        RetrieveRequest retrieveRequest = new RetrieveRequest();
        retrieveRequest.setObjectType(internalClass.getSimpleName());
        retrieveRequest.getProperties().addAll(internalProperties);
        if (filter != null) {
            retrieveRequest.setFilter(filter);
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
            // XXX print filter too
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
                externalObject = externalClass.newInstance();
            } catch (Exception ex) {
                throw new ETSdkException("could not instantiate "
                        + externalClass.getName(), ex);
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

    public ETSoapObjectImmutable fromInternal(APIObject internalObject)
        throws ETSdkException
    {
        ETSoapObjectImmutable externalObject = this;

        Class<? extends ETSoapObjectImmutable> externalClass = getClass();
        String externalClassName = externalClass.getSimpleName();
        Class<? extends APIObject> internalClass = internalObject.getClass();
        String internalClassName = internalClass.getSimpleName();

        logger.trace("converting object from internal class "
                + internalClassName);
        logger.trace("                    to external class "
                + externalClassName);

        for (Field externalField : getAllFields(externalClass)) {
            String externalFieldName = externalField.getName();

            InternalSoapField internalFieldAnnotation
                = externalField.getAnnotation(InternalSoapField.class);
            if (internalFieldAnnotation != null) {
                String internalFieldName = internalFieldAnnotation.name();

                Object internalFieldValue = null;
                try {
                    internalFieldValue =
                            PropertyUtils.getProperty(internalObject,
                                                      internalFieldName);
                } catch (Exception ex) {
                    // XXX test this
                    throw new ETSdkException("could not get property \""
                            + internalFieldName
                            + "\" of object "
                            + internalObject, ex);
                }

                if (internalFieldValue == null) {
                    continue;
                }

                if (internalFieldValue instanceof List) {
                    externalField.setAccessible(true);

                    List<ETSoapObjectImmutable> externalList = new ArrayList<ETSoapObjectImmutable>();
                    @SuppressWarnings("unchecked")
                    List<APIObject> internalList
                        = (List<APIObject>) internalFieldValue;

                    Type fieldType = externalField.getGenericType();
                    assert fieldType instanceof ParameterizedType;
                    ParameterizedType parameterizedType
                        = (ParameterizedType) fieldType;
                    assert parameterizedType.getActualTypeArguments().length == 1;
                    Class<?> externalItemClass
                        = (Class<?>) parameterizedType.getActualTypeArguments()[0];

                    for (APIObject internalItem : internalList) {
                        ETSoapObjectImmutable externalItem = null;
                        try {
                            externalItem = (ETSoapObjectImmutable) externalItemClass.newInstance();
                        } catch (Exception ex) {
                            throw new ETSdkException("could not instantiate "
                                    + externalItemClass.getName(), ex);
                        }
                        externalList.add(externalItem.fromInternal(internalItem));
                    }

                    try {
                        externalField.set(externalObject, externalList);
                    } catch (Exception ex) {
                        throw new ETSdkException("could not set field \""
                                + externalFieldName
                                + "\" of object "
                                + externalObject, ex);
                    }

                    continue;
                }

                try {
                    BeanUtils.setProperty(externalObject,
                                          externalFieldName,
                                          internalFieldValue);
                } catch (Exception ex) {
                    // XXX test this
                    throw new ETSdkException("could not set property \""
                            + externalFieldName
                            + "\" of object "
                            + externalObject, ex);
                }

                if (logger.isTraceEnabled()) {
                    Field internalField = getField(internalClass,
                                                   internalFieldName);

                    Object externalFieldValue = null;
                    try {
                        externalFieldValue =
                                PropertyUtils.getProperty(externalObject,
                                                          externalFieldName);
                    } catch (Exception ex) {
                        // XXX test this
                        throw new ETSdkException("could not get property \""
                                + externalFieldName
                                + "\" of object "
                                + externalObject, ex);
                    }

                    logger.trace("  converted field "
                            + internalClassName + "." + internalFieldName
                            + " (type="
                            + internalField.getType().getSimpleName()
                            + ", value="
                            + internalFieldValue
                            + ")");
                    logger.trace("         to field "
                            + externalClassName + "." + externalFieldName
                            + " (type="
                            + externalField.getType().getSimpleName()
                            + ", value="
                            + externalFieldValue
                            + ")");
                }
            }
        }

        return externalObject;
    }

    public APIObject toInternal()
        throws ETSdkException
    {
        ETSoapObjectImmutable externalObject = this;

        Class<? extends ETSoapObjectImmutable> externalClass = getClass();

        //
        // Use the @InternalSoapType annotation to determine internalType:
        //

        InternalSoapType internalClassAnnotation
            = externalClass.getAnnotation(InternalSoapType.class);
        assert internalClassAnnotation != null;
        Class<? extends APIObject> internalClass = internalClassAnnotation.type();
        assert internalClass != null;

        String internalClassName = internalClass.getSimpleName();
        String externalClassName = externalClass.getSimpleName();

        logger.trace("converting object from external class "
                + externalClassName);
        logger.trace("                    to internal class "
                + internalClassName);

        APIObject internalObject = null;
        try {
            internalObject = internalClass.newInstance();
        } catch (Exception ex) {
            throw new ETSdkException("could not instantiate "
                    + internalClass.getName(), ex);
        }

        for (Field externalField : getAllFields(externalClass)) {
            String externalFieldName = externalField.getName();

            InternalSoapField internalFieldAnnotation
                = externalField.getAnnotation(InternalSoapField.class);
            if (internalFieldAnnotation != null) {
                String internalFieldName = internalFieldAnnotation.name();

                Object externalFieldValue = null;
                try {
                    externalFieldValue =
                            PropertyUtils.getProperty(externalObject,
                                                      externalFieldName);
                } catch (Exception ex) {
                    // XXX test this
                    throw new ETSdkException("could not get property \""
                            + externalFieldName
                            + "\" of object "
                            + externalObject, ex);
                }

                if (externalFieldValue == null) {
                    continue;
                }

                if (externalFieldValue instanceof List) {
                    Field internalField = getField(internalClass,
                                                   internalFieldName);

                    internalField.setAccessible(true);

                    List<APIObject> internalList = new ArrayList<APIObject>();
                    @SuppressWarnings("unchecked")
                    List<ETSoapObjectImmutable> externalList
                        = (List<ETSoapObjectImmutable>) externalFieldValue;

                    for (ETSoapObjectImmutable externalItem : externalList) {
                        internalList.add(externalItem.toInternal());
                    }

//                    // XXX cleanup
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
//                    } else {
                        try {
                            internalField.set(internalObject, internalList);
                        } catch (Exception ex) {
                            throw new ETSdkException("could not set field \""
                                    + internalFieldName
                                    + "\" of object "
                                    + internalObject, ex);
                        }
//                    }

                    continue;
                }

                try {
                    BeanUtils.setProperty(internalObject,
                                          internalFieldName,
                                          externalFieldValue);
                } catch (Exception ex) {
                    // XXX test this
                    throw new ETSdkException("could not set property \""
                            + internalFieldName
                            + "\" of object "
                            + internalObject, ex);
                }

                if (logger.isTraceEnabled()) {
                    Field internalField = getField(internalClass,
                                                   internalFieldName);

                    Object internalFieldValue = null;
                    try {
                        internalFieldValue =
                                PropertyUtils.getProperty(internalObject,
                                                          internalFieldName);
                    } catch (Exception ex) {
                        // XXX test this
                        throw new ETSdkException("could not get property \""
                                + internalFieldName
                                + "\" of object "
                                + internalObject, ex);
                    }

                    logger.trace("  converted field "
                            + externalClassName + "." + externalFieldName
                            + " (type="
                            + externalField.getType().getSimpleName()
                            + ", value="
                            + externalFieldValue
                            + ")");
                    logger.trace("         to field "
                            + internalClassName + "." + internalFieldName
                            + " (type="
                            + internalField.getType().getSimpleName()
                            + ", value="
                            + internalFieldValue
                            + ")");
                }
            }
        }

        return internalObject;
    }

    public static String getInternalProperty(Class<? extends ETSoapObjectImmutable> type,
                                             String externalProperty)
        throws ETSdkException
    {
        Class<? extends ETSoapObjectImmutable> externalClass = type; // for code readability

        //
        // Use the @InternalSoapType annotation to determine internalType:
        //

        InternalSoapType internalClassAnnotation
            = externalClass.getAnnotation(InternalSoapType.class);
        assert internalClassAnnotation != null;
        Class<? extends APIObject> internalClass = internalClassAnnotation.type();
        assert internalClass != null;

        Field externalField = null;
        try {
            externalField = getField(externalClass, externalProperty);
        } catch (ETSdkException ex) {
            throw new ETSdkException(externalProperty + ": invalid property", ex);
        }

        return getInternalProperty(internalClass, externalField);
    }

    public static List<String> getInternalProperties(Class<? extends ETSoapObjectImmutable> type)
        throws ETSdkException
    {
        List<String> internalProperties = new ArrayList<String>();

        Class<? extends ETSoapObjectImmutable> externalClass = type; // for code readability

        //
        // Use the @InternalSoapType annotation to determine internalType:
        //

        InternalSoapType internalClassAnnotation
            = externalClass.getAnnotation(InternalSoapType.class);
        assert internalClassAnnotation != null;
        Class<? extends APIObject> internalClass = internalClassAnnotation.type();
        assert internalClass != null;

        //
        // Build a list of fields from externalType and all superclasses:
        //

        List<Field> externalFields = getAllFields(externalClass);

        //
        // Walk the list of external fields building a list of the
        // corresponding property names using @InternalSoapField:
        //

        for (Field externalField : externalFields) {
            String internalProperty = getInternalProperty(internalClass,
                                                          externalField);
            assert internalProperty != null;
            internalProperties.add(internalProperty);
        }

        internalProperties.removeAll(Arrays.asList(internalClassAnnotation.ignoredFields()));

        return internalProperties;
    }

    private static String getInternalProperty(Class<? extends APIObject> type,
                                              Field externalField)
        throws ETSdkException
    {
        String internalProperty = null;

        Class<? extends APIObject> internalClass = type; // for code readability

        InternalSoapField internalFieldAnnotation
            = externalField.getAnnotation(InternalSoapField.class);
        if (internalFieldAnnotation != null) {
            internalProperty = internalFieldAnnotation.serializedName();

            if (internalProperty.isEmpty()) {
                //
                // There is no property name specified
                // in the annotation so look at the values of
                // @XmlElement or @XmlElementRef on the corresponding
                // internal field of the CXF generated class:
                //

                String internalFieldName = internalFieldAnnotation.name();

                Field internalField = getField(internalClass,
                                               internalFieldName);

                XmlElement element =
                        internalField.getAnnotation(XmlElement.class);
                if (element != null) {
                    internalProperty = element.name();
                } else {
                    // optional dateTimes are annotated with XmlElementRef
                    XmlElementRef elementRef =
                            internalField.getAnnotation(XmlElementRef.class);
                    if (elementRef != null) {
                        internalProperty = elementRef.name();
                    }
                }
            }
        }

        return internalProperty;
    }

    private static Field getField(Class<?> type, String name)
        throws ETSdkException
    {
        // make sure superclass fields are first, to
        // enhance readability of the SOAP envelopes

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

        List<Class<?>> types = new ArrayList<Class<?>>();
        for (Class<?> t = type; t != null; t = t.getSuperclass()) {
            types.add(t);
        }
        ListIterator<Class<?>> li = types.listIterator(types.size());
        while (li.hasPrevious()) {
            Class<?> t = li.previous();
            for (Field field : t.getDeclaredFields()) {
                fields.add(field);
            }
        }

        return fields;
    }

    protected void toStringOpen() {
        toStringReset();
        toStringAppend(getClass().getName());
        toStringAppend("[");
        toStringAppend("id", getId());
        toStringAppend("key", getCustomerKey());
        toStringAppend("createdDate", getCreatedDate());
        toStringAppend("modifiedDate", getModifiedDate());
    }

    protected void toStringClose() {
        toStringAppend("]", false); // don't want a new line here
    }
}
