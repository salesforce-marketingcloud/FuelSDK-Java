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
    GLOBAL_UNSUBSCRIBE("GlobalUnsubscribe"),
    MASTER("Master"),
    PRIVATE("Private"),
    PUBLIC("Public"),
    SALES_FORCE("SalesForce");
    private final String value;

    ETListType(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public static ETListType fromValue(String value) {
        for (ETListType t: ETListType.values()) {
            if (t.value.equals(value)) {
                return t;
            }
        }
        throw new IllegalArgumentException(value);
    }
}
