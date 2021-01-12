package com.example.docker;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Duration;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

@SpringBootApplication
public class KafkaApplication {
    private static int count = 0;

    public static void main(String[] args){
        SpringApplication.run(KafkaApplication.class, args);

        Properties prop = new Properties();
        prop.put("group.id", "testId");
        prop.put("bootstrap.servers", "localhost:9092");
        prop.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        prop.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(prop);
        List<String> topics = new LinkedList<String>();
        topics.add("TutorialTopic");
        kafkaConsumer.subscribe(topics);

        try{
            while(true) {
                ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(1000));
                for (ConsumerRecord<String, String> record : records) {
                    System.out.println(String.format("topic: " + record.topic() + ", message: " + record.value() + "\n"));
                }
                System.out.println("Messages processed = " + count);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            kafkaConsumer.close();
        }
    }
}