//
// ETPermission.java -
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
import com.exacttarget.fuelsdk.internal.Permission;

@InternalSoapType(type = Permission.class)
public class ETPermission extends ETSoapObject implements
		ETObject {

	@InternalSoapField(name="name")
    protected String name;

	@InternalSoapField(name="description")
	protected String description;

	@InternalSoapField(name="objectType")
    protected String objectType;

	@InternalSoapField(name="operation")
    protected String operation;

	@InternalSoapField(name="isShareable")
    protected Boolean isShareable;

	@InternalSoapField(name="isAllowed")
    protected Boolean isAllowed;

	@InternalSoapField(name="isDenied")
    protected Boolean isDenied;

	public ETPermission() {}

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

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public Boolean getIsShareable() {
		return isShareable;
	}

	public void setIsShareable(Boolean isShareable) {
		this.isShareable = isShareable;
	}

	public Boolean getIsAllowed() {
		return isAllowed;
	}

	public void setIsAllowed(Boolean isAllowed) {
		this.isAllowed = isAllowed;
	}

	public Boolean getIsDenied() {
		return isDenied;
	}

	public void setIsDenied(Boolean isDenied) {
		this.isDenied = isDenied;
	}

	@Override
	public String toString() {
		return "ETPermission [name=" + name + ", description=" + description
				+ ", objectType=" + objectType + ", operation=" + operation
				+ ", isShareable=" + isShareable + ", isAllowed=" + isAllowed
				+ ", isDenied=" + isDenied + ", id=" + id + ", createdDate="
				+ createdDate + ", modifiedDate=" + modifiedDate
				+ ", customerKey=" + customerKey + "]";
	}


}
