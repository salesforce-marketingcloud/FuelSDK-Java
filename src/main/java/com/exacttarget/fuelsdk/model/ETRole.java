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

import java.util.List;

import com.exacttarget.fuelsdk.ETSoapObject;
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
