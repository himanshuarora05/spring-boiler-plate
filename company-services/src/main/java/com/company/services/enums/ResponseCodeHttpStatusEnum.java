package com.company.services.enums;

import com.company.services.response.BaseResponse;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

/**
 * @author mukulbansal
 */
public enum ResponseCodeHttpStatusEnum {

    SUCCESS("000001", BaseResponse.Status.SUCCESS, HttpStatus.OK),
    BAD_REQUEST("000002", BaseResponse.Status.FAILURE, HttpStatus.BAD_REQUEST),
    UNSUPPORTED_MEDIA_TYPE("000003", BaseResponse.Status.FAILURE, HttpStatus.UNSUPPORTED_MEDIA_TYPE),
    NOT_FOUND("000004", BaseResponse.Status.FAILURE, HttpStatus.NOT_FOUND),
    REQUEST_METHOD_NOT_SUPPORTED("000005", BaseResponse.Status.FAILURE, HttpStatus.METHOD_NOT_ALLOWED),
    FORBIDDEN_ACTION("000006", BaseResponse.Status.FAILURE, HttpStatus.FORBIDDEN),
    VALIDATION_SUCCESSFUL("999999", BaseResponse.Status.FAILURE, HttpStatus.INTERNAL_SERVER_ERROR),
    SOMETHING_WRONG("999999", BaseResponse.Status.FAILURE, HttpStatus.INTERNAL_SERVER_ERROR),
    ;

    private String              responseCode;
    private BaseResponse.Status status;
    private HttpStatus          httpStatus;

    ResponseCodeHttpStatusEnum(String responseCode, BaseResponse.Status status, HttpStatus httpStatus) {
        this.responseCode = responseCode;
        this.status = status;
        this.httpStatus = httpStatus;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public BaseResponse.Status getStatus() {
        return status;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public static ResponseCodeHttpStatusEnum getFromResponseCode(String responseCode) {
        return Arrays.stream(values()).filter(x -> x.responseCode.equalsIgnoreCase(responseCode)).findFirst().get();
    }
}
