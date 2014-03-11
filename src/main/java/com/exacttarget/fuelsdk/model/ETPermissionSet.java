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
import com.exacttarget.fuelsdk.internal.PermissionSet;

@InternalSoapType(type = PermissionSet.class)
public class ETPermissionSet extends ETSoapObject {
    @InternalSoapField(name = "name")
    private String name = null;
    @InternalSoapField(name = "description")
    private String description = null;
    @InternalSoapField(name = "isAllowed")
    private Boolean isAllowed = null;
    @InternalSoapField(name = "isDenied")
    private Boolean isDenied = null;
    @InternalSoapField(name = "permissions")
    private List<ETPermission> permissions = null;
    @InternalSoapField(name = "permissionSets")
    private List<ETPermissionSet> permissionSets = null;

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

    public List<ETPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<ETPermission> permissions) {
        this.permissions = permissions;
    }

    public List<ETPermissionSet> getPermissionSets() {
        return permissionSets;
    }

    public void setPermissionSets(List<ETPermissionSet> permissionSets) {
        this.permissionSets = permissionSets;
    }
}
