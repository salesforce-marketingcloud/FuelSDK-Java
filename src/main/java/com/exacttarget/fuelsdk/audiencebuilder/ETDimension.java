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
import com.exacttarget.fuelsdk.ETRestObject;
import com.exacttarget.fuelsdk.annotations.ExternalName;
import com.exacttarget.fuelsdk.annotations.RestObject;

@RestObject(path = "/internal/v1/AudienceBuilder/Dimension/{id}",
            primaryKey = "id",
            collectionKey = "entities")
public class ETDimension extends ETRestObject {
    @ExternalName("id") @Expose
    @SerializedName("dimensionID")
    String id = null;
    @ExternalName("name") @Expose
    private String name = null;
    @ExternalName("displayName") @Expose
    String displayName = null;
    @ExternalName("type") @Expose
    private Integer type = null;
    @ExternalName("customObjectId") @Expose
    @SerializedName("customObjectID")
    private String customObjectId = null;
    @ExternalName("customObjectName") @Expose
    private String customObjectName = null;
    @ExternalName("customObjectFieldId") @Expose
    @SerializedName("customObjectFieldID")
    private String customObjectFieldId = null;
    @ExternalName("customObjectFieldName") @Expose
    private String customObjectFieldName = null;
    @ExternalName("customObjectFieldType") @Expose
    @SerializedName("dataType")
    private Integer customObjectFieldType = null;
    @ExternalName("values") @Expose
    private List<ETDimensionValue> values = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCustomObjectId() {
        return customObjectId;
    }

    public void setCustomObjectId(String customObjectId) {
        this.customObjectId = customObjectId;
    }

    public String getCustomObjectName() {
        return customObjectName;
    }

    public void setCustomObjectName(String customObjectName) {
        this.customObjectName = customObjectName;
    }

    public String getCustomObjectFieldId() {
        return customObjectFieldId;
    }

    public void setCustomObjectFieldId(String customObjectFieldId) {
        this.customObjectFieldId = customObjectFieldId;
    }

    public String getCustomObjectFieldName() {
        return customObjectFieldName;
    }

    public void setCustomObjectFieldName(String customObjectFieldName) {
        this.customObjectFieldName = customObjectFieldName;
    }

    public Integer getCustomObjectFieldType() {
        return customObjectFieldType;
    }

    public void setCustomObjectFieldType(Integer customObjectFieldType) {
        this.customObjectFieldType = customObjectFieldType;
    }

    public List<ETDimensionValue> getValues() {
        return values;
    }

    public void setValues(List<ETDimensionValue> values) {
        this.values = values;
    }
}
