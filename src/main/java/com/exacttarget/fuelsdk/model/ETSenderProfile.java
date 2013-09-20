//
// ETSenderProfile.java -
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
import com.exacttarget.fuelsdk.internal.SenderProfile;

@InternalSoapType(type = SenderProfile.class)
public class ETSenderProfile extends BaseSoapSerializableObject implements
		ETObject {

	@InternalSoapField(name = "name")
    protected String name;

	@InternalSoapField(name = "name")
	protected String description;

	@InternalSoapField(name = "name")
    protected String fromName;

	@InternalSoapField(name = "name")
    protected String fromAddress;

	public ETSenderProfile() {}

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

	public String getFromName() {
		return fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	@Override
	public String toString() {
		return "ETSenderProfile [name=" + name + ", description=" + description
				+ ", fromName=" + fromName + ", fromAddress=" + fromAddress
				+ ", id=" + id + ", createdDate=" + createdDate
				+ ", modifiedDate=" + modifiedDate + ", customerKey="
				+ customerKey + "]";
	}


}
