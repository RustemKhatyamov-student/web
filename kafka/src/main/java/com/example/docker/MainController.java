package com.example.docker;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.util.Properties;

@RestController
public class MainController {
    private static int count = 0;

    @GetMapping("/")
    public String producer(){
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String, String>  kafkaProducer = new KafkaProducer<String, String>(props);

        try {
            kafkaProducer.send(new ProducerRecord<>("TutorialTopic", Integer.toString(count), "message - " + count));
            count++;
            //System.out.println(count);
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            kafkaProducer.close();
        }

        return String.valueOf(count);
    }
}
