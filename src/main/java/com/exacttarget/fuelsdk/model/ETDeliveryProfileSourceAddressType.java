package com.exacttarget.fuelsdk.model;


public enum ETDeliveryProfileSourceAddressType {

    DEFAULT_PRIVATE_IP_ADDRESS("DefaultPrivateIPAddress"),
    CUSTOM_PRIVATE_IP_ADDRESS("CustomPrivateIPAddress");
    private final String value;

    ETDeliveryProfileSourceAddressType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ETDeliveryProfileSourceAddressType fromValue(String v) {
        for (ETDeliveryProfileSourceAddressType c: ETDeliveryProfileSourceAddressType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}