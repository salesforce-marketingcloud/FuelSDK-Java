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

import com.exacttarget.fuelsdk.ETObject;
import com.exacttarget.fuelsdk.annotations.InternalSoapField;
import com.exacttarget.fuelsdk.annotations.InternalSoapType;
import com.exacttarget.fuelsdk.internal.DataExtensionField;

@InternalSoapType(type = DataExtensionField.class, ignoredFields = {"DataExtension","IsCreatable","IsUpdatable","IsRetrievable","PartnerMap","Precision","Label","Description","MinLength","MinValue","MaxValue","IsViewable","IsEditable","IsRestrictedPicklist","IsSendTime","DisplayOrder","Status"})
// XXX or should this be ETSoapObject?
public class ETDataExtensionColumn extends ETObject {

	@InternalSoapField(name="name")
	private String name;

	@InternalSoapField(name="ordinal")
	private Integer ordinal;

	@InternalSoapField(name="isPrimaryKey")
	private Boolean isPrimaryKey;

	@InternalSoapField(name="fieldType")
	private ETDataExtensionFieldType fieldType;

	@InternalSoapField(name="dataExtension")
	private ETDataExtension dataExtension;

	@InternalSoapField(name="isCreatable")
	private Boolean isCreatable;

	@InternalSoapField(name="isUpdatable")
	private Boolean isUpdatable;

	@InternalSoapField(name="isRetrievable")
	private Boolean isRetrievable;

	@InternalSoapField(name="partnerMap")
	private String partnerMap;

	@InternalSoapField(name="precision")
	private Integer precision;

	@InternalSoapField(name="scale")
	private Integer scale;

	@InternalSoapField(name="label")
	private String label;

	@InternalSoapField(name="description")
	private String description;

	@InternalSoapField(name="defaultValue")
	private String defaultValue;

	@InternalSoapField(name="minLength")
	private Integer minLength;

	@InternalSoapField(name="maxLength")
	private Integer maxLength;

	@InternalSoapField(name="minValue")
	private String minValue;

	@InternalSoapField(name="maxValue")
	private String maxValue;

	@InternalSoapField(name="isRequired")
	private Boolean isRequired;

	@InternalSoapField(name="isViewable")
	private Boolean isViewable;

	@InternalSoapField(name="isEditable")
	private Boolean isEditable;

	@InternalSoapField(name="isRestrictedPicklist")
	private Boolean isRestrictedPicklist;

	@InternalSoapField(name="isSendTime")
	private Boolean isSendTime;

	@InternalSoapField(name="displayOrder")
	private Integer displayOrder;

	@InternalSoapField(name="status")
    private String status;

	public ETDataExtensionColumn() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getOrdinal() {
		return ordinal;
	}

	public void setOrdinal(Integer ordinal) {
		this.ordinal = ordinal;
	}

	public Boolean getIsPrimaryKey() {
		return isPrimaryKey;
	}

	public void setIsPrimaryKey(Boolean isPrimaryKey) {
		this.isPrimaryKey = isPrimaryKey;
	}

	public ETDataExtensionFieldType getFieldType() {
		return fieldType;
	}

	public void setFieldType(ETDataExtensionFieldType fieldType) {
		this.fieldType = fieldType;
	}

	public ETDataExtension getDataExtension() {
		return dataExtension;
	}

	public void setDataExtension(ETDataExtension dataExtension) {
		this.dataExtension = dataExtension;
	}

	public Boolean getIsCreatable() {
		return isCreatable;
	}

	public void setIsCreatable(Boolean isCreatable) {
		this.isCreatable = isCreatable;
	}

	public Boolean getIsUpdatable() {
		return isUpdatable;
	}

	public void setIsUpdatable(Boolean isUpdatable) {
		this.isUpdatable = isUpdatable;
	}

	public Boolean getIsRetrievable() {
		return isRetrievable;
	}

	public void setIsRetrievable(Boolean isRetrievable) {
		this.isRetrievable = isRetrievable;
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

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public Integer getMinLength() {
		return minLength;
	}

	public void setMinLength(Integer minLength) {
		this.minLength = minLength;
	}

	public Integer getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(Integer maxLength) {
		this.maxLength = maxLength;
	}

	public String getMinValue() {
		return minValue;
	}

	public void setMinValue(String minValue) {
		this.minValue = minValue;
	}

	public String getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(String maxValue) {
		this.maxValue = maxValue;
	}

	public Boolean getIsRequired() {
		return isRequired;
	}

	public void setIsRequired(Boolean isRequired) {
		this.isRequired = isRequired;
	}

	public Boolean getIsViewable() {
		return isViewable;
	}

	public void setIsViewable(Boolean isViewable) {
		this.isViewable = isViewable;
	}

	public Boolean getIsEditable() {
		return isEditable;
	}

	public void setIsEditable(Boolean isEditable) {
		this.isEditable = isEditable;
	}

	public Boolean getIsRestrictedPicklist() {
		return isRestrictedPicklist;
	}

	public void setIsRestrictedPicklist(Boolean isRestrictedPicklist) {
		this.isRestrictedPicklist = isRestrictedPicklist;
	}

	public Boolean getIsSendTime() {
		return isSendTime;
	}

	public void setIsSendTime(Boolean isSendTime) {
		this.isSendTime = isSendTime;
	}

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ETDataExtensionColumn [name=" + name + ", ordinal=" + ordinal
				+ ", isPrimaryKey=" + isPrimaryKey + ", fieldType=" + fieldType
				+ ", dataExtension=" + dataExtension + ", isCreatable=" + isCreatable
				+ ", isUpdatable=" + isUpdatable + ", isRetrievable="
				+ isRetrievable + ", partnerMap=" + partnerMap + ", precision="
				+ precision + ", scale=" + scale + ", label=" + label
				+ ", description=" + description + ", defaultValue="
				+ defaultValue + ", minLength=" + minLength + ", maxLength="
				+ maxLength + ", minValue=" + minValue + ", maxValue="
				+ maxValue + ", isRequired=" + isRequired + ", isViewable="
				+ isViewable + ", isEditable=" + isEditable
				+ ", isRestrictedPicklist=" + isRestrictedPicklist
				+ ", isSendTime=" + isSendTime + ", displayOrder="
				+ displayOrder + ", status=" + status + "]";
	}


}
