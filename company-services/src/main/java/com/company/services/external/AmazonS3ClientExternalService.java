package com.company.services.external;

import com.amazonaws.services.s3.model.PutObjectRequest;

/**
 * @author mukulbansal
 */
public interface AmazonS3ClientExternalService {

    void deleteAmazonS3Object(String bucketName, String key) ;

    void uploadAmazonS3Object(PutObjectRequest putObjectRequest) ;
}
