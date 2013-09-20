//
// ETSendDefinitionListType.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// @COPYRIGHT@
//

package com.exacttarget.fuelsdk.model;


public enum ETSendDefinitionListType {

	SOURCE_LIST("SourceList"),
    EXCLUSION_LIST("ExclusionList"),
    DOMAIN_EXCLUSION("DomainExclusion"),
    OPT_OUT_LIST("OptOutList");
    private final String value;

    ETSendDefinitionListType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ETSendDefinitionListType fromValue(String v) {
        for (ETSendDefinitionListType c: ETSendDefinitionListType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}