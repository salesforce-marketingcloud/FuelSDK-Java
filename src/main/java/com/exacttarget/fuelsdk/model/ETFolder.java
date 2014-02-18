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
import com.exacttarget.fuelsdk.internal.DataFolder;

@InternalSoapType(type = DataFolder.class)
public class ETFolder extends ETSoapObject {
    @InternalSoapField(name="allowChildren")
    protected Boolean allowChildren;
    @InternalSoapField(name="contentType")
	protected String contentType;
    @InternalSoapField(name="description")
	protected String description;
    @InternalSoapField(name="isActive")
	protected Boolean active;
    @InternalSoapField(name="isEditable")
	protected Boolean editable;
    @InternalSoapField(name="name")
	protected String name;
    @InternalSoapField(name="parentFolder", serializedName="parentFolder.id")
	protected ETFolder parentFolder;

    public ETFolder() {
    	this.active = true;
    	this.editable = true;
    }

	public Boolean getAllowChildren() {
		return allowChildren;
	}
	public void setAllowChildren(Boolean allowChildren) {
		this.allowChildren = allowChildren;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ETFolder getParentFolder() {
		return parentFolder;
	}
	public void setParentFolder(ETFolder parentFolder) {
		this.parentFolder = parentFolder;
	}

	@Override
	public String toString() {
		return "ETFolder [id=" + id + ", allowChildren=" + allowChildren
            + ", contentType=" + contentType + ", customerKey="
            + customerKey + ", description=" + description + ", active="
            + active + ", editable=" + editable + ", name=" + name + "]";
	}
}
