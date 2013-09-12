package com.exacttarget.fuelsdk.model;


public enum ETSendPriority {
	
    BURST("Burst"),
    NORMAL("Normal"),
    LOW("Low");
    private final String value;

    ETSendPriority(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ETSendPriority fromValue(String v) {
        for (ETSendPriority c: ETSendPriority.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
