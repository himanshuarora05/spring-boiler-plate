package com.company.processors;

import com.company.core.serdes.GsonSerializer;
import com.company.core.validators.GenericBeanValidator;
import com.company.services.request.AbstractServiceRequest;
import com.company.services.request.BaseRequest;
import com.company.services.response.ServiceResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import javax.validation.constraints.NotNull;

/**
 * @author mukulbansal
 */
public abstract class AbstractProcessor<REQUEST extends BaseRequest, SERVICE_REQUEST extends AbstractServiceRequest>
        implements BaseProcessor<REQUEST, SERVICE_REQUEST> {

    protected final Logger LOG = LoggerFactory.getLogger(this.getClass());

    /**
     * Each controller method will use this method with its request DTO extending BaseRequest
     *
     * @param request
     * @param serviceRequest
     * @return
     */
    @Override
    public ResponseEntity<String> execute(REQUEST request, SERVICE_REQUEST serviceRequest) {
        ServiceResponse        serviceResponse = ServiceResponse.getDefaultResponse();
        ResponseEntity<String> response;
        try {
            GenericBeanValidator<REQUEST> requestValidator = new GenericBeanValidator<>(request);
            if (!requestValidator.validate()) {
                serviceResponse = ServiceResponse.getServiceResponse(requestValidator.getRawErrorMessage());
            } else {
                serviceResponse = preProcess(request, serviceRequest);
                if (serviceResponse.isValid() && serviceRequest.isValid()) {
                    serviceResponse = doProcess(serviceRequest, serviceResponse);
                }
            }
        } catch (Exception e) {
            LOG.error("Exception occured-", e);
        } finally {
            response = new ResponseEntity<>(GsonSerializer.getInstance().toJson(serviceResponse),
                    serviceResponse.getResponse().getHttpStatus());
            response = postProcess(serviceRequest, serviceResponse, response);
        }
        return response;
    }

    /**
     * This method fabricates a new service object and puts the extracted data after validating request data for an API.
     * e.g. Fetching state transition, validating expiry, status and compatibility with API
     * and setting StateTransition object in ServiceRequestDTO etc.
     *
     * @param baseRequest
     * @param serviceRequest
     * @return
     */
    @NotNull
    protected abstract ServiceResponse preProcess(REQUEST baseRequest, SERVICE_REQUEST serviceRequest) throws Exception;

    /**
     * This method will be processing the serviceDTO and generating the ServiceResponse.
     * e.g Sending an OTP, mapping new phone to user etc.
     *
     * @param abstractServiceRequest
     * @param serviceResponse
     * @return
     */
    protected abstract ServiceResponse doProcess(SERVICE_REQUEST abstractServiceRequest, ServiceResponse serviceResponse)
            throws Exception;

    /**
     * This method shall be overridden for implementing tasks which are to be done at the end of API life cycle.
     * e.g. Pushing data to risk, creating audit etc. by default it returns the response returned
     *
     * @param baseRequest
     * @param serviceResponse
     * @param response
     * @return
     */

    protected ResponseEntity<String> postProcess(SERVICE_REQUEST baseRequest, ServiceResponse serviceResponse,
                                                 ResponseEntity<String> response) {
        return response;
    }
}
