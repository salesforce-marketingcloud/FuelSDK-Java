package com.exacttarget.fuelsdk.model;


public enum ETListClassification {
	EXACT_TARGET_LIST("ExactTargetList"),
    PUBLICATION_LIST("PublicationList"),
    SUPPRESSION_LIST("SuppressionList");
    private final String value;

    ETListClassification(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ETListClassification fromValue(String v) {
        for (ETListClassification c: ETListClassification.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}
