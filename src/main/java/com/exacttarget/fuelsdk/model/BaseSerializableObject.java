package com.exacttarget.fuelsdk.model;

import com.exacttarget.fuelsdk.annotations.InternalField;

import java.util.Date;

public abstract class BaseSerializableObject {
    @InternalField(name="ID")
    protected Integer ID;
    @InternalField(name="createdDate")
    protected Date createdDate;
    @InternalField(name="modifiedDate")
    protected Date modifiedDate;
    @InternalField(name="customerKey")
    protected String customerKey;

    public Integer getID() {
        return ID;
    }
    public void setID(Integer id) {
        this.ID = id;
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
    public String getCustomerKey() {
        return customerKey;
    }
    public void setCustomerKey(String customerKey) {
        this.customerKey = customerKey;
    }
}
