//
// ETDataExtensionRow.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// @COPYRIGHT@
//

package com.exacttarget.fuelsdk.model;

import java.util.Map;

import com.exacttarget.fuelsdk.ETObject;
import com.exacttarget.fuelsdk.annotations.InternalSoapField;
import com.exacttarget.fuelsdk.annotations.InternalSoapType;
import com.exacttarget.fuelsdk.internal.DataExtensionObject;

@InternalSoapType(type = DataExtensionObject.class)
public class ETDataExtensionRow extends ETSoapObject implements ETObject {

	@InternalSoapField(name="name")
    private String name;

	@InternalSoapField(name="keys")
    private Map<String, String> keys;

	@InternalSoapField(name="properties")
    protected Map<String, String> columns;

	public ETDataExtensionRow() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, String> getKeys() {
		return keys;
	}

	public void setKeys(Map<String, String> keys) {
		this.keys = keys;
	}

	public Map<String, String> getColumns() {
		return columns;
	}

	public void setColumns(Map<String, String> columns) {
		this.columns = columns;
	}

	@Override
	public String toString() {
		return "ETDataExtensionRow [name=" + name + ", keys=" + keys
				+ ", columns=" + columns + ", id=" + id + ", createdDate="
				+ createdDate + ", modifiedDate=" + modifiedDate
				+ ", customerKey=" + customerKey + "]";
	}


}
