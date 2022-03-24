package com.inf600c.demo.utils;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import java.io.ByteArrayOutputStream;
import java.io.File;

public class S3Handler {
    private S3Properties amazonS3Properties;
    private AmazonS3 amazonS3Client;

    public S3Handler(S3Properties amazonS3Properties, AmazonS3 amazonS3) {
        this.amazonS3Properties = amazonS3Properties;
        this.amazonS3Client = amazonS3;
    }

    public ByteArrayOutputStream getFile(String filename) {
        String bucketName = amazonS3Properties.getBucketName();
        try {
            S3Object s3object = amazonS3Client.getObject(new GetObjectRequest(bucketName, filename));
            return Utils.toByteArrayOutputStream(s3object.getObjectContent());
        } catch (Exception e) { }

        return null;
    }

    public String uploadFile(File file) {
        String filename = Utils.genFilename(file);
        PutObjectRequest putObjectRequest = new PutObjectRequest(amazonS3Properties.getBucketName(), filename, file);
        amazonS3Client.putObject(putObjectRequest);

        return filename;
    }
}