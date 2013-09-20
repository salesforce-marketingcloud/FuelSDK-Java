//
// ETList.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// @COPYRIGHT@
//

package com.exacttarget.fuelsdk.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.exacttarget.fuelsdk.annotations.InternalSoapField;
import com.exacttarget.fuelsdk.annotations.InternalSoapType;
import com.exacttarget.fuelsdk.internal.List;

@InternalSoapType(type = List.class)
public class ETList extends ETSoapObject
{
    @InternalSoapField(name="listName")
    protected String name = null;
    @InternalSoapField(name="description")
    protected String description = null;
    // XXX?
    //@InternalSoapField(name="category")
    protected Integer categoryId = null;
    @InternalSoapField(name="listClassification")
    protected ETListClassification listClassification = null;
    // XXX?
    //@InternalSoapField(name="type")
    protected ETListType listType = null;

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
        return ToStringBuilder.reflectionToString(this);
    }
}
