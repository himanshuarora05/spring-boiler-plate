package com.company.processors;

import com.company.services.request.AbstractServiceRequest;
import com.company.services.request.BaseRequest;
import org.springframework.http.ResponseEntity;

/**
 * @author mukulbansal
 */
public interface BaseProcessor<R extends BaseRequest, SR extends AbstractServiceRequest> {
    ResponseEntity<String> execute(R request, SR serviceRequest);
}
