package com.company.services.enums;

/**
 * @author mukulbansal
 */
public enum StateTransitionMethodTypeEnum {
    ONBOARD("onboard", 10 * 60 * 1000),
    ;

    private String name;
    private long   defaultExpiryTimeMillis;

    StateTransitionMethodTypeEnum(String name, long defaultExpiryTimeMillis) {
        this.name = name;
        this.defaultExpiryTimeMillis = defaultExpiryTimeMillis;
    }

    public String getName() {
        return name;
    }

    public long getDefaultExpiryTimeMillis() {
        return defaultExpiryTimeMillis;
    }

}
