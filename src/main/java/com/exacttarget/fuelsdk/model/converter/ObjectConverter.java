package com.exacttarget.fuelsdk.model.converter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.datatype.XMLGregorianCalendar;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.annotations.InternalField;
import com.exacttarget.fuelsdk.annotations.InternalType;
import com.exacttarget.fuelsdk.internal.*;
import com.exacttarget.fuelsdk.model.*;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.converters.IntegerConverter;

public class ObjectConverter {
    static {
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
                    return ETEmailType.valueOf(((EmailType) value).toString());
                }
                return null;
            }
        }, ETEmailType.class);

        ConvertUtils.register(new Converter() {
            public Object convert(Class type, Object value) {
                if (value == null) return null;
                if (type == SubscriberStatus.class) {
                    return ETSubscriberStatus.valueOf(((SubscriberStatus) value).toString());
                }
                return null;
            }
        }, ETSubscriberStatus.class);

        ConvertUtils.register(new Converter() {
            public Object convert(Class type, Object value) {
                if (value == null) return null;
                if (type == ListClassificationEnum.class) {
                    return ETListClassification.valueOf(((ListClassificationEnum) value).toString());
                }
                return null;
            }
        }, ETListClassification.class);

        ConvertUtils.register(new Converter() {
            public Object convert(Class type, Object value) {
                if (value == null) return null;
                if (type == ListTypeEnum.class) {
                    return ETListType.valueOf(((ListTypeEnum) value).toString());
                }
                return null;
            }
        }, ETListType.class);

        // By default, IntegerConverter sets nulls as 0
        ConvertUtils.register(new IntegerConverter(null), Integer.class);
    }

    public static <T extends ETObject> T convertToEtObject(APIObject o, Class<T> toType)
        throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        // Convert o to ETObject type by examining toType's @InternalField annotations
        T out = toType.newInstance();

        for(Map.Entry<String, String> props : createInternalToETPropertyMap(new HashMap<String, String>(), toType).entrySet()) {
            BeanUtils.setProperty(out, props.getValue(), PropertyUtils.getProperty(o, props.getKey()));
        }

        return out;
    }

    public static <T extends APIObject> T convertFromEtObject(ETObject o, Class<T> toType)
        throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        // Convert o to APIObject type by examining o's @InternalField annotations
        T out = toType.newInstance();

        for(Map.Entry<String, String> props : createInternalToETPropertyMap(new HashMap<String, String>(), o.getClass()).entrySet()) {
            BeanUtils.setProperty(out, props.getKey(), PropertyUtils.getProperty(o, props.getValue()));
        }

        return out;
    }

    public static java.util.List<String> findSerializablePropertyNames(Class<? extends ETObject> type) throws NoSuchFieldException, ETSdkException {
        // This method would be much simpler to write if we assume all fields with @XmlElement are to be transmitted
        // We are under the current assumption that we only want to return those fields which have been explicitly marked
        InternalType classAnnotation = type.getAnnotation(InternalType.class);
        if(classAnnotation == null) {
            throw new ETSdkException("The type specified does not wrap an internal ET APIObject.");
        }
        Class internalType = classAnnotation.type();

        java.util.List<String> names = new java.util.ArrayList<String>();
        for(Field declared : type.getDeclaredFields()) {
            InternalField propAnnotation = declared.getAnnotation(InternalField.class);
            if(propAnnotation != null) {
                // This field has an @InternalField annotation, let's find the corresponding property in the APIObject class
                Field internalField = internalType.getDeclaredField(propAnnotation.name());
                XmlElement element = internalField.getAnnotation(XmlElement.class);
                if(element != null) {
                    // This property is serializable, let's add it
                    names.add(element.name());
                }
            }
        }
        return names;
    }

    protected static Map<String, String> createInternalToETPropertyMap(Map<String, String> properties, Class<?> type) {
        for(Field f : type.getDeclaredFields()) {
            InternalField fld = f.getAnnotation(InternalField.class);
            if(fld != null) {
                properties.put(fld.name(), f.getName());
            }
        }
        return type.getSuperclass() == null ? properties : createInternalToETPropertyMap(properties, type.getSuperclass());
    }
}
