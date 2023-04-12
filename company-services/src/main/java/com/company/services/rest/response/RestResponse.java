package com.company.services.rest.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpHeaders;

/**
 * @author mukulbansal
 */
@Getter
@Setter
public class RestResponse {
    private String response;
    private int statusCode;
    private String statusMessage;
    private String status;
    private String url;
    private HttpHeaders httpHeaders;
}
