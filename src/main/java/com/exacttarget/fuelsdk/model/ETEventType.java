package com.exacttarget.fuelsdk.model;


public enum ETEventType {
    
	OPEN("Open"),
    CLICK("Click"),
    HARD_BOUNCE("HardBounce"),
    SOFT_BOUNCE("SoftBounce"),
    OTHER_BOUNCE("OtherBounce"),
    UNSUBSCRIBE("Unsubscribe"),
    SENT("Sent"),
    NOT_SENT("NotSent"),
    SURVEY("Survey"),
    FORWARDED_EMAIL("ForwardedEmail"),
    FORWARDED_EMAIL_OPT_IN("ForwardedEmailOptIn"),
    DELIVERED_EVENT("DeliveredEvent");
    private final String value;

    ETEventType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ETEventType fromValue(String v) {
        for (ETEventType c: ETEventType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
