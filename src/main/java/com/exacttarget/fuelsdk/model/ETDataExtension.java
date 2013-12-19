//
// ETDataExtension.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// @COPYRIGHT@
//

package com.exacttarget.fuelsdk.model;

import java.util.List;

import com.exacttarget.fuelsdk.ETSoapObject;
import com.exacttarget.fuelsdk.annotations.InternalSoapField;
import com.exacttarget.fuelsdk.annotations.InternalSoapType;
import com.exacttarget.fuelsdk.internal.DataExtension;

@InternalSoapType(type = DataExtension.class, ignoredFields={"ID", "Fields"})
public class ETDataExtension extends ETSoapObject {

	@InternalSoapField(name="name")
	private String name;

	@InternalSoapField(name="description")
	private String description;

	@InternalSoapField(name="isSendable")
	private Boolean isSendable;

	@InternalSoapField(name="isTestable")
	private Boolean isTestable;

	@InternalSoapField(name="dataRetentionPeriodLength", ignoreOnPatch=true)
	private Integer dataRetentionPeriodLength;

	@InternalSoapField(name="dataRetentionPeriodUnitOfMeasure", ignoreOnPatch=true)
	private Integer dataRetentionPeriodUnitOfMeasure;

	@InternalSoapField(name="rowBasedRetention", ignoreOnPatch=true)
	private Boolean rowBasedRetention;

	@InternalSoapField(name="resetRetentionPeriodOnImport", ignoreOnPatch=true)
	private Boolean resetRetentionPeriodOnImport;

	@InternalSoapField(name="deleteAtEndOfRetentionPeriod", ignoreOnPatch=true)
	private Boolean deleteAtEndOfRetentionPeriod;

	@InternalSoapField(name="retainUntil", ignoreOnPatch=true)
	private String retainUntil;

	@InternalSoapField(name="fields")
	private List<ETDataExtensionColumn> columns;

	@InternalSoapField(name="categoryID")
	private Long categoryID;

	@InternalSoapField(name="status")
	private String status;

	public ETDataExtension() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getIsSendable() {
		return isSendable;
	}

	public void setIsSendable(Boolean isSendable) {
		this.isSendable = isSendable;
	}

	public Boolean getIsTestable() {
		return isTestable;
	}

	public void setIsTestable(Boolean isTestable) {
		this.isTestable = isTestable;
	}

	public Integer getDataRetentionPeriodLength() {
		return dataRetentionPeriodLength;
	}

	public void setDataRetentionPeriodLength(Integer dataRetentionPeriodLength) {
		this.dataRetentionPeriodLength = dataRetentionPeriodLength;
	}

	public Integer getDataRetentionPeriodUnitOfMeasure() {
		return dataRetentionPeriodUnitOfMeasure;
	}

	public void setDataRetentionPeriodUnitOfMeasure(
			Integer dataRetentionPeriodUnitOfMeasure) {
		this.dataRetentionPeriodUnitOfMeasure = dataRetentionPeriodUnitOfMeasure;
	}

	public Boolean getRowBasedRetention() {
		return rowBasedRetention;
	}

	public void setRowBasedRetention(Boolean rowBasedRetention) {
		this.rowBasedRetention = rowBasedRetention;
	}

	public Boolean getResetRetentionPeriodOnImport() {
		return resetRetentionPeriodOnImport;
	}

	public void setResetRetentionPeriodOnImport(Boolean resetRetentionPeriodOnImport) {
		this.resetRetentionPeriodOnImport = resetRetentionPeriodOnImport;
	}

	public Boolean getDeleteAtEndOfRetentionPeriod() {
		return deleteAtEndOfRetentionPeriod;
	}

	public void setDeleteAtEndOfRetentionPeriod(Boolean deleteAtEndOfRetentionPeriod) {
		this.deleteAtEndOfRetentionPeriod = deleteAtEndOfRetentionPeriod;
	}

	public String getRetainUntil() {
		return retainUntil;
	}

	public void setRetainUntil(String retainUntil) {
		this.retainUntil = retainUntil;
	}

	public List<ETDataExtensionColumn> getColumns() {
		return columns;
	}

	public void setColumns(List<ETDataExtensionColumn> columns) {
		this.columns = columns;
	}

	public Long getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(Long categoryID) {
		this.categoryID = categoryID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ETDataExtension [name=" + name + ", description=" + description
				+ ", isSendable=" + isSendable + ", isTestable=" + isTestable
				+ ", dataRetentionPeriodLength=" + dataRetentionPeriodLength
				+ ", dataRetentionPeriodUnitOfMeasure="
				+ dataRetentionPeriodUnitOfMeasure + ", rowBasedRetention="
				+ rowBasedRetention + ", resetRetentionPeriodOnImport="
				+ resetRetentionPeriodOnImport
				+ ", deleteAtEndOfRetentionPeriod="
				+ deleteAtEndOfRetentionPeriod + ", retainUntil=" + retainUntil
				+ ", columns=" + columns + ", categoryID=" + categoryID
				+ ", status=" + status + ", id=" + id + ", createdDate="
				+ createdDate + ", modifiedDate=" + modifiedDate
				+ ", customerKey=" + customerKey + "]";
	}



}
