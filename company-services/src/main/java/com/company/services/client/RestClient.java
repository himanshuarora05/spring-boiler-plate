package com.company.services.client;

import com.company.core.util.Constants;
import com.company.services.rest.response.RestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * @author mukulbansal
 */
public class RestClient {

    private final Logger LOG = LoggerFactory.getLogger(RestClient.class);

    private RestTemplate restTemplate;

    public RestClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public RestResponse postWithoutErrorHandling(String url, Map<String, String> paramMap, Map<String, String> headerMap, String body){
        if (headerMap == null) {
            headerMap = new HashMap<>();
        }
        RestResponse restResponse = new RestResponse();
        HttpHeaders requestHeaders = new HttpHeaders();
        if (headerMap != null && !headerMap.isEmpty()) {
            Set<Entry<String, String>> entrySet = headerMap.entrySet();
            for (Entry<String, String> entry : entrySet) {
                requestHeaders.set(entry.getKey(), entry.getValue());
            }
        }
        requestHeaders.set("Accept", "text/html,application/xml;q=0.9,application/xhtml+xml,text/xml;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5");
        requestHeaders.set(Constants.Common.X_REQUEST_ID, MDC.get(Constants.Common.X_REQUEST_ID));

        MultiValueMap<String, String> mapa = new LinkedMultiValueMap<String, String>();
        if (null == paramMap) {
            paramMap = new HashMap<>();
        }
        mapa.setAll(paramMap);
        ResponseEntity<String> responseEntity = null;

        if (!StringUtils.isEmpty(body)) {
            HttpEntity<String> httpEntity = new HttpEntity<String>(body, requestHeaders);
            responseEntity = exchange(url, HttpMethod.POST, httpEntity, String.class, paramMap);
        } else {
            HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<MultiValueMap<String, String>>(mapa, requestHeaders);
            responseEntity = exchange(url, HttpMethod.POST, httpEntity, String.class, paramMap);
        }
        restResponse.setStatusCode(responseEntity.getStatusCode().value());
        restResponse.setResponse(responseEntity.getBody());
        restResponse.setStatusMessage(responseEntity.getStatusCode().getReasonPhrase());
        restResponse.setStatus(Constants.Common.SUCCESS);
        restResponse.setHttpHeaders(responseEntity.getHeaders());

        return restResponse;
    }

    //---------------------------Wrapper over spring rest client----------------------------------

    @PostConstruct
    public void init() {
        try {
            List<HttpMessageConverter<?>> convertorList = getMessageConverters();
            for (HttpMessageConverter converter : convertorList) {
                if (converter instanceof StringHttpMessageConverter) {
                    Field charsetField = converter.getClass().getField("DEFAULT_CHARSET");
                    Charset charset = Charset.forName("UTF-8");

                    charsetField.setAccessible(true);

                    Field modifiersField = Field.class.getDeclaredField("modifiers");
                    modifiersField.setAccessible(true);
                    modifiersField.setInt(charsetField, charsetField.getModifiers() & ~Modifier.FINAL);

                    charsetField.set(null, charset);
                }
            }
        } catch (Exception ex) {
            LOG.error("Error in setting charset", ex);
        }
    }

    private ResponseEntity<String> exchange(String url, HttpMethod method, HttpEntity<?> httpEntity,
                                            Class<?> class1, Map<String, String> paramMap) {
        ResponseEntity<String> responseEntity = null;
        Long startTime = null;
        try {
            startTime = System.currentTimeMillis();
            logRequest(url, method, httpEntity);
            responseEntity = restTemplate.exchange(url, method, httpEntity, String.class, paramMap);
        } catch (HttpClientErrorException e) {
            LOG.error("Bad Request Response recieved from client- ", e);
            responseEntity = new ResponseEntity<>(e.getResponseBodyAsString(), HttpStatus.BAD_REQUEST);
        } catch (HttpServerErrorException e) {
            responseEntity = new ResponseEntity<>(e.getResponseBodyAsString(), HttpStatus.INTERNAL_SERVER_ERROR);
            throw e;
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            throw e;
        } finally {
            // logging response here because RestTemlate throw Exception in case of HTTP 4XX or 5XX from client.
            logResponse(url, responseEntity, startTime);
        }
        return responseEntity;
    }

    private void logRequest(String url, HttpMethod method, HttpEntity<?> httpEntity) {
        LOG.info("Making a rest call for URL {} with HTTP Method {}, headers {}, and Body- {}", url, method, httpEntity.getHeaders(), httpEntity.getBody());
    }

    private void logResponse(String url, ResponseEntity<String> responseEntity, Long startTime) {
        LOG.info("Response recieved in {} ms for rest call is {}.", System.currentTimeMillis() - startTime, responseEntity.getBody());

    }

    private List<HttpMessageConverter<?>> getMessageConverters() {
        return restTemplate.getMessageConverters();
    }

}

