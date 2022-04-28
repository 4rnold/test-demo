package com.example.kafkademo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class Test1 {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Test
    public void testAck() throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            kafkaTemplate.send("topic.quick.ack", i+""+"oooo");
        }
    }
}