package com.company.processors.handlers;

import com.company.services.request.AbstractServiceRequest;
import com.company.services.response.ServiceResponse;

/**
 * @author mukulbansal
 */
public interface BaseHandler<SR extends AbstractServiceRequest> {
    ServiceResponse execute(SR serviceRequest) throws Exception;
}
