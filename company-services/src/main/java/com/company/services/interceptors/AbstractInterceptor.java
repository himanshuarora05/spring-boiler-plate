package com.company.services.interceptors;

import com.company.core.serdes.GsonSerializer;
import com.company.services.enums.ResponseCodeHttpStatusEnum;
import com.company.services.response.ServiceResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author mukulbansal
 */
public class AbstractInterceptor extends HandlerInterceptorAdapter {

    protected final Logger LOG = LoggerFactory.getLogger(this.getClass());

    protected void setResponse(HttpServletResponse response, ResponseCodeHttpStatusEnum responseCodeHttpStatusEnum) {
        response.setStatus(responseCodeHttpStatusEnum.getHttpStatus().value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        try {
            response.getWriter().write(GsonSerializer.getInstance().toJson(ServiceResponse.getServiceResponse(responseCodeHttpStatusEnum)));
        } catch (IOException e) {
            LOG.error("Exception- ", e);
        }
    }

}
