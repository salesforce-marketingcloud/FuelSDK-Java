//
// ETDataSourceType.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// @COPYRIGHT@
//

package com.exacttarget.fuelsdk.model;


public enum ETDataSourceType {

    LIST("List"),
    CUSTOM_OBJECT("CustomObject"),
    DOMAIN_EXCLUSION("DomainExclusion"),
    SALES_FORCE_REPORT("SalesForceReport"),
    SALES_FORCE_CAMPAIGN("SalesForceCampaign"),
    FILTER_DEFINITION("FilterDefinition"),
    OPT_OUT_LIST("OptOutList");
    private final String value;

    ETDataSourceType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ETDataSourceType fromValue(String v) {
        for (ETDataSourceType c: ETDataSourceType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
