//
// BaseTrackingEvent.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// @COPYRIGHT@
//

package com.exacttarget.fuelsdk.model;

import java.util.Date;

import com.exacttarget.fuelsdk.annotations.InternalSoapField;

public abstract class BaseTrackingEvent extends ETSoapObject {

	@InternalSoapField(name="sendID")
	 private Integer sendId;

	 @InternalSoapField(name="subscriberKey")
	 private String subscriberKey;

	 @InternalSoapField(name="eventDate")
	 private Date eventDate;

	 @InternalSoapField(name="eventType")
	 private ETEventType eventType;

	 @InternalSoapField(name="batchID")
	 private Integer batchId;

	 @InternalSoapField(name="triggeredSendDefinitionObjectID")
	 private Integer triggeredSendDefinitionObjectId;

	 //@InternalSoapField(name="partnerKey")
	 private String partnerKey;

	public Integer getSendId() {
		return sendId;
	}

	public void setSendId(Integer sendId) {
		this.sendId = sendId;
	}

	public String getSubscriberKey() {
		return subscriberKey;
	}

	public void setSubscriberKey(String subscriberKey) {
		this.subscriberKey = subscriberKey;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public ETEventType getEventType() {
		return eventType;
	}

	public void setEventType(ETEventType eventType) {
		this.eventType = eventType;
	}

	public Integer getBatchId() {
		return batchId;
	}

	public void setBatchId(Integer batchId) {
		this.batchId = batchId;
	}

	public Integer getTriggeredSendDefinitionObjectId() {
		return triggeredSendDefinitionObjectId;
	}

	public void setTriggeredSendDefinitionObjectId(
			Integer triggeredSendDefinitionObjectId) {
		this.triggeredSendDefinitionObjectId = triggeredSendDefinitionObjectId;
	}

	public String getPartnerKey() {
		return partnerKey;
	}

	public void setPartnerKey(String partnerKey) {
		this.partnerKey = partnerKey;
	}

	@Override
	public String toString() {
		return "BaseTrackingEvent [sendId=" + sendId + ", subscriberKey="
				+ subscriberKey + ", eventDate=" + eventDate + ", eventType="
				+ eventType + ", batchId=" + batchId
				+ ", triggeredSendDefinitionObjectId="
				+ triggeredSendDefinitionObjectId + ", partnerKey="
				+ partnerKey + ", id=" + id + ", createdDate=" + createdDate
				+ ", modifiedDate=" + modifiedDate + ", customerKey="
				+ customerKey + "]";
	}


}
