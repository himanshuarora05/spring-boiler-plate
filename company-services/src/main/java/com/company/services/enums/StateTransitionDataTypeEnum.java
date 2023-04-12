package com.company.services.enums;

/**
 * @author mukulbansal
 */
public enum StateTransitionDataTypeEnum {
    USERID(1, String.class),
    PHONE(2, String.class),
    OTP_TYPE_ENUM(3, String.class),
    ;

    private int type;
    private Class clazz;

    StateTransitionDataTypeEnum(int type, Class clazz) {
        this.type = type;
        this.clazz = clazz;
    }

    public int getType() {
        return type;
    }

    public Class getClazz() {
        return clazz;
    }
}
