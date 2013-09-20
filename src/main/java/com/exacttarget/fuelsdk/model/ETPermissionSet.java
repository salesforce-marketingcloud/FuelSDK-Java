//
// ETPermissionSet.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// @COPYRIGHT@
//

package com.exacttarget.fuelsdk.model;

import java.util.List;

import com.exacttarget.fuelsdk.annotations.InternalSoapField;
import com.exacttarget.fuelsdk.annotations.InternalSoapType;
import com.exacttarget.fuelsdk.internal.PermissionSet;

@InternalSoapType(type=PermissionSet.class)
public class ETPermissionSet extends BaseSoapSerializableObject implements
		ETObject {

	@InternalSoapField(name="name")
    protected String name;

	@InternalSoapField(name="description")
    protected String description;

	@InternalSoapField(name="isAllowed")
    protected Boolean isAllowed;

	@InternalSoapField(name="isDenied")
    protected Boolean isDenied;

	@InternalSoapField(name="permissionSets")
    protected List<ETPermissionSet> permissionSets;

	@InternalSoapField(name="permissions")
    protected List<ETPermission> permissions;

	public ETPermissionSet() {}

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

	public List<ETPermissionSet> getPermissionSets() {
		return permissionSets;
	}

	public void setPermissionSets(List<ETPermissionSet> permissionSets) {
		this.permissionSets = permissionSets;
	}

	public List<ETPermission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<ETPermission> permissions) {
		this.permissions = permissions;
	}

	@Override
	public String toString() {
		return "ETPermissionSet [name=" + name + ", description=" + description
				+ ", isAllowed=" + isAllowed + ", isDenied=" + isDenied
				+ ", permissionSets=" + permissionSets + ", permissions="
				+ permissions + ", id=" + id + ", createdDate=" + createdDate
				+ ", modifiedDate=" + modifiedDate + ", customerKey="
				+ customerKey + "]";
	}

}
