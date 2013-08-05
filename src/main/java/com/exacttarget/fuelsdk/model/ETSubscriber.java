package com.exacttarget.fuelsdk.model;

import java.util.Date;

import com.exacttarget.fuelsdk.annotations.InternalField;
import com.exacttarget.fuelsdk.annotations.InternalType;
import com.exacttarget.fuelsdk.internal.Subscriber;

@InternalType(type = Subscriber.class)
public class ETSubscriber extends BaseSerializableObject implements ETObject {
    @InternalField(name="emailAddress")
	protected String emailAddress;
    @InternalField(name="subscriberKey")
	protected String subscriberKey;
    @InternalField(name="unsubscribedDate")
	protected Date unsubscribedDate;
    @InternalField(name="status")
	protected ETSubscriberStatus status;
    @InternalField(name="emailTypePreference")
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
		return "ETSubscriber [id=" + ID + ", emailAddress=" + emailAddress
				+ ", subscriberKey=" + subscriberKey + ", unsubscribedDate="
				+ unsubscribedDate + ", status=" + status
				+ ", emailTypePreference=" + emailTypePreference + "]";
	}

}
