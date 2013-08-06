package com.exacttarget.fuelsdk.model;

import com.exacttarget.fuelsdk.annotations.InternalField;
import com.exacttarget.fuelsdk.annotations.InternalType;
import com.exacttarget.fuelsdk.internal.ContentArea;

@InternalType(type = ContentArea.class)
public class ETContentArea extends BaseSerializableObject implements ETObject {
    @InternalField(name="categoryID")
	protected Integer categoryId;
    @InternalField(name="content")
	protected String content;
    @InternalField(name="layout")
	protected ETLayoutType layout;
    @InternalField(name="name")
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
