package com.company.services.filters;

import com.company.services.util.AnalyticsLoggerUtil;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author mukulbansal
 */
@Component
@Order(2)
public class LoggerFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        com.company.services.http.wrappers.CustomHttpServletRequestWrapper requestToUse  = new com.company.services.http.wrappers.CustomHttpServletRequestWrapper((HttpServletRequest) servletRequest);
        ContentCachingResponseWrapper   responseToUse = new ContentCachingResponseWrapper((HttpServletResponse) servletResponse);
        if (servletResponse.getCharacterEncoding() == null) {
            servletResponse.setCharacterEncoding("UTF-8");
        }
        filterChain.doFilter(requestToUse, responseToUse);
        AnalyticsLoggerUtil.writeLog(requestToUse, responseToUse);
        responseToUse.copyBodyToResponse();
    }

    @Override
    public void destroy() {

    }
}
