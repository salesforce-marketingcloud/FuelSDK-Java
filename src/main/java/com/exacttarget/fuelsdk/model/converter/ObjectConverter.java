package com.exacttarget.fuelsdk.model.converter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.datatype.XMLGregorianCalendar;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
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
                    return ETEmailType.valueOf((value).toString());
                }
                return null;
            }
        }, ETEmailType.class);

        ConvertUtils.register(new Converter() {
            public Object convert(Class type, Object value) {
                if (value == null) return null;
                if (type == SubscriberStatus.class) {
                    return ETSubscriberStatus.valueOf((value).toString());
                }
                return null;
            }
        }, ETSubscriberStatus.class);

        ConvertUtils.register(new Converter() {
            public Object convert(Class type, Object value) {
                if (value == null) return null;
                if (type == ListClassificationEnum.class) {
                    return ETListClassification.valueOf((value).toString());
                }
                return null;
            }
        }, ETListClassification.class);

        ConvertUtils.register(new Converter() {
            public Object convert(Class type, Object value) {
                if (value == null) return null;
                if (type == ListTypeEnum.class) {
                    return ETListType.valueOf((value).toString());
                }
                return null;
            }
        }, ETListType.class);
        
        ConvertUtils.register(new Converter() {
            public Object convert(Class type, Object value) {
                if (value == null) return null;
                if (type == LayoutType.class) {
                    return ETLayoutType.valueOf((value).toString());
                }
                return null;
            }
        }, ETLayoutType.class);
        
        

        // By default, IntegerConverter sets nulls as 0
        ConvertUtils.register(new IntegerConverter(null), Integer.class);
    }

    public static <T extends ETObject> T convertToEtObject(APIObject o, Class<T> toType)
        throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        // Convert o to ETObject type by examining toType's @InternalField annotations
        T out = toType.newInstance();

        for(Map.Entry<String, String> props : createInternalToETPropertyMap(new HashMap<String, String>(), toType).entrySet()) {
        	String toProp = props.getKey().equals("id") ? "ID" : props.getKey();
            BeanUtils.setProperty(out, props.getValue(), PropertyUtils.getProperty(o, toProp));
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
        
        java.util.List<Field> fields = new ArrayList<Field>(Arrays.asList(type.getDeclaredFields()));
        if (null != type.getSuperclass()) {
        	Class superType = type.getSuperclass();
        	fields.addAll(Arrays.asList(superType.getDeclaredFields()));
        }

        for(Field declared : fields) {
            InternalField propAnnotation = declared.getAnnotation(InternalField.class);
            if(propAnnotation != null) {
                // This field has an @InternalField annotation, let's find the corresponding property in the APIObject class
                Field internalField;
                try {
                	internalField = internalType.getDeclaredField(propAnnotation.name());
                } catch(NoSuchFieldException ex) {
                	internalField = internalType.getSuperclass().getDeclaredField(propAnnotation.name());
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
