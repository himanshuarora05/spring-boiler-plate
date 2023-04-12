package com.company.services.util;

import com.company.core.model.AnalyticsLogDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author mukulbansal
 */
public class AnalyticsLoggerUtil {

    private static final Logger LOG = LoggerFactory.getLogger(AnalyticsLoggerUtil.class);

    public static void writeLog(com.company.services.http.wrappers.CustomHttpServletRequestWrapper requestToUse, ContentCachingResponseWrapper responseToUse) throws UnsupportedEncodingException {
        String          requestUri      = requestToUse.getRequestURI();
        String          requestBody     = requestToUse.getRawBody();
        String          responseBody    = (new String(responseToUse.getContentAsByteArray(), responseToUse.getCharacterEncoding()));
        AnalyticsLogDto analyticsLogDTO = new AnalyticsLogDto(requestUri, requestBody, responseBody, getHttpHeaderMap(requestToUse), responseToUse.getStatus());
        LOG.info(analyticsLogDTO.toString());
    }

    public static @NotNull Map<String, String> getHttpHeaderMap(HttpServletRequest request) {
        Assert.notNull(request);
        Map<String, String> headerMap   = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String headerName = headerNames.nextElement();
                headerMap.put(headerName, request.getHeader(headerName));
            }
        }
        return headerMap;
    }
}
