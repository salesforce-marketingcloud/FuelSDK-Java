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

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.log4j.Logger;

import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETGetService;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.ETServiceResponse;
import com.exacttarget.fuelsdk.annotations.InternalType;
import com.exacttarget.fuelsdk.filter.ETFilter;
import com.exacttarget.fuelsdk.filter.ETSimpleFilter;
import com.exacttarget.fuelsdk.internal.APIObject;
import com.exacttarget.fuelsdk.internal.DataFolder;
import com.exacttarget.fuelsdk.internal.EmailType;
import com.exacttarget.fuelsdk.internal.FilterPart;
import com.exacttarget.fuelsdk.internal.List;
import com.exacttarget.fuelsdk.internal.ListClassificationEnum;
import com.exacttarget.fuelsdk.internal.ListTypeEnum;
import com.exacttarget.fuelsdk.internal.RetrieveRequest;
import com.exacttarget.fuelsdk.internal.RetrieveRequestMsg;
import com.exacttarget.fuelsdk.internal.RetrieveResponseMsg;
import com.exacttarget.fuelsdk.internal.SimpleFilterPart;
import com.exacttarget.fuelsdk.internal.SimpleOperators;
import com.exacttarget.fuelsdk.internal.Soap;
import com.exacttarget.fuelsdk.internal.SubscriberStatus;
import com.exacttarget.fuelsdk.model.ETEmailType;
import com.exacttarget.fuelsdk.model.ETFolder;
import com.exacttarget.fuelsdk.model.ETList;
import com.exacttarget.fuelsdk.model.ETListClassification;
import com.exacttarget.fuelsdk.model.ETListType;
import com.exacttarget.fuelsdk.model.ETObject;
import com.exacttarget.fuelsdk.model.ETSubscriberStatus;

public class ETGetServiceImpl
    extends ETServiceImpl implements ETGetService
{
    private static Logger logger = Logger.getLogger(ETGetServiceImpl.class);

    public <T extends ETObject> ETServiceResponse<T> get(ETClient client, Class<T> type)
        throws ETSdkException
    {
        return this.get(client, type, null);
    }

	public <T extends ETObject> ETServiceResponse<T> get(ETClient client,
			Class<T> type, ETFilter filter) throws ETSdkException {
		Soap soap = client.getSOAPConnection().getSoap();

        InternalType typeAnnotation = type.getAnnotation(InternalType.class);
        if(typeAnnotation == null) {
            throw new ETSdkException("The type specified does not wrap an internal ET APIObject.");
        }

        RetrieveRequest retrieveRequest = new RetrieveRequest();
        retrieveRequest.setObjectType(typeAnnotation.type().getSimpleName());
        retrieveRequest.getProperties().addAll(Arrays.asList(typeAnnotation.fields()));
        
        if (filter != null) {
        	
        	FilterPart filterPart = convertFilterPart(filter);
        	
            retrieveRequest.setFilter(filterPart);
        }

        RetrieveRequestMsg retrieveRequestMsg = new RetrieveRequestMsg();
        retrieveRequestMsg.setRetrieveRequest(retrieveRequest);

        RetrieveResponseMsg retrieveResponseMsg = soap.retrieve(retrieveRequestMsg);

        ETServiceResponse<T> response = new ETServiceResponseImpl<T>();
        response.setRequestId(retrieveResponseMsg.getRequestID());
        
        setupConvertUtils();
        
        
        try {
            for (APIObject apiObject : retrieveResponseMsg.getResults()) {
            	T dest = (T) type.newInstance();
            	
            	BeanUtils.copyProperties(dest, apiObject);
            	
                response.getResults().add(dest);
            }
        }
        catch (Exception ex) {
            throw new ETSdkException("Error instantiating object", ex);
        }

        return response;
	}

	protected void setupConvertUtils() {
		
		
		/// Enums
		ConvertUtils.register(new Converter() {
			
			public Object convert(Class arg0, Object obj) {
				if (obj == null) return null;
				if (arg0 == XMLGregorianCalendar.class) {
					return ((XMLGregorianCalendar) obj).toGregorianCalendar().getTime();
				}
				return null;
			}
		}, Date.class);
        
        
        ConvertUtils.register(new Converter() {
			
			public Object convert(Class type, Object value) {
				if (value == null) return null;
				if (type == EmailType.class) {
					return ETEmailType.valueOf(((EmailType)value).toString());
				}
				return null;
			}
		}, ETEmailType.class);
        
        ConvertUtils.register(new Converter() {
			
			public Object convert(Class type, Object value) {
				if (value == null) return null;
				if (type == SubscriberStatus.class) {
					return ETSubscriberStatus.valueOf(((SubscriberStatus)value).toString());
				}
				return null;
			}
		}, ETSubscriberStatus.class);
        
        ConvertUtils.register(new Converter() {
			
			public Object convert(Class type, Object value) {
				if (value == null) return null;
				if (type == ListClassificationEnum.class) {
					return ETListClassification.valueOf(((ListClassificationEnum)value).toString());
				}
				return null;
			}
		}, ETListClassification.class);
        
        ConvertUtils.register(new Converter() {
			
			public Object convert(Class type, Object value) {
				if (value == null) return null;
				if (type == ListTypeEnum.class) {
					return ETListType.valueOf(((ListTypeEnum)value).toString());
				}
				return null;
			}
		}, ETListType.class);
        
        // By default, IntegerConverter sets nulls as 0
        ConvertUtils.register(new IntegerConverter(null), Integer.class);
	}

	private FilterPart convertFilterPart(ETFilter filter) {
		FilterPart filterPart = null;
		if (filter instanceof ETSimpleFilter) {
			filterPart = new SimpleFilterPart();
			((SimpleFilterPart)filterPart).setProperty(((ETSimpleFilter) filter).getProperty());
			((SimpleFilterPart)filterPart).setSimpleOperator(SimpleOperators.valueOf(((ETSimpleFilter) filter).getOperator().toString()));
			((SimpleFilterPart)filterPart).getValue().addAll(((ETSimpleFilter) filter).getValues());
			if (null != ((ETSimpleFilter) filter).getDateValues()) {
				for(Date d : ((ETSimpleFilter) filter).getDateValues()) {
					GregorianCalendar gregorian = new GregorianCalendar();
					gregorian.setTime(d);
					try {
						((SimpleFilterPart)filterPart).getDateValue().add(DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorian));
					} catch (DatatypeConfigurationException e) {
						// TODO
					}
				}
			}
		}
		return filterPart;
	}
}
