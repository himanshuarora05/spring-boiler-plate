package com.company.core.util;

/**
 * @author mukulbansal
 */
public class Constants {

    public interface PublicFiles {
        int DEFAULT_PUBLIC_FILES_COUNT = 0;
    }

    public interface Compliments {
        int DEFAULT_COMPLIMENTS_COUNT = 0;
    }

    public interface Rating {
        int DEFAULT_USER_RATING = 5;
    }

    public interface DateFormats{
        String DOB_DATE_FORMAT_PATTERN = "dd/MM/yyyy";
    }

    public interface Regex{
        String NEWLINE_REGEX = "[\\n\\t\\r]";
        String UNICODE_PERMISSIBLE_NAME_REGEX = "^[\\p{L} .'-]+$";
    }

    public interface Symbols {
        String OPEN_CURLY_BRACES = "{";
        String CLOSE_CURLY_BRACES = "}";
        String PIPE = "|";
        String HYPHEN = "-";
        String COLON = ":";
        String COMMA = ",";
        String SLASH = "/";
        String DOT = ".";
        String EQUALS = "=";
    }

    public interface Common {
        String X_REQUEST_ID = "x_request_id";
        String X_SPAN_ID = "x_span_id";
        String SUCCESS = "SUCCESS";
        String FAILURE = "FAILURE";
    }

    public interface CustomHeaders {
        String SESSION_TOKEN = "x-session-token";
        String CALL_ACCESS_TOKEN = "x-call-access-token";
    }

    public interface FcmNotificationData {
        String CALLER_USER_ID = "callerUserId";
        String CALLER_USERNAME = "callerUsername";
        String CALL_ACCESS_TOKEN = "callAccessToken";
        String CALL_CHANNEL_NAME = "callChannelName";
    }

}
