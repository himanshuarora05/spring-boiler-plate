package com.company.services.filters;

import com.company.core.util.Constants;
import com.company.services.util.UserPlatformUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author mukulbansal
 */
@Component
@Order(1)
public class TimerFilter implements Filter {
    private static final Logger LOG = LoggerFactory.getLogger(TimerFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long startTime = System.currentTimeMillis();
        MDC.put(Constants.Common.X_REQUEST_ID, UserPlatformUtil.getTraceId((HttpServletRequest)servletRequest));
        MDC.put(Constants.Common.X_SPAN_ID, UserPlatformUtil.getSpanId());
        filterChain.doFilter(servletRequest, servletResponse);
        long endTime = System.currentTimeMillis();
        LOG.info("Time taken by the API {} is {}", ((HttpServletRequest) servletRequest).getRequestURI(), endTime - startTime);
    }

    @Override
    public void destroy() {
        MDC.clear();
    }
}
