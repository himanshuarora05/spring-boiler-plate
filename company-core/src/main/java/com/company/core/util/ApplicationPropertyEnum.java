package com.company.core.util;

import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * @author mukulbansal
 */
@Getter
@AllArgsConstructor
public enum ApplicationPropertyEnum {

    ERROR_CODES_LOCATION("error.codes.location", "error-codes.properties"),
    SPRING_CONFIG_LOCATION("spring.config.location", "application-dev.properties"),
    PROFILE("profile", "prod"),
    GENERAL_USE_THREAD_POOL_MIN_THREADS("db.background.cleanup.min.threads","2"),
    GENERAL_USE_THREAD_POOL_MAX_THREADS("db.background.cleanup.min.threads","5"),
    AWS_ACCESS_KEY("aws.access.key", StringUtils.EMPTY),
    AWS_SECRET_KEY("aws.secret.key", StringUtils.EMPTY),
    AWS_CONNECTION_TIMEOUT("aws.connection.timeout", "2000"),
    AWS_SOCKET_TIMEOUT("aws.socket.timeout", "2000"),
    AWS_BUCKET_ADDRESS("aws.bucket.address", StringUtils.EMPTY),
    AWS_PROFILE_PIC_BUCKET_NAME("aws.profile.pic.bucket.name", StringUtils.EMPTY),
    AWS_PROFILE_PIC_FOLDER_NAME("aws.profile.pic.folder.name", StringUtils.EMPTY),
    MULTIPART_MAX_REQUEST_MB("multipart.max.request.mb", "20"),
    REST_CLIENT_READ_TIMEOUT("rest.client.read.timeout", "2000"),
    REST_CLIENT_CONNECT_TIMEOUT("rest.client.connect.timeout", "1000"),
    REST_CLIENT_CONNECTION_REQUEST_TIMEOUT("rest.client.connection.request.timeout", "1000"),
    FCM_GOOGLEAPIS_URL("fcm.googleapis.url", "https://fcm.googleapis.com/fcm/send"),
    FCM_KEY("fcm.key", StringUtils.EMPTY),
    APP_PROPERTY_UPDATE_SECRET("app.property.update.secret", StringUtils.EMPTY),
    ;

    private String key;
    private String value;
    private boolean isSystemConfig = Boolean.FALSE;

    ApplicationPropertyEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getValue(){
        return System.getProperty(this.key, this.value);
    }
}
