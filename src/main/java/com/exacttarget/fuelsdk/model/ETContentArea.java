//
// ETContentArea.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// @COPYRIGHT@
//

package com.exacttarget.fuelsdk.model;

import com.exacttarget.fuelsdk.ETSoapObject;
import com.exacttarget.fuelsdk.annotations.InternalSoapField;
import com.exacttarget.fuelsdk.annotations.InternalSoapType;
import com.exacttarget.fuelsdk.internal.ContentArea;

@InternalSoapType(type = ContentArea.class)
public class ETContentArea extends ETSoapObject {
    @InternalSoapField(name="categoryID")
	protected Integer categoryId;
    @InternalSoapField(name="content")
	protected String content;
    @InternalSoapField(name="layout")
	protected ETLayoutType layout;
    @InternalSoapField(name="name")
	protected String name;

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
	public ETLayoutType getLayout() {
		return layout;
	}
	public void setLayout(ETLayoutType layout) {
		this.layout = layout;
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
