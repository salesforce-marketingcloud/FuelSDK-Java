//
// ObjectConverter.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// @COPYRIGHT@
//

package com.exacttarget.fuelsdk.model.converter;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.converters.IntegerConverter;

import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.annotations.InternalSoapField;
import com.exacttarget.fuelsdk.annotations.InternalSoapType;
import com.exacttarget.fuelsdk.internal.APIObject;
import com.exacttarget.fuelsdk.internal.AccountTypeEnum;
import com.exacttarget.fuelsdk.internal.DataExtension;
import com.exacttarget.fuelsdk.internal.DataExtensionFieldType;
import com.exacttarget.fuelsdk.internal.DataExtensionObject.Keys;
import com.exacttarget.fuelsdk.internal.DataFolder;
import com.exacttarget.fuelsdk.internal.Email;
import com.exacttarget.fuelsdk.internal.ListSubscriber;
import com.exacttarget.fuelsdk.internal.ObjectExtension;
import com.exacttarget.fuelsdk.internal.SendClassification;
import com.exacttarget.fuelsdk.internal.SubscriberList;
import com.exacttarget.fuelsdk.internal.SubscriberStatus;
import com.exacttarget.fuelsdk.model.ETAccountType;
import com.exacttarget.fuelsdk.model.ETDataExtensionColumn;
import com.exacttarget.fuelsdk.model.ETDataExtensionFieldType;
import com.exacttarget.fuelsdk.model.ETDataSourceType;
import com.exacttarget.fuelsdk.model.ETDeliveryProfile;
import com.exacttarget.fuelsdk.model.ETDeliveryProfileDomainType;
import com.exacttarget.fuelsdk.model.ETDeliveryProfileSourceAddressType;
import com.exacttarget.fuelsdk.model.ETEmail;
import com.exacttarget.fuelsdk.model.ETEmailType;
import com.exacttarget.fuelsdk.model.ETEventType;
import com.exacttarget.fuelsdk.model.ETFolder;
import com.exacttarget.fuelsdk.model.ETLayoutType;
import com.exacttarget.fuelsdk.model.ETList;
import com.exacttarget.fuelsdk.model.ETListClassification;
import com.exacttarget.fuelsdk.model.ETListSubscriber;
import com.exacttarget.fuelsdk.model.ETListType;
import com.exacttarget.fuelsdk.model.ETObject;
import com.exacttarget.fuelsdk.model.ETSalutationSource;
import com.exacttarget.fuelsdk.model.ETSendClassification;
import com.exacttarget.fuelsdk.model.ETSendClassificationType;
import com.exacttarget.fuelsdk.model.ETSendDefinitionListType;
import com.exacttarget.fuelsdk.model.ETSendPriority;
import com.exacttarget.fuelsdk.model.ETSenderProfile;
import com.exacttarget.fuelsdk.model.ETSubscriber;
import com.exacttarget.fuelsdk.model.ETSubscriberList;
import com.exacttarget.fuelsdk.model.ETSubscriberStatus;

public class ObjectConverter {
    static {
        ConvertUtilsBean convertUtils = BeanUtilsBean.getInstance().getConvertUtils();
        /// Enums
        convertUtils.register(new Converter() {
            @SuppressWarnings("rawtypes")
			public Object convert(Class arg0, Object obj) {
                if (obj == null) return null;
                if (arg0 == XMLGregorianCalendar.class) {
                    return ((XMLGregorianCalendar) obj).toGregorianCalendar().getTime();
                }
                return null;
            }
        }, Date.class);

        // Convert ET Enums
        convertUtils.register(new ETEnumConverter(), ETEmailType.class);
        convertUtils.register(new ETEnumConverter(), ETSubscriberStatus.class);
        convertUtils.register(new ETEnumConverter(), ETListClassification.class);
        convertUtils.register(new ETEnumConverter(), ETListType.class);
        convertUtils.register(new ETEnumConverter(), ETLayoutType.class);
        convertUtils.register(new ETEnumConverter(), ETEventType.class);
        convertUtils.register(new ETEnumConverter(), ETAccountType.class);
        convertUtils.register(new ETEnumConverter(), ETDataExtensionFieldType.class);
        convertUtils.register(new ETEnumConverter(), DataExtensionFieldType.class);
        convertUtils.register(new ETEnumConverter(), ETDeliveryProfileDomainType.class);
        convertUtils.register(new ETEnumConverter(), ETDeliveryProfileSourceAddressType.class);
        convertUtils.register(new ETEnumConverter(), ETSalutationSource.class);
        convertUtils.register(new ETEnumConverter(), ETSendClassificationType.class);
        convertUtils.register(new ETEnumConverter(), ETSendPriority.class);
        convertUtils.register(new ETEnumConverter(), SubscriberStatus.class);
        convertUtils.register(new ETEnumConverter(), ETDataSourceType.class);
        convertUtils.register(new ETEnumConverter(), ETSendDefinitionListType.class);
        convertUtils.register(new ETEnumConverter(), AccountTypeEnum.class);

        // Convert ET Objects
        convertUtils.register(new ETObjectConverter(), ETFolder.class);
        convertUtils.register(new ETObjectConverter(), ETSendClassification.class);
        convertUtils.register(new ETObjectConverter(), ETDeliveryProfile.class);
        convertUtils.register(new ETObjectConverter(), ETEmail.class);
        convertUtils.register(new ETObjectConverter(), ETSenderProfile.class);
        convertUtils.register(new ETObjectConverter(), ETSubscriber.class);
        convertUtils.register(new ETObjectConverter(), ETSubscriberList.class);
        convertUtils.register(new ETObjectConverter(), ETListSubscriber.class);
        convertUtils.register(new ETObjectConverter(), ETList.class);

        // Convert API Objects
        convertUtils.register(new ETObjectConverter(), DataFolder.class);
        convertUtils.register(new ETObjectConverter(), SendClassification.class);
        convertUtils.register(new ETObjectConverter(), Email.class);
        convertUtils.register(new ETObjectConverter(), ListSubscriber.class);
        convertUtils.register(new ETObjectConverter(), SubscriberList.class);
        convertUtils.register(new ETObjectConverter(), com.exacttarget.fuelsdk.internal.List.class);
        convertUtils.register(new ETObjectConverter(), DataExtension.Fields.class);
        convertUtils.register(new ETObjectConverter(), Keys.class);
        convertUtils.register(new ETObjectConverter(), ObjectExtension.Properties.class);
        convertUtils.register(new ETObjectConverter(), ETDataExtensionColumn[].class);
        convertUtils.register(new ETObjectConverter(), Map.class);


        // By default, IntegerConverter sets nulls as 0
        convertUtils.register(new IntegerConverter(null), Integer.class);
    }

    public static <T extends ETObject> T convertToEtObject(APIObject o, Class<T> toType, boolean isPatch)
        throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        // Convert o to ETObject type by examining toType's @InternalField annotations
        T out = toType.newInstance();

        for(Map.Entry<String, String> props : createInternalToETPropertyMap(new HashMap<String, String>(), toType, isPatch).entrySet()) {
            BeanUtils.setProperty(out, props.getValue(), PropertyUtils.getProperty(o, resolvePropertyName(props.getKey())));
        }

        return out;
    }

    @SuppressWarnings("unchecked")
	public static <T extends APIObject> T convertFromEtObject(ETObject o, Class<T> toType, boolean isPatch)
        throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, SecurityException, NoSuchFieldException {
        // Convert o to APIObject type by examining o's @InternalField annotations
        T out = toType.newInstance();

        for(Map.Entry<String, String> props : createInternalToETPropertyMap(new HashMap<String, String>(), o.getClass(), isPatch).entrySet()) {

        	String propertyName = resolvePropertyName(props.getKey());
        	Object prop = PropertyUtils.getProperty(out, propertyName);

        	if (null != prop && prop.getClass() == ArrayList.class) {
        		ArrayList<APIObject> propList = (ArrayList<APIObject>)prop;
        		Collection<ETObject> fromCollection = (Collection<ETObject>) PropertyUtils.getProperty(o, props.getValue());
        		if (null != fromCollection) {
	        		for (ETObject etObject : fromCollection) {
	        			propList.add(convertFromEtObject(etObject,(Class<T>) ((ParameterizedType) out.getClass().getDeclaredField(propertyName).getGenericType()).getActualTypeArguments()[0], isPatch));
	        		}
        		}

        	} else {
        		BeanUtils.setProperty(out, propertyName, PropertyUtils.getProperty(o, props.getValue()));
        	}
        }

        return out;
    }

    @SuppressWarnings("rawtypes")
	public static java.util.List<String> findSerializablePropertyNames(Class<? extends ETObject> type) throws NoSuchFieldException, ETSdkException {
        // This method would be much simpler to write if we assume all fields with @XmlElement are to be transmitted
        // We are under the current assumption that we only want to return those fields which have been explicitly marked
        InternalSoapType classAnnotation = type.getAnnotation(InternalSoapType.class);
        if(classAnnotation == null) {
            throw new ETSdkException("The type specified does not wrap an internal ET APIObject.");
        }
		Class internalType = classAnnotation.type();

        java.util.List<String> names = new java.util.ArrayList<String>();

        java.util.List<Field> fields = new ArrayList<Field>(Arrays.asList(type.getDeclaredFields()));
        if (null != type.getSuperclass()) {
			Class superType = type.getSuperclass();
        	fields.addAll(Arrays.asList(superType.getDeclaredFields()));
        }

        for(Field declared : fields) {
            InternalSoapField propAnnotation = declared.getAnnotation(InternalSoapField.class);
            if(propAnnotation != null) {
                // This field has an @InternalField annotation, let's find the corresponding property in the APIObject class
                Field internalField = null;
                if (!propAnnotation.serializedName().equals("")) {
                	names.add(propAnnotation.serializedName());
                	continue;
                }
                Class checkType = internalType;
                while (checkType != null) {
	                try {
	                	internalField = checkType.getDeclaredField(propAnnotation.name());
	                	break;
	                } catch(NoSuchFieldException ex) {
	                	checkType = checkType.getSuperclass();
	                }
                }
                if (internalField == null) {
                	throw new ETSdkException("Error inspecting serialization properties of specified type");
                }
                XmlElement element = internalField.getAnnotation(XmlElement.class);
                if(element != null) {
                    // This property is serializable, let's add it
                    names.add(element.name());
                }
                else {
                    // Optional datetimes are annotated with XmlElementRef, so let's check that as well
                    XmlElementRef elementRef = internalField.getAnnotation(XmlElementRef.class);
                    if(elementRef != null) {
                        // This property is serializable, let's add it
                        names.add(elementRef.name());
                    }
                }
            }
        }
        names.removeAll(Arrays.asList(classAnnotation.ignoredFields()));
        return names;
    }

    protected static Map<String, String> createInternalToETPropertyMap(Map<String, String> properties, Class<?> type, boolean isPatch) {
        for(Field f : type.getDeclaredFields()) {
            InternalSoapField fld = f.getAnnotation(InternalSoapField.class);
            if(fld != null && (!isPatch || !(isPatch && fld.ignoreOnPatch()))) {
                properties.put(fld.name(), f.getName());
            }
        }
        return type.getSuperclass() == null ? properties : createInternalToETPropertyMap(properties, type.getSuperclass(), isPatch);
    }

    protected static String resolvePropertyName(String beanPropertyName) {

    	if (beanPropertyName.equals("id")) {
    		return "ID";
    	} else if (beanPropertyName.equals("htmlBody")) {
    		return "HTMLBody";
    	} else if (beanPropertyName.indexOf('.') > -1) {
    		return beanPropertyName.substring(0, beanPropertyName.indexOf('.'));
    	}
    	return beanPropertyName;
    }

}
