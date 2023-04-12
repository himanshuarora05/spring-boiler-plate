package com.company.core.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author mukulbansal
 */
@Getter
@AllArgsConstructor
public class CustomEncryptionException extends RuntimeException {

    private String message;

    public CustomEncryptionException(String message , Throwable cause){
        super(message,cause);
    }

    public CustomEncryptionException(Throwable cause) {
        super(cause);
    }

}
