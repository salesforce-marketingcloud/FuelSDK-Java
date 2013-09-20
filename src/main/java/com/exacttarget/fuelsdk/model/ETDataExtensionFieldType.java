//
// ETDataExtensionFieldType.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// @COPYRIGHT@
//

package com.exacttarget.fuelsdk.model;


public enum ETDataExtensionFieldType {

	    TEXT("Text"),
	    NUMBER("Number"),
	    DATE("Date"),
	    BOOLEAN("Boolean"),
	    EMAIL_ADDRESS("EmailAddress"),
	    PHONE("Phone"),
	    DECIMAL("Decimal"),
	    LOCALE("Locale");
	    private final String value;

	    ETDataExtensionFieldType(String v) {
	        value = v;
	    }

	    public String value() {
	        return value;
	    }

	    public static ETDataExtensionFieldType fromValue(String v) {
	        for (ETDataExtensionFieldType c: ETDataExtensionFieldType.values()) {
	            if (c.value.equals(v)) {
	                return c;
	            }
	        }
	        throw new IllegalArgumentException(v);
	    }

	}