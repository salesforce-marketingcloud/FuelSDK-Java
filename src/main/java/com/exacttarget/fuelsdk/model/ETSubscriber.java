package com.exacttarget.fuelsdk.model;

import java.util.Date;

import com.exacttarget.fuelsdk.annotations.InternalType;
import com.exacttarget.fuelsdk.internal.Subscriber;

@InternalType(type = Subscriber.class, fields = {
	"ID",
	"EmailAddress",
    "SubscriberKey",
    "UnsubscribedDate",
    "Status",
    "EmailTypePreference"})
public class ETSubscriber implements ETObject {


	protected Integer id;
	protected String emailAddress;
	protected String subscriberKey;
	protected Date unsubscribedDate;
	protected ETSubscriberStatus status;
	protected ETEmailType emailTypePreference;

	public ETSubscriber() {
	}

	public Integer getID() {
		return id;
	}

	public void setID(Integer id) {
		this.id = id;
	}

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

	@Override
	public String toString() {
		return "ETSubscriber [id=" + id + ", emailAddress=" + emailAddress
				+ ", subscriberKey=" + subscriberKey + ", unsubscribedDate="
				+ unsubscribedDate + ", status=" + status
				+ ", emailTypePreference=" + emailTypePreference + "]";
	}

}
