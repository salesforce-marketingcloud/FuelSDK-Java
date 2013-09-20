//
// ETList.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//

package com.exacttarget.fuelsdk.model;

import com.exacttarget.fuelsdk.annotations.InternalSoapField;
import com.exacttarget.fuelsdk.annotations.InternalSoapType;
import com.exacttarget.fuelsdk.internal.List;

@InternalSoapType(type = List.class)
public class ETList extends ETSoapObject implements ETObject
{
    @InternalSoapField(name="listName")
    protected String name;
    @InternalSoapField(name="description")
    protected String description;
    // XXX?
    //@InternalSoapField(name="category")
    protected Integer categoryId;
    @InternalSoapField(name="listClassification")
    protected ETListClassification listClassification;
    // XXX?
    //@InternalSoapField(name="type")
    protected ETListType listType;

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

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public ETListClassification getListClassification() {
        return listClassification;
    }

    public void setListClassification(ETListClassification listClassification) {
        this.listClassification = listClassification;
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
