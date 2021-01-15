package com.example.vision.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Controller
public class MainController {
    S3 s3Client;
    ComputerVision vision;

    @RequestMapping("/")
    public String home(Model model) throws IOException {
        s3Client = new S3();
        List<String> list = s3Client.getListBuckets();
        model.addAttribute("buckets", list);

        model.addAttribute("nameBucket", "mail.s3");

        list = s3Client.getFileListFromBucket("mail.s3");
        model.addAttribute("images", list);

        LinkedList<String> response = new ComputerVision().detectOnImage("test1.jpg");
        model.addAttribute("responses", response);

        return "index";
    }

    public String getResponse(String response){
        String s = "";
        s = response.substring(response.indexOf("label"));
        return s;
    }
}