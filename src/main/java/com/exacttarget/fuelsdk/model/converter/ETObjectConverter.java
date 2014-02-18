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

package com.exacttarget.fuelsdk.model.converter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.Converter;

import com.exacttarget.fuelsdk.ETObject;
import com.exacttarget.fuelsdk.internal.APIProperty;
import com.exacttarget.fuelsdk.internal.DataExtension;
import com.exacttarget.fuelsdk.internal.DataExtension.Fields;
import com.exacttarget.fuelsdk.internal.DataExtensionField;
import com.exacttarget.fuelsdk.internal.DataExtensionObject.Keys;
import com.exacttarget.fuelsdk.internal.ObjectExtension;
import com.exacttarget.fuelsdk.model.ETDataExtensionColumn;

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
