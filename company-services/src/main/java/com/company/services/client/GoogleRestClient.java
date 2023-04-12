package com.company.services.client;

import com.company.services.rest.response.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author mukulbansal
 */
@Component
public class GoogleRestClient {

    @Autowired
    private RestClient restClient;

    public RestResponse post(String url, Map<String, String> paramMap, Map<String, String> headerMap, String body) {
        return restClient.postWithoutErrorHandling(url, paramMap, headerMap, body);
    }
}
