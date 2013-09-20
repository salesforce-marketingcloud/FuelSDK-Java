//
// ETSalutationSource.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// @COPYRIGHT@
//

package com.exacttarget.fuelsdk.model;


public enum ETSalutationSource {

    DEFAULT("Default"),
    CONTENT_LIBRARY("ContentLibrary"),
    NONE("None");
    private final String value;

    ETSalutationSource(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ETSalutationSource fromValue(String v) {
        for (ETSalutationSource c: ETSalutationSource.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
