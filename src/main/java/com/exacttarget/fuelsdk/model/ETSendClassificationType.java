//
// ETSendClassificationType.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// @COPYRIGHT@
//

package com.exacttarget.fuelsdk.model;


public enum ETSendClassificationType {

    OPERATIONAL("Operational"),
    MARKETING("Marketing");
    private final String value;

    ETSendClassificationType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ETSendClassificationType fromValue(String v) {
        for (ETSendClassificationType c: ETSendClassificationType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
