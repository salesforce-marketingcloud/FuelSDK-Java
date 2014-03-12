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

package com.exacttarget.fuelsdk.model;

import com.exacttarget.fuelsdk.ETSoapObject;
import com.exacttarget.fuelsdk.annotations.InternalSoapField;
import com.exacttarget.fuelsdk.annotations.InternalSoapType;
import com.exacttarget.fuelsdk.internal.FilterDefinition;
import com.exacttarget.fuelsdk.internal.SendDefinitionList;

@InternalSoapType(type = SendDefinitionList.class)
public class ETSendDefinitionList extends ETSoapObject {
    @InternalSoapField(name = "name")
    private String name = null;
    @InternalSoapField(name = "customObjectID")
    private String customObjectID = null;
    @InternalSoapField(name = "dataSourceTypeID")
    private ETDataSourceType dataSourceTypeID = null;
    @InternalSoapField(name = "filterDefinition")
    private FilterDefinition filterDefinition = null;
    @InternalSoapField(name = "isTestObject")
    private Boolean isTestObject = null;
    @InternalSoapField(name = "list")
    private ETList list = null;
    @InternalSoapField(name = "salesForceObjectID")
    private String salesForceObjectID = null;
    @InternalSoapField(name = "sendDefinitionListType")
    private ETSendDefinitionListType sendDefinitionListType = null;

    public ETSendDefinitionList() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCustomObjectID() {
        return customObjectID;
    }

    public void setCustomObjectID(String customObjectID) {
        this.customObjectID = customObjectID;
    }

    public ETDataSourceType getDataSourceTypeID() {
        return dataSourceTypeID;
    }

    public void setDataSourceTypeID(ETDataSourceType dataSourceTypeID) {
        this.dataSourceTypeID = dataSourceTypeID;
    }

    public FilterDefinition getFilterDefinition() {
        return filterDefinition;
    }

    public void setFilterDefinition(FilterDefinition filterDefinition) {
        this.filterDefinition = filterDefinition;
    }

    public Boolean getIsTestObject() {
        return isTestObject;
    }

    public void setIsTestObject(Boolean isTestObject) {
        this.isTestObject = isTestObject;
    }

    public ETList getList() {
        return list;
    }

    public void setList(ETList list) {
        this.list = list;
    }

    public String getSalesForceObjectID() {
        return salesForceObjectID;
    }

    public void setSalesForceObjectID(String salesForceObjectID) {
        this.salesForceObjectID = salesForceObjectID;
    }

    public ETSendDefinitionListType getSendDefinitionListType() {
        return sendDefinitionListType;
    }

    public void setSendDefinitionListType(ETSendDefinitionListType sendDefinitionListType) {
        this.sendDefinitionListType = sendDefinitionListType;
    }
}
