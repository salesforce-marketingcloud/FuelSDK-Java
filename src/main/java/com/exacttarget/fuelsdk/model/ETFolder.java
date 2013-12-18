//
// ETFolder.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// @COPYRIGHT@
//

package com.exacttarget.fuelsdk.model;

import com.exacttarget.fuelsdk.ETObject;
import com.exacttarget.fuelsdk.annotations.InternalSoapField;
import com.exacttarget.fuelsdk.annotations.InternalSoapType;
import com.exacttarget.fuelsdk.internal.DataFolder;

@InternalSoapType(type = DataFolder.class)
public class ETFolder extends ETSoapObject implements ETObject {
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
