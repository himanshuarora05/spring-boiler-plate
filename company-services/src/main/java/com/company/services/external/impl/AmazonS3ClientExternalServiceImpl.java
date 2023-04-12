package com.company.services.external.impl;

import com.amazonaws.services.s3.model.PutObjectRequest;
import com.company.core.util.AssertionUtil;
import com.company.services.external.AmazonS3ClientExternalService;
import com.company.services.util.AwsS3ClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author mukulbansal
 */
@Service
public class AmazonS3ClientExternalServiceImpl implements AmazonS3ClientExternalService {

    private static final Logger LOG = LoggerFactory.getLogger(AmazonS3ClientExternalServiceImpl.class);

    @Override
    public void deleteAmazonS3Object(String bucketName, String key) {
        long startTime = System.currentTimeMillis();
        AssertionUtil.notEmpty(bucketName);
        AssertionUtil.notEmpty(key);
        AwsS3ClientUtil.getAmazonS3Instance().deleteObject(bucketName, key);
        LOG.info("Time in ms taken by S3 delete- {}", System.currentTimeMillis() - startTime);
    }

    @Override
    public void uploadAmazonS3Object(PutObjectRequest putObjectRequest) {
        long startTime = System.currentTimeMillis();
        AssertionUtil.notNull(putObjectRequest);
        AwsS3ClientUtil.getAmazonS3Instance().putObject(putObjectRequest);
        LOG.info("Time in ms taken by S3 upload- {}", System.currentTimeMillis() - startTime);

    }

}
