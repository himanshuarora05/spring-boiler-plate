package com.company.services.util;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.company.core.util.ApplicationPropertyEnum;

/**
 * @author mukulbansal
 */
public class AwsS3ClientUtil {

    private static AmazonS3 amazonS3 = null;

    static {
        AWSCredentials awsCredentials = new BasicAWSCredentials(ApplicationPropertyEnum.AWS_ACCESS_KEY.getValue(),
                ApplicationPropertyEnum.AWS_SECRET_KEY.getValue());
        ClientConfiguration clientConfiguration = new ClientConfiguration() ;
        clientConfiguration.setConnectionTimeout(Integer.parseInt(ApplicationPropertyEnum.AWS_CONNECTION_TIMEOUT.getValue()));
        clientConfiguration.setSocketTimeout(Integer.parseInt(ApplicationPropertyEnum.AWS_SOCKET_TIMEOUT.getValue()));
        amazonS3 = new AmazonS3Client(awsCredentials);
    }

    public static AmazonS3 getAmazonS3Instance() {
        return amazonS3;
    }
}
