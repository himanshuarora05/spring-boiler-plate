package com.company.services.response;

import com.company.core.util.Constants;
import com.company.services.enums.ResponseCodeHttpStatusEnum;
import org.slf4j.MDC;

/**
 * @author mukulbansal
 */
public class ResponseMessageHelper {

    public static String getResponse(String responseCode){
        return responseCode.equalsIgnoreCase(ResponseCodeHttpStatusEnum.SOMETHING_WRONG.getResponseCode())
                ? System.getProperty(responseCode).concat(MDC.get(Constants.Common.X_REQUEST_ID))
                : System.getProperty(responseCode);
    }
}
