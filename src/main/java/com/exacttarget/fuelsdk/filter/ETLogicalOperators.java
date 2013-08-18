package com.exacttarget.fuelsdk.filter;


public enum ETLogicalOperators {


    OR,
    AND;

    public String value() {
        return name();
    }

    public static ETLogicalOperators fromValue(String v) {
        return valueOf(v);
    }

}