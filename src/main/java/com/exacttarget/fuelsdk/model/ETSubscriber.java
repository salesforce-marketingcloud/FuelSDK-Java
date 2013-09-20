//
// ETSubscriber.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// @COPYRIGHT@
//

package com.exacttarget.fuelsdk.model;

import java.util.Date;
import java.util.List;

import com.exacttarget.fuelsdk.annotations.InternalSoapField;
import com.exacttarget.fuelsdk.annotations.InternalSoapType;
import com.exacttarget.fuelsdk.internal.Subscriber;

@InternalSoapType(type = Subscriber.class, ignoredFields = {"CustomerKey", "ModifiedDate", "Lists"})
public class ETSubscriber extends BaseSoapSerializableObject implements ETObject {

	@InternalSoapField(name="emailAddress")
	private String emailAddress;

	@InternalSoapField(name="subscriberKey")
	private String subscriberKey;

    @InternalSoapField(name="unsubscribedDate")
    private Date unsubscribedDate;

    @InternalSoapField(name="status")
    private ETSubscriberStatus status;

    @InternalSoapField(name="emailTypePreference")
    private ETEmailType emailTypePreference;

    @InternalSoapField(name="lists")
    private List<ETSubscriberList> lists;

    public ETSubscriber() {}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getSubscriberKey() {
		return subscriberKey;
	}

	public void setSubscriberKey(String subscriberKey) {
		this.subscriberKey = subscriberKey;
	}

	public Date getUnsubscribedDate() {
		return unsubscribedDate;
	}

	public void setUnsubscribedDate(Date unsubscribedDate) {
		this.unsubscribedDate = unsubscribedDate;
	}

	public ETSubscriberStatus getStatus() {
		return status;
	}

	public void setStatus(ETSubscriberStatus status) {
		this.status = status;
	}

	public ETEmailType getEmailTypePreference() {
		return emailTypePreference;
	}

	public void setEmailTypePreference(ETEmailType emailTypePreference) {
		this.emailTypePreference = emailTypePreference;
	}

	public List<ETSubscriberList> getLists() {
		return lists;
	}

	public void setLists(List<ETSubscriberList> lists) {
		this.lists = lists;
	}

	@Override
	public String toString() {
		return "ETSubscriber [emailAddress=" + emailAddress
				+ ", subscriberKey=" + subscriberKey + ", unsubscribedDate="
				+ unsubscribedDate + ", status=" + status
				+ ", emailTypePreference=" + emailTypePreference + ", lists="
				+ lists + ", id=" + id + ", createdDate=" + createdDate
				+ ", modifiedDate=" + modifiedDate + ", customerKey="
				+ customerKey + "]";
	}


}