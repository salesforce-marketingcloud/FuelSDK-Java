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
import com.exacttarget.fuelsdk.internal.DataExtensionField;

@InternalSoapType(type = DataExtensionField.class, ignoredFields = {
    "DataExtension",
    "Description",
    "DisplayOrder",
    "ID",
    "IsCreatable",
    "IsEditable",
    "IsRestrictedPicklist",
    "IsRetrievable",
    "IsSendTime",
    "IsUpdatable",
    "IsViewable",
    "Label",
    "MaxValue",
    "MinLength",
    "MinValue",
    "PartnerMap",
    "Precision",
    "Status"
})
public class ETDataExtensionColumn extends ETSoapObject {
    @InternalSoapField(name = "name")
    private String name = null;
    @InternalSoapField(name = "dataExtension")
    private ETDataExtension dataExtension = null;
    @InternalSoapField(name = "defaultValue")
    private String defaultValue = null;
    @InternalSoapField(name = "description")
    private String description = null;
    @InternalSoapField(name = "displayOrder")
    private Integer displayOrder = null;
    @InternalSoapField(name = "fieldType")
    private ETDataExtensionFieldType fieldType = null;
    @InternalSoapField(name = "isCreatable")
    private Boolean isCreatable = null;
    @InternalSoapField(name = "isEditable")
    private Boolean isEditable = null;
    @InternalSoapField(name = "isPrimaryKey")
    private Boolean isPrimaryKey = null;
    @InternalSoapField(name = "isRequired")
    private Boolean isRequired = null;
    @InternalSoapField(name = "isRestrictedPicklist")
    private Boolean isRestrictedPicklist = null;
    @InternalSoapField(name = "isRetrievable")
    private Boolean isRetrievable = null;
    @InternalSoapField(name = "isSendTime")
    private Boolean isSendTime = null;
    @InternalSoapField(name = "isUpdatable")
    private Boolean isUpdatable = null;
    @InternalSoapField(name = "isViewable")
    private Boolean isViewable = null;
    @InternalSoapField(name = "label")
    private String label = null;
    @InternalSoapField(name = "maxLength")
    private Integer maxLength = null;
    @InternalSoapField(name = "maxValue")
    private String maxValue = null;
    @InternalSoapField(name = "minLength")
    private Integer minLength = null;
    @InternalSoapField(name = "minValue")
    private String minValue = null;
    @InternalSoapField(name = "ordinal")
    private Integer ordinal = null;
    @InternalSoapField(name = "partnerMap")
    private String partnerMap = null;
    @InternalSoapField(name = "precision")
    private Integer precision = null;
    @InternalSoapField(name = "scale")
    private Integer scale = null;
    @InternalSoapField(name = "status")
    private String status = null;

    public ETDataExtensionColumn() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ETDataExtension getDataExtension() {
        return dataExtension;
    }

    public void setDataExtension(ETDataExtension dataExtension) {
        this.dataExtension = dataExtension;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public ETDataExtensionFieldType getFieldType() {
        return fieldType;
    }

    public void setFieldType(ETDataExtensionFieldType fieldType) {
        this.fieldType = fieldType;
    }

    public Boolean getIsCreatable() {
        return isCreatable;
    }

    public void setIsCreatable(Boolean isCreatable) {
        this.isCreatable = isCreatable;
    }

    public Boolean getIsEditable() {
        return isEditable;
    }

    public void setIsEditable(Boolean isEditable) {
        this.isEditable = isEditable;
    }

    public Boolean getIsPrimaryKey() {
        return isPrimaryKey;
    }

    public void setIsPrimaryKey(Boolean isPrimaryKey) {
        this.isPrimaryKey = isPrimaryKey;
    }

    public Boolean getIsRequired() {
        return isRequired;
    }

    public void setIsRequired(Boolean isRequired) {
        this.isRequired = isRequired;
    }

    public Boolean getIsRestrictedPicklist() {
        return isRestrictedPicklist;
    }

    public void setIsRestrictedPicklist(Boolean isRestrictedPicklist) {
        this.isRestrictedPicklist = isRestrictedPicklist;
    }

    public Boolean getIsRetrievable() {
        return isRetrievable;
    }

    public void setIsRetrievable(Boolean isRetrievable) {
        this.isRetrievable = isRetrievable;
    }

    public Boolean getIsSendTime() {
        return isSendTime;
    }

    public void setIsSendTime(Boolean isSendTime) {
        this.isSendTime = isSendTime;
    }

    public Boolean getIsUpdatable() {
        return isUpdatable;
    }

    public void setIsUpdatable(Boolean isUpdatable) {
        this.isUpdatable = isUpdatable;
    }

    public Boolean getIsViewable() {
        return isViewable;
    }

    public void setIsViewable(Boolean isViewable) {
        this.isViewable = isViewable;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(Integer maxLength) {
        this.maxLength = maxLength;
    }

    public String getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(String maxValue) {
        this.maxValue = maxValue;
    }

    public Integer getMinLength() {
        return minLength;
    }

    public void setMinLength(Integer minLength) {
        this.minLength = minLength;
    }

    public String getMinValue() {
        return minValue;
    }

    public void setMinValue(String minValue) {
        this.minValue = minValue;
    }

    public Integer getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(Integer ordinal) {
        this.ordinal = ordinal;
    }

    public String getPartnerMap() {
        return partnerMap;
    }

    public void setPartnerMap(String partnerMap) {
        this.partnerMap = partnerMap;
    }

    public Integer getPrecision() {
        return precision;
    }

    public void setPrecision(Integer precision) {
        this.precision = precision;
    }

    public Integer getScale() {
        return scale;
    }

    public void setScale(Integer scale) {
        this.scale = scale;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
