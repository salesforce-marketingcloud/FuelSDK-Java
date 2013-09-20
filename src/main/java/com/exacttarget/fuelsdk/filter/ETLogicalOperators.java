//
// ETLogicalOperators.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// @COPYRIGHT@
//

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