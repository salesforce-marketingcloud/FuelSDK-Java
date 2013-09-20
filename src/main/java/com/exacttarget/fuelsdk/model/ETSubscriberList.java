//
// ETSubscriberList.java -
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
import com.exacttarget.fuelsdk.internal.SubscriberList;

@InternalSoapType(type = SubscriberList.class)
public class ETSubscriberList extends BaseSoapSerializableObject implements
		ETObject {

	@InternalSoapField(name = "status")
    private ETSubscriberStatus status;

	@InternalSoapField(name = "list")
	private ETList list;

	@InternalSoapField(name = "action")
	private String action;

	@InternalSoapField(name = "subscriber")
	private ETSubscriber subscriber;

	public ETSubscriberList() {}

	public ETSubscriberStatus getStatus() {
		return status;
	}

	public void setStatus(ETSubscriberStatus status) {
		this.status = status;
	}

	public ETList getList() {
		return list;
	}

	public void setList(ETList list) {
		this.list = list;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public ETSubscriber getSubscriber() {
		return subscriber;
	}

	public void setSubscriber(ETSubscriber subscriber) {
		this.subscriber = subscriber;
	}

	@Override
	public String toString() {
		return "ETSubscriberList [status=" + status + ", list=" + list
				+ ", action=" + action + ", subscriber=" + subscriber + ", id="
				+ id + ", createdDate=" + createdDate + ", modifiedDate="
				+ modifiedDate + ", customerKey=" + customerKey + "]";
	}



}
