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

import com.exacttarget.fuelsdk.annotations.InternalClientObject;
import com.exacttarget.fuelsdk.annotations.InternalSoapField;

@InternalClientObject
public class ETClientID {

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
