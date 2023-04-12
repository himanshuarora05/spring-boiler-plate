package com.company.processors.handlers;

import com.company.services.request.AbstractServiceRequest;
import com.company.services.response.ServiceResponse;

import javax.validation.constraints.NotNull;

/**
 * @param <SR>
 * @author mukulbansal
 */
public abstract class AbstractBaseHandler<SR extends AbstractServiceRequest> implements BaseHandler<SR> {

    @Override
    public ServiceResponse execute(SR serviceRequest) throws Exception {
        ServiceResponse baseResponse = preHandle(serviceRequest);
        if (baseResponse != null && baseResponse.isValid()) {
            baseResponse = handle(serviceRequest, baseResponse);
            if (baseResponse != null && baseResponse.isValid()) {
                baseResponse = postHandle(serviceRequest, baseResponse);
            }
        }
        return baseResponse;
    }

    /**
     * Any validations specific for a State Transition(flow) shall be implemented here.
     * e.g. password existence in case of logged out and token validation in logged in cases etc.
     *
     * @param serviceRequest
     * @return
     * @throws Exception
     */
    protected abstract @NotNull ServiceResponse preHandle(SR serviceRequest) throws Exception;

    /**
     * The main processing is implemented here.
     * e.g. sending OTP, Phone update etc.
     *
     * @param serviceRequest
     * @param serviceResponse
     * @return
     * @throws Exception
     */
    protected abstract ServiceResponse handle(SR serviceRequest, ServiceResponse serviceResponse) throws Exception;

    /**
     * Any statements which needs to be processed after success result of APIs shall be implemented here.
     * e.g. create new state transition object and set it to response etc.
     *
     * @param serviceRequest
     * @param serviceResponse
     * @return
     * @throws Exception
     */
    protected abstract ServiceResponse postHandle(SR serviceRequest, ServiceResponse serviceResponse) throws Exception;

}
