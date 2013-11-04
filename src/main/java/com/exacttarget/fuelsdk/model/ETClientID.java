package com.exacttarget.fuelsdk.model;

import com.exacttarget.fuelsdk.annotations.InternalClientObject;
import com.exacttarget.fuelsdk.annotations.InternalSoapField;

@InternalClientObject
public class ETClientID implements ETObject {

	@InternalSoapField(name="clientID")
    protected Integer clientID;
	@InternalSoapField(name="id")
    protected Integer id;
	@InternalSoapField(name="partnerClientKey")
    protected String partnerClientKey;
	@InternalSoapField(name="userID")
    protected Integer userID;
	@InternalSoapField(name="partnerUserKey")
    protected String partnerUserKey;
	@InternalSoapField(name="createdBy")
    protected Integer createdBy;
	@InternalSoapField(name="modifiedBy")
    protected Integer modifiedBy;
	@InternalSoapField(name="enterpriseID")
    protected Long enterpriseID;
	@InternalSoapField(name="customerKey")
    protected String customerKey;
	
	public ETClientID(){}

	public Integer getClientID() {
		return clientID;
	}

	public void setClientID(Integer clientID) {
		this.clientID = clientID;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPartnerClientKey() {
		return partnerClientKey;
	}

	public void setPartnerClientKey(String partnerClientKey) {
		this.partnerClientKey = partnerClientKey;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public String getPartnerUserKey() {
		return partnerUserKey;
	}

	public void setPartnerUserKey(String partnerUserKey) {
		this.partnerUserKey = partnerUserKey;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Long getEnterpriseID() {
		return enterpriseID;
	}

	public void setEnterpriseID(Long enterpriseID) {
		this.enterpriseID = enterpriseID;
	}

	public String getCustomerKey() {
		return customerKey;
	}

	public void setCustomerKey(String customerKey) {
		this.customerKey = customerKey;
	}

	@Override
	public String toString() {
		return "ETClientID [clientID=" + clientID + ", id=" + id
				+ ", partnerClientKey=" + partnerClientKey + ", userID="
				+ userID + ", partnerUserKey=" + partnerUserKey
				+ ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy
				+ ", enterpriseID=" + enterpriseID + ", customerKey="
				+ customerKey + "]";
	}
	
	
	
}
