package com.exacttarget.fuelsdk.model;


public enum ETEmailType {
    
    TEXT("Text"),
    HTML("HTML");
    private final String value;

    ETEmailType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ETEmailType fromValue(String v) {
        for (ETEmailType c: ETEmailType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}