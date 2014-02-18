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
//

package com.exacttarget.fuelsdk.model;

import java.util.Date;

import com.exacttarget.fuelsdk.ETSoapObject;
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
