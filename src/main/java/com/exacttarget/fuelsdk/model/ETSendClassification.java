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

package com.exacttarget.fuelsdk.model;

import com.exacttarget.fuelsdk.ETSoapObject;
import com.exacttarget.fuelsdk.annotations.InternalSoapField;
import com.exacttarget.fuelsdk.annotations.InternalSoapType;
import com.exacttarget.fuelsdk.internal.DeliveryProfile;
import com.exacttarget.fuelsdk.internal.SendClassification;

@InternalSoapType(type = SendClassification.class, ignoredFields = {"SenderProfile","DeliveryProfile","SendPriority","ID"})
public class ETSendClassification extends ETSoapObject {

	@InternalSoapField(name = "sendClassificationType")
    protected ETSendClassificationType sendClassificationType;

	@InternalSoapField(name = "name")
    protected String name;

	@InternalSoapField(name = "description")
    protected String description;

	@InternalSoapField(name = "senderProfile")
    protected ETSenderProfile senderProfile;

	@InternalSoapField(name = "deliveryProfile")
    protected DeliveryProfile deliveryProfile;

	@InternalSoapField(name = "sendPriority")
    protected ETSendPriority sendPriority;

	public ETSendClassification() {}

	public ETSendClassificationType getSendClassificationType() {
		return sendClassificationType;
	}

	public void setSendClassificationType(
			ETSendClassificationType sendClassificationType) {
		this.sendClassificationType = sendClassificationType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ETSenderProfile getSenderProfile() {
		return senderProfile;
	}

	public void setSenderProfile(ETSenderProfile senderProfile) {
		this.senderProfile = senderProfile;
	}

	public DeliveryProfile getDeliveryProfile() {
		return deliveryProfile;
	}

	public void setDeliveryProfile(DeliveryProfile deliveryProfile) {
		this.deliveryProfile = deliveryProfile;
	}

	public ETSendPriority getSendPriority() {
		return sendPriority;
	}

	public void setSendPriority(ETSendPriority sendPriority) {
		this.sendPriority = sendPriority;
	}

	@Override
	public String toString() {
		return "ETSendClassification [sendClassificationType="
				+ sendClassificationType + ", name=" + name + ", description="
				+ description + ", senderProfile=" + senderProfile
				+ ", deliveryProfile=" + deliveryProfile
				+ ", sendPriority=" + sendPriority + ", id=" + id + ", createdDate=" + createdDate
				+ ", modifiedDate=" + modifiedDate + ", customerKey="
				+ customerKey + "]";
	}


}
