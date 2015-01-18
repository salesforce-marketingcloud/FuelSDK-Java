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

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.exacttarget.fuelsdk.ETFilter;
import com.exacttarget.fuelsdk.ETRestObject;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.annotations.ExternalName;
import com.exacttarget.fuelsdk.annotations.RestObject;

@RestObject(path = "/internal/v1/AudienceBuilder/Dimension",
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

    public static String toQueryParams(ETFilter filter)
        throws ETSdkException
    {
        return ETAudience.toQueryParams(filter);
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
