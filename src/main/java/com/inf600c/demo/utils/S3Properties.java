package com.inf600c.demo.utils;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.regions.Regions;

public class S3Properties {
    private static S3Properties instance = null;

    private String bucketName = "inf600c-demo";
    private String accessKey;
    private String secretKey;

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public static S3Properties getInstance() {
        if (instance == null) {
            instance = new S3Properties();
        }
        return instance;
    }

    private S3Properties() {
        this.accessKey = "";
        this.secretKey = "";
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public AmazonS3 getS3Conn() {
        return AmazonS3ClientBuilder.standard()
                .withRegion(Regions.US_EAST_1)
                .withCredentials(new AWSStaticCredentialsProvider(
                                    new BasicAWSCredentials(accessKey, secretKey)))
                .build();
    }

}