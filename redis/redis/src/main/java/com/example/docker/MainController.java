package com.example.docker;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

@RestController
public class MainController {
    @GetMapping("/")
    public String main(){

        Jedis jedis = new Jedis("localhost", 6379);

        int count;
        if (!jedis.exists("count")) {
            jedis.set("count", "0");
            count = 0;
        } else {
            Long c = jedis.incr("count");
            count = c.intValue();
        }
        jedis.close();
        jedis.shutdown();
        return String.valueOf(count);
    }
}
