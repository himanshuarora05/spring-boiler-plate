package com.company.services.response;

import com.company.services.enums.ResponseCodeHttpStatusEnum;

/**
 * @author mukulbansal
 */
public class ServiceResponse extends BaseResponse {
    private transient ResponseCodeHttpStatusEnum response;

    public ServiceResponse(ResponseCodeHttpStatusEnum response) {
        super(response != null
                        ? response.getStatus()
                        : ResponseCodeHttpStatusEnum.SOMETHING_WRONG.getStatus(),
                response != null
                        ? response.getResponseCode()
                        : ResponseCodeHttpStatusEnum.SOMETHING_WRONG.getResponseCode(),
                response != null
                        ? ResponseMessageHelper.getResponse(response.getResponseCode())
                        : ResponseMessageHelper.getResponse(ResponseCodeHttpStatusEnum.SOMETHING_WRONG.getResponseCode()));
        this.response = response != null ? response :
                ResponseCodeHttpStatusEnum.SOMETHING_WRONG;
    }

    public static ServiceResponse getDefaultResponse() {
        return new ServiceResponse(ResponseCodeHttpStatusEnum.SOMETHING_WRONG);
    }

    public static ServiceResponse getServiceResponse(ResponseCodeHttpStatusEnum responseCodeHttpStatusEnum) {
        return new ServiceResponse(responseCodeHttpStatusEnum);
    }

    public static ServiceResponse getServiceResponse(ResponseCodeHttpStatusEnum responseCodeHttpStatusEnum, String... variables) {
        ServiceResponse serviceResponse = new ServiceResponse(responseCodeHttpStatusEnum);
        for (int counter = 1; counter <= variables.length; counter++) {
            serviceResponse.setMessage(serviceResponse.getMessage().replace("{%" + counter + "}", variables[0]));
        }
        return serviceResponse;
    }

    public static ServiceResponse getServiceResponse(String responseCode) {
        return new ServiceResponse(ResponseCodeHttpStatusEnum.getFromResponseCode(responseCode));
    }

    public ResponseCodeHttpStatusEnum getResponse() {
        return response;
    }

    public boolean isValid() {
        return Boolean.TRUE;
    }

}
