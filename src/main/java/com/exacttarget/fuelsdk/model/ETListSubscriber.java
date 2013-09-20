//
// ETListSubscriber.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// @COPYRIGHT@
//

package com.exacttarget.fuelsdk.model;

import com.exacttarget.fuelsdk.annotations.InternalSoapField;
import com.exacttarget.fuelsdk.annotations.InternalSoapType;
import com.exacttarget.fuelsdk.internal.ListSubscriber;

@InternalSoapType(type = ListSubscriber.class, ignoredFields = {"CustomerKey"})
public class ETListSubscriber extends ETSoapObject implements ETObject {
    @InternalSoapField(name="listID")
	protected Integer listId;
    @InternalSoapField(name="status")
	protected String status;
    @InternalSoapField(name="subscriberKey")
	protected String subscriberKey;

	public Integer getListId() {
		return listId;
	}
	public void setListId(Integer listId) {
		this.listId = listId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
    }
	public String getSubscriberKey() {
		return subscriberKey;
	}
	public void setSubscriberKey(String subscriberKey) {
		this.subscriberKey = subscriberKey;
	}

	@Override
	public String toString() {
        return "ETListSubscriber [id=" + id + ", createdDate=" + createdDate
            + ", listId=" + listId
            + ", modifiedDate=" + modifiedDate + ", status=" + status
            + ", subscriberKey=" + subscriberKey + "]";
	}
}
