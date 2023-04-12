package com.company.processors;

import com.company.core.util.ApplicationPropertyEnum;
import com.company.services.enums.ResponseCodeHttpStatusEnum;
import com.company.services.request.AppPropertyUpdateServiceRequest;
import com.company.services.response.ServiceResponse;
import com.company.services.rest.request.AppPropertyUpdateRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

/**
 * @author mukulbansal
 */
@Service
public class AppPropertyUpdateProcessor extends AbstractProcessor<AppPropertyUpdateRequest, AppPropertyUpdateServiceRequest> {

    @Override
    protected @NotNull ServiceResponse preProcess(AppPropertyUpdateRequest baseRequest, AppPropertyUpdateServiceRequest serviceRequest) throws Exception {
        if(StringUtils.isNotBlank(ApplicationPropertyEnum.APP_PROPERTY_UPDATE_SECRET.getValue()) && baseRequest.getSecret().equalsIgnoreCase(ApplicationPropertyEnum.APP_PROPERTY_UPDATE_SECRET.getValue())) {
            serviceRequest.setKey(baseRequest.getKey());
            serviceRequest.setValue(baseRequest.getValue());
            return ServiceResponse.getServiceResponse(ResponseCodeHttpStatusEnum.VALIDATION_SUCCESSFUL);
        }
        return ServiceResponse.getServiceResponse(ResponseCodeHttpStatusEnum.FORBIDDEN_ACTION);
    }

    @Override
    protected ServiceResponse doProcess(AppPropertyUpdateServiceRequest abstractServiceRequest, ServiceResponse serviceResponse) throws Exception {
        System.setProperty(abstractServiceRequest.getKey(), abstractServiceRequest.getValue());
        return ServiceResponse.getServiceResponse(ResponseCodeHttpStatusEnum.SUCCESS);
    }
}
