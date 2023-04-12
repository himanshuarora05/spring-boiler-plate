package com.company.services.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author mukulbansal
 */
public class EncryptionUtilTest {

    private static final String RAW_STRING = "100000034";

    @Test
    public void encrypt(){
        String encString = EncryptionUtil.encrypt(RAW_STRING);
        Assert.assertEquals(RAW_STRING, EncryptionUtil.decrypt(encString));
    }

}
