//
// ETLayoutType.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// @COPYRIGHT@
//

package com.exacttarget.fuelsdk.model;


public enum ETLayoutType {
    HTML_WRAPPED("HTMLWrapped"),
    RAW_TEXT("RawText"),
    SMS("SMS");
    private final String value;

    ETLayoutType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ETLayoutType fromValue(String v) {
        for (ETLayoutType c: ETLayoutType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
