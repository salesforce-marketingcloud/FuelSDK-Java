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

import com.exacttarget.fuelsdk.annotations.ExternalName;
import com.exacttarget.fuelsdk.annotations.InternalName;
import com.exacttarget.fuelsdk.annotations.SoapObject;
import com.exacttarget.fuelsdk.internal.DataFolder;

@SoapObject(internalType = DataFolder.class)
public class ETFolder extends ETSoapObject {
    @ExternalName("name")
    private String name = null;
    @ExternalName("description")
    private String description = null;
    @ExternalName("contentType")
    private String contentType = null;
    @ExternalName("parentFolder")
    @InternalName(property = "ParentFolder.CustomerKey")
    private ETFolder parentFolder = null;
    @ExternalName("isActive")
    private Boolean isActive = null;
    @ExternalName("isEditable")
    private Boolean isEditable = null;
    @ExternalName("allowChildren")
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

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean getIsEditable() {
        return isEditable;
    }

    public void setIsEditable(Boolean isEditable) {
        this.isEditable = isEditable;
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
        toStringAppend("parentFolder", getParentFolder());
        toStringAppend("isActive", getIsActive());
        toStringAppend("isEditable", getIsEditable());
        toStringAppend("allowChildren", getAllowChildren());
        toStringClose();
        return getToString();
    }
}
