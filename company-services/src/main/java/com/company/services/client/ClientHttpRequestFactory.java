package com.company.services.client;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

import java.net.URI;

/**
 * @author mukulbansal
 */
public class ClientHttpRequestFactory extends HttpComponentsClientHttpRequestFactory {

    public ClientHttpRequestFactory(HttpClient httpClient) {
        super(httpClient);
    }

    @Override
    protected HttpUriRequest createHttpUriRequest(HttpMethod httpMethod, URI uri) {
        return super.createHttpUriRequest(httpMethod, uri);
    }
}
