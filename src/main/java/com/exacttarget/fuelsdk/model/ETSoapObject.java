//
// ETSoapObject.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// @COPYRIGHT@
//

package com.exacttarget.fuelsdk.model;

import java.util.Date;

import com.exacttarget.fuelsdk.ETObject;
import com.exacttarget.fuelsdk.annotations.InternalSoapField;

public abstract class ETSoapObject extends ETObject {
    @InternalSoapField(name="id")
    protected Integer id = null;
    @InternalSoapField(name="customerKey")
    protected String customerKey = null;
    @InternalSoapField(name="createdDate")
    protected Date createdDate = null;
    @InternalSoapField(name="modifiedDate")
    protected Date modifiedDate = null;
    @InternalSoapField(name="client")
    protected ETClientID clientId = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomerKey() {
        return customerKey;
    }

    public void setCustomerKey(String customerKey) {
        this.customerKey = customerKey;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

	public ETClientID getClientId() {
		return clientId;
	}

	public void setClientId(ETClientID clientId) {
		this.clientId = clientId;
	}


}
