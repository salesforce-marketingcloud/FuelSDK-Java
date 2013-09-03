package com.exacttarget.fuelsdk.model;


public enum ETAccountType {
	    NONE("None"),
	    EXACTTARGET("EXACTTARGET"),
	    PRO_CONNECT("PRO_CONNECT"),
	    CHANNEL_CONNECT("CHANNEL_CONNECT"),
	    CONNECT("CONNECT"),
	    PRO_CONNECT_CLIENT("PRO_CONNECT_CLIENT"),
	    LP_MEMBER("LP_MEMBER"),
	    DOTO_MEMBER("DOTO_MEMBER"),
	    ENTERPRISE_2("ENTERPRISE_2"),
	    BUSINESS_UNIT("BUSINESS_UNIT");
	    private final String value;

	    ETAccountType(String v) {
	        value = v;
	    }

	    public String value() {
	        return value;
	    }

	    public static ETAccountType fromValue(String v) {
	        for (ETAccountType c: ETAccountType.values()) {
	            if (c.value.equals(v)) {
	                return c;
	            }
	        }
	        throw new IllegalArgumentException(v);
	    }

	}