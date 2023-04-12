package com.company.services.response;

import lombok.Getter;
import lombok.Setter;

/**
 * @author mukulbansal
 */
@Getter
@Setter
public abstract class BaseResponse {

    private Status status;
    private String responseCode;
    private String message;

    public BaseResponse(Status status, String responseCode, String message) {
        this.status = status;
        this.responseCode = responseCode;
        this.message = message;
    }

    public enum Status {
        SUCCESS, FAILURE, ERROR
    }

}
