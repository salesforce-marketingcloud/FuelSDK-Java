//
// ETDeliveryProfileDomainType.java -
//
//      x
//
// Copyright (C) 2013 ExactTarget
//
// @COPYRIGHT@
//

package com.exacttarget.fuelsdk.model;


public enum ETDeliveryProfileDomainType {

    DEFAULT_DOMAIN("DefaultDomain"),
    CUSTOM_DOMAIN("CustomDomain");
    private final String value;

    ETDeliveryProfileDomainType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ETDeliveryProfileDomainType fromValue(String v) {
        for (ETDeliveryProfileDomainType c: ETDeliveryProfileDomainType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
