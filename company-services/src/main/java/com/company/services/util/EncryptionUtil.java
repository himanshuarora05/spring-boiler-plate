package com.company.services.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.nio.charset.Charset;
import java.util.Base64;

/**
 * @author mukulbansal
 */
public class EncryptionUtil {

    private static final Logger LOG = LoggerFactory.getLogger(EncryptionUtil.class);

    private static final String ENC_KEY = "ffdeb9bfa4ad49488daf53f743e3be4c";
    private static DESKeySpec DES_KEY_SPEC = null;
    private static SecretKeyFactory SECRET_KEY_FACTORY = null;
    private static SecretKey SECRET_KEY = null;

    static {
        try {
            DES_KEY_SPEC = new DESKeySpec(ENC_KEY.getBytes(Charset.defaultCharset()));
            SECRET_KEY_FACTORY = SecretKeyFactory.getInstance("DES");
            SECRET_KEY = SECRET_KEY_FACTORY.generateSecret(DES_KEY_SPEC);
        } catch (Exception e) {
            LOG.error("Exception- ", e);
            System.exit(0);
        }
    }

    public static String encrypt(String rawString) {
        try {
            byte[] cleartext = rawString.getBytes(Charset.defaultCharset());
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, SECRET_KEY);
            return Base64.getEncoder().encodeToString(cipher.doFinal(cleartext));
        } catch (Exception e) {
            throw new com.company.core.exception.CustomEncryptionException(e);
        }
    }

    public static String decrypt(String encString) {
        try {
            byte[] ecsStringBytes = Base64.getDecoder().decode(encString);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, SECRET_KEY);
            return new String(cipher.doFinal(ecsStringBytes));
        } catch (Exception e) {
            throw new com.company.core.exception.CustomDecryptionException(e);
        }
    }

}
