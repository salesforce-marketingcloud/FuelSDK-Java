package com.exacttarget.fuelsdk.model;

import java.util.Date;

import com.exacttarget.fuelsdk.annotations.InternalType;
import com.exacttarget.fuelsdk.internal.ContentArea;

@InternalType(type = ContentArea.class, fields = {
	"ID",
	"CategoryID",
    "Content",
    "CustomerKey",
    "CreatedDate",
    "Layout",
    "ModifiedDate",
    "Name"})
public class ETContentArea implements ETObject {

	protected Integer id;
	protected Integer categoryId;
	protected String content;
	protected String customerKey;
	protected Date createdDate;
	protected String layout;
	protected Date modifiedDate;
	protected String name;

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCustomerKey() {
		return customerKey;
	}

	public void setCustomerKey(String customerKey) {
		this.customerKey = customerKey;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getLayout() {
		return layout;
	}

	public void setLayout(String layout) {
		this.layout = layout;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "ETContentArea [id=" + id + ", categoryId=" + categoryId
				+ ", content=" + content + ", customerKey=" + customerKey
				+ ", createdDate=" + createdDate + ", layout=" + layout
				+ ", modifiedDate=" + modifiedDate + ", name=" + name + "]";
	}
	
	
	
}
