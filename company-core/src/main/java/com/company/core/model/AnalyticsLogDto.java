package com.company.core.model;

import com.company.core.util.Constants;
import com.company.core.serdes.GsonSerializer;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * @author mukulbansal
 */
public class AnalyticsLogDto {

    private String requestUri;
    private String requestBody;
    private String responseBody;
    private String requestHeaders;
    private int    responseHttpStatus;

    public AnalyticsLogDto(String requestUri, String requestBody, String responseBody, Map<String, String> requestHeaders, int responseHttpStatus) {
        this.requestUri = requestUri;
        this.requestBody = StringUtils.isNotBlank(requestBody) ? requestBody.replaceAll(Constants.Regex.NEWLINE_REGEX, StringUtils.EMPTY) : StringUtils.EMPTY;
        this.responseBody = StringUtils.isNotBlank(responseBody) ? responseBody.replaceAll(Constants.Regex.NEWLINE_REGEX, StringUtils.EMPTY) : StringUtils.EMPTY;
        this.requestHeaders = GsonSerializer.getInstance().toJson(requestHeaders);
        this.responseHttpStatus = responseHttpStatus;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(requestUri).append(Constants.Symbols.PIPE)
                .append(requestHeaders).append(Constants.Symbols.PIPE)
                .append(requestBody).append(Constants.Symbols.PIPE)
                .append(responseBody).append(Constants.Symbols.PIPE)
                .append(responseHttpStatus)
                .toString();


    }
}
