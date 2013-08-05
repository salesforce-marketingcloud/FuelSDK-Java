package com.exacttarget.fuelsdk.model;

import com.exacttarget.fuelsdk.annotations.InternalType;
import com.exacttarget.fuelsdk.internal.DataFolder;

@InternalType(type = DataFolder.class, fields = {
	"ID",
	"AllowChildren",
	"ContentType",
	"CustomerKey",
	"Description",
	"IsActive",
	"IsEditable",
	"Name",
	"ParentFolder.ID"
    })
public class ETFolder implements ETObject {

	protected Integer id;
	protected Boolean allowChildren;
	protected String contentType;
	protected String customerKey;
	protected String description;
	protected Boolean active;
	protected Boolean editable;
	protected String name;
	protected ETFolder parentFolder;
	
	public Integer getID() {
		return id;
	}

	public void setID(Integer id) {
		this.id = id;
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

	public String getCustomerKey() {
		return customerKey;
	}

	public void setCustomerKey(String customerKey) {
		this.customerKey = customerKey;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean isActive() {
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
		return "ETFolder [id=" + id + ", allowChildren=" + allowChildren
				+ ", contentType=" + contentType + ", customerKey="
				+ customerKey + ", description=" + description + ", active="
				+ active + ", editable=" + editable + ", name=" + name + "]";
	}
	
	
}
