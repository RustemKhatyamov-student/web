package com.example.docker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class DockerApplication {
    public static int count = 0;

    @RequestMapping("/")
    @ResponseBody
    public String count(){
        count++;
        return "count = " + count;
    }

    public static void main(String[] args){
        SpringApplication.run(DockerApplication.class, args);
    }
}
