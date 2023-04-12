package com.company.core.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author mukulbansal
 */
@Getter
@AllArgsConstructor
public class CustomDecryptionException extends RuntimeException{
    private String message;

    public CustomDecryptionException(String message , Throwable cause){
        super(message,cause);
    }

    public CustomDecryptionException(Throwable cause) {
        super(cause);
    }

}
