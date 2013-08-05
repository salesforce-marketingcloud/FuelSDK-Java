//
// ETList.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// Author(s): Ian Murdock <imurdock@exacttarget.com>
//

package com.exacttarget.fuelsdk.model;

import java.util.Date;

import com.exacttarget.fuelsdk.annotations.InternalType;
import com.exacttarget.fuelsdk.internal.List;

@InternalType(type = List.class, fields = {"ListName", "Description"})
public class ETList implements ETObject
{
	protected Integer id;
	protected Integer categoryId;
	protected String customerKey;
    protected String name;
    protected String description;
    protected ETListClassification listClassification;
    protected Date modifiedDate;
    protected ETListType listType;
    
    
	public Integer getID() {
		return id;
	}
	public void setID(Integer id) {
		this.id = id;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getCustomerKey() {
		return customerKey;
	}
	public void setCustomerKey(String customerKey) {
		this.customerKey = customerKey;
	}
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
	public ETListClassification getListClassification() {
		return listClassification;
	}
	public void setListClassification(ETListClassification listClassification) {
		this.listClassification = listClassification;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public ETListType getListType() {
		return listType;
	}
	public void setListType(ETListType listType) {
		this.listType = listType;
	}
	
	@Override
	public String toString() {
		return "ETList [id=" + id + ", categoryId=" + categoryId + ", customerKey="
				+ customerKey + ", name=" + name + ", description="
				+ description + ", listClassification=" + listClassification
				+ ", modifiedDate=" + modifiedDate + ", listType=" + listType
				+ "]";
	}
}
