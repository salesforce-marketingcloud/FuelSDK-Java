//
// ETListClassification.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// @COPYRIGHT@
//

package com.exacttarget.fuelsdk.model;

public enum ETListClassification {
    EXACT_TARGET_LIST("ExactTargetList"),
    PUBLICATION_LIST("PublicationList"),
    SUPPRESSION_LIST("SuppressionList");
    private final String value;

    ETListClassification(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public static ETListClassification fromValue(String value) {
        for (ETListClassification c: ETListClassification.values()) {
            if (c.value.equals(value)) {
                return c;
            }
        }
        throw new IllegalArgumentException(value);
    }
}
