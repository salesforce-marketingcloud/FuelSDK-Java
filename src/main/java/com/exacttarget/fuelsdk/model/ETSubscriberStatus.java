package com.exacttarget.fuelsdk.model;


public enum ETSubscriberStatus {
    ACTIVE("Active"),
    BOUNCED("Bounced"),
    HELD("Held"),
    UNSUBSCRIBED("Unsubscribed"),
    DELETED("Deleted");
    private final String value;

    ETSubscriberStatus(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ETSubscriberStatus fromValue(String v) {
        for (ETSubscriberStatus c: ETSubscriberStatus.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}
