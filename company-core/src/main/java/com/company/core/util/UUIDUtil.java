package com.company.core.util;

import java.util.UUID;

/**
 * @author mukulbansal
 */
public class UUIDUtil {

    private static final int TRIMMED_UUID_LENGTH = 30;

    public static String getUUID4() {
        return String.valueOf(UUID.randomUUID());
    }

    public static String getTrimmedUUID4() {
        return String.valueOf(UUID.randomUUID()).split(Constants.Symbols.HYPHEN)[0];
    }
}
