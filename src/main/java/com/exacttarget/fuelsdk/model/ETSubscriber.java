package com.exacttarget.fuelsdk.model;

import java.util.Date;

import com.exacttarget.fuelsdk.annotations.InternalSoapField;
import com.exacttarget.fuelsdk.annotations.InternalSoapType;
import com.exacttarget.fuelsdk.internal.Subscriber;

@InternalSoapType(type = Subscriber.class, ignoredFields = {"CustomerKey"})
public class ETSubscriber extends BaseSoapSerializableObject implements ETObject {
    @InternalSoapField(name="emailAddress")
	protected String emailAddress;
    @InternalSoapField(name="subscriberKey")
	protected String subscriberKey;
    @InternalSoapField(name="unsubscribedDate")
	protected Date unsubscribedDate;
    @InternalSoapField(name="status")
	protected ETSubscriberStatus status;
    @InternalSoapField(name="emailTypePreference")
	protected ETEmailType emailTypePreference;

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
