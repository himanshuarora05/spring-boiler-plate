package com.company.processors;

import com.company.core.util.ApplicationPropertyEnum;
import com.company.services.enums.ResponseCodeHttpStatusEnum;
import com.company.services.request.AppPropertyGetServiceRequest;
import com.company.services.request.BaseRequest;
import com.company.services.response.AppPropertyGetResponse;
import com.company.services.response.ServiceResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

/**
 * @author mukulbansal
 */
@Service
public class AppPropertyGetProcessor extends AbstractProcessor<BaseRequest, AppPropertyGetServiceRequest> {

    @Override
    protected @NotNull ServiceResponse preProcess(BaseRequest baseRequest, AppPropertyGetServiceRequest serviceRequest) throws Exception {
        if(StringUtils.isNotBlank(ApplicationPropertyEnum.APP_PROPERTY_UPDATE_SECRET.getValue()) && serviceRequest.getSecret().equalsIgnoreCase(ApplicationPropertyEnum.APP_PROPERTY_UPDATE_SECRET.getValue())) {
            return ServiceResponse.getServiceResponse(ResponseCodeHttpStatusEnum.VALIDATION_SUCCESSFUL);
        }
        serviceRequest.setSecret(null);
        return ServiceResponse.getServiceResponse(ResponseCodeHttpStatusEnum.FORBIDDEN_ACTION);
    }

    @Override
    protected ServiceResponse doProcess(AppPropertyGetServiceRequest abstractServiceRequest, ServiceResponse serviceResponse) throws Exception {
        AppPropertyGetResponse appPropertyGetResponse = new AppPropertyGetResponse(ResponseCodeHttpStatusEnum.SUCCESS);
        appPropertyGetResponse.setData(System.getProperties());
        return appPropertyGetResponse;
    }
}
