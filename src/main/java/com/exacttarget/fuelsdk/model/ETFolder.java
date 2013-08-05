package com.exacttarget.fuelsdk.model;

import com.exacttarget.fuelsdk.annotations.InternalField;
import com.exacttarget.fuelsdk.annotations.InternalType;
import com.exacttarget.fuelsdk.internal.DataFolder;

@InternalType(type = DataFolder.class)
public class ETFolder extends BaseSerializableObject implements ETObject {
    @InternalField(name="allowChildren")
    protected Boolean allowChildren;
    @InternalField(name="contentType")
	protected String contentType;
    @InternalField(name="description")
	protected String description;
    @InternalField(name="isActive")
	protected Boolean active;
    @InternalField(name="isEditable")
	protected Boolean editable;
    @InternalField(name="name")
	protected String name;
    @InternalField(name="parentFolder")
	protected ETFolder parentFolder;

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
	public Boolean isEditable() {
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
		return "ETFolder [id=" + ID + ", allowChildren=" + allowChildren
            + ", contentType=" + contentType + ", customerKey="
            + customerKey + ", description=" + description + ", active="
            + active + ", editable=" + editable + ", name=" + name + "]";
	}
}
