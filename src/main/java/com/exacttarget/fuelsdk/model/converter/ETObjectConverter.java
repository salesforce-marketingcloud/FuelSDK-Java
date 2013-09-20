//
// ETObjectConverter.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// @COPYRIGHT@
//

package com.exacttarget.fuelsdk.model.converter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.Converter;

import com.exacttarget.fuelsdk.internal.APIProperty;
import com.exacttarget.fuelsdk.internal.DataExtension;
import com.exacttarget.fuelsdk.internal.DataExtension.Fields;
import com.exacttarget.fuelsdk.internal.DataExtensionField;
import com.exacttarget.fuelsdk.internal.DataExtensionObject.Keys;
import com.exacttarget.fuelsdk.internal.ObjectExtension;
import com.exacttarget.fuelsdk.model.ETDataExtensionColumn;
import com.exacttarget.fuelsdk.model.ETObject;

public class ETObjectConverter implements Converter{

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object convert(Class type, Object value) {
		try {

			if (type == DataExtension.Fields.class) {
				DataExtension.Fields fields = new Fields();
	            List<ETDataExtensionColumn> columns = (List<ETDataExtensionColumn>) value;
	            for(ETDataExtensionColumn col : columns) {
	            	fields.getField().add(ObjectConverter.convertFromEtObject(col, DataExtensionField.class, false));
	            }

	            return fields;
			} else if (type == Keys.class) {

				Keys keys = new Keys();
                Map<String, String> columns = (Map<String, String>) value;
                for(String key : columns.keySet()) {
                	APIProperty property = new APIProperty();
                	property.setName(key);
                	property.setValue(columns.get(key));
                	keys.getKey().add(property);
                }

                return keys;
			} else if (type == ObjectExtension.Properties.class) {
				ObjectExtension.Properties properties = new ObjectExtension.Properties();

                Map<String, String> columns = (Map<String, String>) value;

                for(String key : columns.keySet()) {
                	APIProperty property = new APIProperty();
                	property.setName(key);
                	property.setValue(columns.get(key));

                	properties.getProperty().add(property);
                }

                return properties;
			} else if (type == Map.class) {
				ObjectExtension.Properties properties = (ObjectExtension.Properties) value;

                Map<String, String> columns = new HashMap<String, String>();

                for (APIProperty property : properties.getProperty()) {
                	columns.put(property.getName(), property.getValue());
                }

                return columns;
			}

            return ObjectConverter.convertFromEtObject((ETObject) value, type, false);
        }
        catch(Exception e) {
            return null;
        }
	}

}
