package com.company.services.util;

import com.company.core.util.Constants;
import com.company.core.util.UUIDUtil;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author mukulbansal
 */
public class UserPlatformUtil {



    public static String getTraceId(HttpServletRequest request) {
        return StringUtils.isBlank(request.getHeader(Constants.Common.X_REQUEST_ID))
                ? UUIDUtil.getUUID4()
                : request.getHeader(Constants.Common.X_REQUEST_ID);
    }

    public static String getSpanId() {
        return UUIDUtil.getTrimmedUUID4();
    }
}
