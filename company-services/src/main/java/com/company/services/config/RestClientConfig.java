package com.company.services.config;

import com.company.core.util.ApplicationPropertyEnum;
import com.company.services.client.ClientHttpRequestFactory;
import com.company.services.client.RestClient;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * @author mukulbansal
 */
@Configuration
public class RestClientConfig {

    @Bean
    public RestClient restClient() {
        RestClient restClient = new RestClient(restTemplate());
        return restClient;
    }

    private RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate(requestFactory());
        restTemplate.setMessageConverters(Arrays.asList(stringConverter(), formConverter()));
        return restTemplate;
    }

    private ClientHttpRequestFactory requestFactory() {
        ClientHttpRequestFactory requestFactory = new ClientHttpRequestFactory(highLatencyHttpClient());
        requestFactory.setReadTimeout(Integer.parseInt(ApplicationPropertyEnum.REST_CLIENT_READ_TIMEOUT.getValue()));
        requestFactory.setConnectTimeout(Integer.parseInt(ApplicationPropertyEnum.REST_CLIENT_CONNECT_TIMEOUT.getValue()));
        requestFactory.setConnectionRequestTimeout(Integer.parseInt(ApplicationPropertyEnum.REST_CLIENT_CONNECTION_REQUEST_TIMEOUT.getValue()));
        return requestFactory;
    }

    private StringHttpMessageConverter stringConverter() {
        return new StringHttpMessageConverter();
    }

    private FormHttpMessageConverter formConverter() {
        return new FormHttpMessageConverter();
    }


    private HttpClient highLatencyHttpClient() {
        return HttpClientBuilder.create().setConnectionManager(highLatencyPoolingClientConnectionManager()).build();
    }

    private PoolingHttpClientConnectionManager highLatencyPoolingClientConnectionManager() {
        PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
        poolingHttpClientConnectionManager.setMaxTotal(50);
        poolingHttpClientConnectionManager.setDefaultMaxPerRoute(20);
        return poolingHttpClientConnectionManager;
    }
}
