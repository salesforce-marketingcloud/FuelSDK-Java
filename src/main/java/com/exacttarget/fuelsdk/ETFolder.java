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

package com.exacttarget.fuelsdk;

import com.exacttarget.fuelsdk.annotations.InternalSoapField;
import com.exacttarget.fuelsdk.annotations.InternalSoapType;
import com.exacttarget.fuelsdk.internal.DataFolder;

@InternalSoapType(type = DataFolder.class)
public class ETFolder extends ETSoapObject {
    @InternalSoapField(name = "name")
    private String name = null;
    @InternalSoapField(name = "description")
    private String description = null;
    @InternalSoapField(name = "contentType")
    private String contentType = null;
    @InternalSoapField(name = "parentFolder", serializedName = "ParentFolder.CustomerKey")
    private ETFolder parentFolder = null;
    @InternalSoapField(name = "isActive")
    private Boolean active = null;
    @InternalSoapField(name = "isEditable")
    private Boolean editable = null;
    @InternalSoapField(name = "allowChildren")
    private Boolean allowChildren = null;

    public ETFolder() {}

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

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getParentFolderKey() {
        if (parentFolder == null) {
            return null;
        }
        return parentFolder.getCustomerKey();
    }

    public void setParentFolderKey(String parentFolderKey) {
        if (parentFolder == null) {
            parentFolder = new ETFolder();
        }
        parentFolder.setCustomerKey(parentFolderKey);
    }

    /**
     * @deprecated
     * Use <code>getParentFolderKey()</code>.
     */
    @Deprecated
    public ETFolder getParentFolder() {
        return parentFolder;
    }

    /**
     * @deprecated
     * Use <code>setParentFolderKey()</code>.
     */
    @Deprecated
    public void setParentFolder(ETFolder parentFolder) {
        this.parentFolder = parentFolder;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getEditable() {
        return editable;
    }

    public void setEditable(Boolean editable) {
        this.editable = editable;
    }

    public Boolean getAllowChildren() {
        return allowChildren;
    }

    public void setAllowChildren(Boolean allowChildren) {
        this.allowChildren = allowChildren;
    }

    @Override
    public String toString() {
        toStringOpen();
        toStringAppend("name", getName());
        toStringAppend("description", getDescription());
        toStringAppend("contentType", getContentType());
        toStringAppend("parentFolder", getParentFolderKey());
        toStringAppend("active", getActive());
        toStringAppend("editable", getEditable());
        toStringAppend("allowChildren", getAllowChildren());
        toStringClose();
        return getToString();
    }
}
