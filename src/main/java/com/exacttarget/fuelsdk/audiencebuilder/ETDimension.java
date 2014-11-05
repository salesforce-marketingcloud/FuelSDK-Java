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

package com.exacttarget.fuelsdk.audiencebuilder;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.exacttarget.fuelsdk.ETFilter;
import com.exacttarget.fuelsdk.ETRestObject;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.annotations.ExternalName;
import com.exacttarget.fuelsdk.annotations.RestObject;

@RestObject(path = "/internal/v1/AudienceBuilder/Dimension/{id}",
            primaryKey = "id",
            collection = "entities",
            totalCount = "totalCount")
public class ETDimension extends ETRestObject {
    @Expose @SerializedName("dimensionID")
    @ExternalName("id")
    String id = null;
    @Expose
    @ExternalName("name")
    private String name = null;
    @Expose
    @ExternalName("type")
    private Integer type = null;
    @Expose @SerializedName("customObjectFieldID")
    @ExternalName("fieldId")
    private String fieldId = null;
    @Expose @SerializedName("customObjectFieldName")
    @ExternalName("fieldName")
    private String fieldName = null;
    @Expose @SerializedName("dataType")
    @ExternalName("fieldType")
    private Integer fieldType = null;
    @Expose @SerializedName("recordCount")
    @ExternalName("count")
    private Integer count = null;
    @Expose
    @ExternalName("values")
    private List<ETDimensionValue> values = null;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public void setDescription(String description) {
    }

    public Integer getType() {
        return type;
    }

    public String getFieldId() {
        return fieldId;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Integer getFieldType() {
        return fieldType;
    }

    public Integer getCount() {
        return count;
    }

    public List<ETDimensionValue> getValues() {
        return values;
    }

    @Override
    protected String getFilterQueryParams(ETFilter filter)
        throws ETSdkException
    {
        StringBuilder stringBuilder = new StringBuilder();

        String internalProperty = null;

        if (filter == null) {
            return "";
        }

        if (filter.getProperty() != null) {
            internalProperty = getInternalProperty(ETDimension.class,
                                                   filter.getProperty());

            // convert " " to "%20" in property
            internalProperty = internalProperty.replaceAll(" ", "%20");

            stringBuilder.append(internalProperty);

            stringBuilder.append("=");
        }

        // convert " " to "%20" in all values
        List<String> values = new ArrayList<String>();
        for (String value : filter.getValues()) {
            values.add(value.replaceAll(" ", "%20"));
        }

        ETFilter.Operator operator = filter.getOperator();
        switch(operator) {
          case EQUALS:
            stringBuilder.append(values.get(0));
            break;
          case NOT_EQUALS:
            stringBuilder.append("not(" + values.get(0) + ")");
            break;
          case LESS_THAN:
            stringBuilder.append("lt(" + values.get(0) + ")");
            break;
          case LESS_THAN_OR_EQUALS:
            stringBuilder.append("lte(" + values.get(0) + ")");
            break;
          case GREATER_THAN:
            stringBuilder.append("gt(" + values.get(0) + ")");
            break;
          case GREATER_THAN_OR_EQUALS:
            stringBuilder.append("gte(" + values.get(0) + ")");
            break;
          case IN:
            stringBuilder.append("in(");
            boolean first = true;
            for (String value : values) {
                if (first) {
                    first = false;
                } else {
                    stringBuilder.append(",");
                }
                stringBuilder.append(value);
            }
            stringBuilder.append(")");
            break;
          case AND:
            stringBuilder.append(getFilterQueryParams(filter.getFilters().get(0)));
            stringBuilder.append("&");
            stringBuilder.append(getFilterQueryParams(filter.getFilters().get(1)));
            break;
          default:
            throw new ETSdkException("unsupported operator: " + operator);
        }

        return stringBuilder.toString();
    }

    /**
     * @deprecated
     * Use <code>getFieldId</code>.
     */
    @Deprecated
    public String getCustomObjectFieldId() {
        return getFieldId();
    }

    /**
     * @deprecated
     * Use <code>getFieldName</code>.
     */
    @Deprecated
    public String getCustomObjectFieldName() {
        return getFieldName();
    }

    /**
     * @deprecated
     * Use <code>getFieldType</code>.
     */
    @Deprecated
    public Integer getCustomObjectFieldType() {
        return getFieldType();
    }
}
