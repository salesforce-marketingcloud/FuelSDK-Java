//
// ETListType.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// @COPYRIGHT@
//

package com.exacttarget.fuelsdk.model;


public enum ETListType {
    PUBLIC("Public"),
    PRIVATE("Private"),
    SALES_FORCE("SalesForce"),
    GLOBAL_UNSUBSCRIBE("GlobalUnsubscribe"),
    MASTER("Master");
    private final String value;

    ETListType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ETListType fromValue(String v) {
        for (ETListType c: ETListType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}