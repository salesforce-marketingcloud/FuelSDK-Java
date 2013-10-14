//
// ETSendClassification.java -
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
import com.exacttarget.fuelsdk.internal.DeliveryProfile;
import com.exacttarget.fuelsdk.internal.SendClassification;

@InternalSoapType(type = SendClassification.class, ignoredFields = {"SenderProfile","DeliveryProfile","SendPriority","ID"})
public class ETSendClassification extends ETSoapObject implements
		ETObject {

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
