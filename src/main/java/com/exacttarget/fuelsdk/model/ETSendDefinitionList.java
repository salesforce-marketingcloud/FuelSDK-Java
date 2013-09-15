package com.exacttarget.fuelsdk.model;

import com.exacttarget.fuelsdk.annotations.InternalSoapField;
import com.exacttarget.fuelsdk.annotations.InternalSoapType;
import com.exacttarget.fuelsdk.internal.FilterDefinition;
import com.exacttarget.fuelsdk.internal.SendDefinitionList;

@InternalSoapType(type = SendDefinitionList.class)
public class ETSendDefinitionList extends BaseSoapSerializableObject implements
		ETObject {
	
	@InternalSoapField(name = "list")
    private ETList list;
    
	@InternalSoapField(name = "sendDefinitionListType")
	private ETSendDefinitionListType sendDefinitionListType;
	
	@InternalSoapField(name = "customObjectID")
    private String customObjectID;
	
	@InternalSoapField(name = "dataSourceTypeID")
    private ETDataSourceType dataSourceTypeID;
	
	@InternalSoapField(name = "filterDefinition")
    private FilterDefinition filterDefinition;
	
	@InternalSoapField(name = "isTestObject")
    private Boolean isTestObject;
	
	@InternalSoapField(name = "salesForceObjectID")
    private String salesForceObjectID;
	
	@InternalSoapField(name = "name")
    private String name;
	
	//@InternalSoapField(name = "list")
    //private SendDefinitionList.Parameters parameters;
	
	public ETSendDefinitionList() {}

	public ETList getList() {
		return list;
	}

	public void setList(ETList list) {
		this.list = list;
	}

	public ETSendDefinitionListType getSendDefinitionListType() {
		return sendDefinitionListType;
	}

	public void setSendDefinitionListType(
			ETSendDefinitionListType sendDefinitionListType) {
		this.sendDefinitionListType = sendDefinitionListType;
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

	public String getSalesForceObjectID() {
		return salesForceObjectID;
	}

	public void setSalesForceObjectID(String salesForceObjectID) {
		this.salesForceObjectID = salesForceObjectID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "ETSendDefinitionList [list=" + list
				+ ", sendDefinitionListType=" + sendDefinitionListType
				+ ", customObjectID=" + customObjectID + ", dataSourceTypeID="
				+ dataSourceTypeID + ", filterDefinition=" + filterDefinition
				+ ", isTestObject=" + isTestObject + ", salesForceObjectID="
				+ salesForceObjectID + ", name=" + name + ", id=" + id
				+ ", createdDate=" + createdDate + ", modifiedDate="
				+ modifiedDate + ", customerKey=" + customerKey + "]";
	}
	
	

}
