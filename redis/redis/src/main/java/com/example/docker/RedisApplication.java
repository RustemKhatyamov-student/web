package com.example.docker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class RedisApplication {
    //public static int count;
    public static void main(String[] args){
        SpringApplication.run(RedisApplication.class, args);

    }
}