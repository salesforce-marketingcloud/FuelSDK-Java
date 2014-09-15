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
import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETResponse;
import com.exacttarget.fuelsdk.ETRestObjectImmutable;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.annotations.RestAnnotations;

@RestAnnotations(path = "/internal/v1/AudienceBuilder/Dimension/{id}",
                 primaryKey = "id",
                 collectionKey = "entities")
public class ETDimension extends ETRestObjectImmutable {
    public class Value {
        @SerializedName("pK")
        @Expose
        private String key = null;
        @Expose
        private String name = null;
        @Expose
        private Integer count = null;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }
    }

    @SerializedName("dimensionID")
    @Expose
    private String id = null;
    @Expose
    private String name = null;
    @SerializedName("attrCustomObjectID")
    @Expose
    private String attrCustomObjectId = null;
    @SerializedName("attrCustomObjectFieldID")
    @Expose
    private String attrCustomObjectFieldId = null;
    @Expose
    private Integer attrCustomObjectFieldType = null;
    @SerializedName("customObjectID")
    @Expose
    private String customObjectId = null;
    @Expose
    private String customObjectName = null;
    @SerializedName("customObjectFieldID")
    @Expose
    private String customObjectFieldId = null;
    @Expose
    private String customObjectFieldName = null;
    @Expose
    private Integer dataType = null;
    @Expose
    private String displayName = null;
    @SerializedName("fkDataProfileAttribID")
    @Expose
    private Integer fkDataProfileAttribID = null;
    @Expose
    private Integer recordCount = null;
    @Expose
    private Integer type = null;
    @Expose
    private List<Value> values = null;

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

    public String getAttrCustomObjectId() {
        return attrCustomObjectId;
    }

    public void setAttrCustomObjectId(String attrCustomObjectId) {
        this.attrCustomObjectId = attrCustomObjectId;
    }

    public String getAttrCustomObjectFieldId() {
        return attrCustomObjectFieldId;
    }

    public void setAttrCustomObjectFieldId(String attrCustomObjectFieldId) {
        this.attrCustomObjectFieldId = attrCustomObjectFieldId;
    }

    public Integer getAttrCustomObjectFieldType() {
        return attrCustomObjectFieldType;
    }

    public void setAttrCustomObjectFieldType(Integer attrCustomObjectFieldType) {
        this.attrCustomObjectFieldType = attrCustomObjectFieldType;
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

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Integer getFkDataProfileAttribID() {
        return fkDataProfileAttribID;
    }

    public void setFkDataProfileAttribID(Integer fkDataProfileAttribID) {
        this.fkDataProfileAttribID = fkDataProfileAttribID;
    }

    public Integer getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(Integer recordCount) {
        this.recordCount = recordCount;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public List<Value> getValues() {
        return values;
    }

    public void setValues(List<Value> values) {
        this.values = values;
    }

    public static ETResponse<ETDimension> retrieve(ETClient client)
        throws ETSdkException
    {
        return ETRestObjectImmutable.retrieve(client, ETDimension.class);
    }

    public static ETResponse<ETDimension> retrieve(ETClient client,
                                                   Integer page,
                                                   Integer pageSize)
        throws ETSdkException
    {
        return ETRestObjectImmutable.retrieve(client, page, pageSize, ETDimension.class);
    }

    public static ETResponse<ETDimension> retrieve(ETClient client,
                                                   String filter)
        throws ETSdkException
    {
        return ETRestObjectImmutable.retrieve(client, filter, ETDimension.class);
    }

    public static ETResponse<ETDimension> retrieve(ETClient client,
                                                   String filter,
                                                   Integer page,
                                                   Integer pageSize)
        throws ETSdkException
    {
        return ETRestObjectImmutable.retrieve(client, filter, page, pageSize, ETDimension.class);
    }
}
