//
// ETRole.java -
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
import com.exacttarget.fuelsdk.internal.Role;

@InternalSoapType(type=Role.class)
public class ETRole extends ETSoapObject {

	@InternalSoapField(name="name")
    protected String name;

	@InternalSoapField(name="description")
    protected String description;

	@InternalSoapField(name="isPrivate")
    protected Boolean isPrivate;

	@InternalSoapField(name="isSystemDefined")
    protected Boolean isSystemDefined;

	@InternalSoapField(name="forceInheritance")
    protected Boolean forceInheritance;

	@InternalSoapField(name="permissionSets")
    protected boolean permissionSets;

	@InternalSoapField(name="permissions")
	protected List<ETPermission> permissions;

	public ETRole() { }

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

	public Boolean getIsPrivate() {
		return isPrivate;
	}

	public void setIsPrivate(Boolean isPrivate) {
		this.isPrivate = isPrivate;
	}

	public Boolean getIsSystemDefined() {
		return isSystemDefined;
	}

	public void setIsSystemDefined(Boolean isSystemDefined) {
		this.isSystemDefined = isSystemDefined;
	}

	public Boolean getForceInheritance() {
		return forceInheritance;
	}

	public void setForceInheritance(Boolean forceInheritance) {
		this.forceInheritance = forceInheritance;
	}

	public boolean isPermissionSets() {
		return permissionSets;
	}

	public void setPermissionSets(boolean permissionSets) {
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
		return "ETRole [name=" + name + ", description=" + description
				+ ", isPrivate=" + isPrivate + ", isSystemDefined="
				+ isSystemDefined + ", forceInheritance=" + forceInheritance
				+ ", permissionSets=" + permissionSets + ", permissions="
				+ permissions + ", id=" + id + ", createdDate=" + createdDate
				+ ", modifiedDate=" + modifiedDate + ", customerKey="
				+ customerKey + "]";
	}

}
