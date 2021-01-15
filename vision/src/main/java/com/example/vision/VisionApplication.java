package com.example.vision;

import com.example.vision.controllers.ComputerVision;
//import com.example.vision.controllers.S3;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.LinkedList;

@SpringBootApplication
public class VisionApplication {
    public static void main(String[] args) {
        SpringApplication.run(VisionApplication.class, args);

//        S3 s3Client = new S3();
//        List<String> list = s3Client.getListBuckets();
//        System.out.println("All buckets:");
//        System.out.println(list);
//        s3Client.createBucket("mail.s3");
//        System.out.println("Create bucket mail.s3.");
//        list = s3Client.getListBuckets();
//        System.out.println("All buckets:");
//        System.out.println(list);
//        System.out.println();
//        s3Client.loadFileToBucket("mail.s3", "1.png");
//        System.out.println("Load file 1.png to mail.s3.");
//        list = s3Client.getFileListFromBucket("mail.s32");
//        System.out.println("All files from mail.s32:");
//        System.out.println(list);
//        System.out.println();
//        s3Client.deleteFile("mail.s32", "1.png");
//        System.out.println("Delete file 1.png to mail.s32.");
//        list = s3Client.getFileListFromBucket("mail.s32");
//        System.out.println("All files from mail.s32:");
//        System.out.println(list);
//        System.out.println();
//        s3Client.deleteBucket("mail.s32");
//        System.out.println("Delete bucket mail.s32.");
//        list = s3Client.getListBuckets();
//        System.out.println("All buckets:");
//        System.out.println(list);

        LinkedList<String> response = new ComputerVision().detectOnImage("test1.jpg");
        System.out.println("Vision:" + response);
    }
}
