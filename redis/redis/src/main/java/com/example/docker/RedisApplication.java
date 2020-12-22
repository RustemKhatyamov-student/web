package com.example.docker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import redis.clients.jedis.Jedis;

@SpringBootApplication
public class RedisApplication {
    public static int count;
    public static void main(String[] args){
        SpringApplication.run(RedisApplication.class, args);

        Jedis jedis = new Jedis("localhost", 6379);
        System.out.println("Connected to Redis");

        if (!jedis.exists("count")) {
            jedis.set("count", "0");
            count = 0;
        } else {
            Long c = jedis.incr("count");
            count = c.intValue();
        }
        jedis.close();
        jedis.shutdown();
        System.out.println("count = " + count);
    }
}
