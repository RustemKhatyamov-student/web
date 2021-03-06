package com.example.vision.controllers;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class S3 {
    AmazonS3 s3Client;
    String bucketName = "mail.s3";

    public S3() {
        try {
            AWSCredentials credentials = new BasicAWSCredentials(
                    "cmruspozaUQBLPBGGvhpfR",
                    "8zSQMMifCUXkuV7M3Gw3oDC5sj1kACm5j88jcQWYPiCb");

            s3Client = AmazonS3ClientBuilder
                    .standard()
                    .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("https://hb.bizmrg.com/", "ru-msk"))
                    .withCredentials(new AWSStaticCredentialsProvider(credentials))
                    .build();

            if (!s3Client.doesBucketExist(bucketName)) {
                s3Client.createBucket(new CreateBucketRequest(bucketName));
                String bucketLocation = s3Client.getBucketLocation(new GetBucketLocationRequest(bucketName));
            }

        } catch (AmazonServiceException e) {
            e.printStackTrace();
        } catch (SdkClientException e) {
            e.printStackTrace();
        }
    }

    public void createBucket(String bucketName) {
        if (s3Client.doesBucketExist(bucketName)) {
            System.out.println("Bucket \"" + bucketName + "\" exists!");
        } else {
            try {
                s3Client.createBucket(bucketName);
            } catch (AmazonS3Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteBucket(String bucketName) {
        ObjectListing objectListing = s3Client.listObjects(bucketName);
        for (Iterator<?> iterator =
             objectListing.getObjectSummaries().iterator();
             iterator.hasNext();
        ) {
            S3ObjectSummary summary = (S3ObjectSummary) iterator.next();
            s3Client.deleteObject(bucketName, summary.getKey());
        }
        if (objectListing.isTruncated()) {
            objectListing = s3Client.listNextBatchOfObjects(objectListing);
        }
        s3Client.deleteBucket(bucketName);
    }

    public List<String> getListBuckets() {
        List<Bucket> buckets = s3Client.listBuckets();
        LinkedList<String> bucketNames = new LinkedList<String>();
        for (Bucket bucket : buckets) {
            bucketNames.add(bucket.getName());
        }
        return bucketNames;
    }

    public void loadFileToBucket(String bucketName, String file) {
        s3Client.putObject(bucketName, file, file);
        System.out.println("File \"" + file + "\" uploaded.");
    }

    public void deleteFile(String bucketName, String file) {
        s3Client.deleteObject(bucketName, file);
        System.out.println("File \"" + file + "\" deleted.");
    }

    public void downloadFileFromBucket(String bucketName, String fileKey) {
        try {
            S3Object object = s3Client.getObject(bucketName, fileKey);
            S3ObjectInputStream s3inpStream = object.getObjectContent();
            FileOutputStream fileOutputStream = new FileOutputStream(new File(fileKey));
            byte[] buffer = new byte[4096];
            int dataSize = 0;
            while ((dataSize = s3inpStream.read(buffer)) > 0) {
                fileOutputStream.write(buffer, 0, dataSize);
            }
            s3inpStream.close();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> getFileListFromBucket(String bucketName) {
        LinkedList<String> fileNames = new LinkedList<String>();
        ListObjectsV2Result result = s3Client.listObjectsV2(bucketName);
        List<S3ObjectSummary> objectSummaries = result.getObjectSummaries();
        for (S3ObjectSummary objectSummary : objectSummaries) {
            fileNames.add(objectSummary.getKey());
        }
        return fileNames;
    }
}
