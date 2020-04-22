package com.jp.kafkaconsumer.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

//@Service
public class KafkaKeyConsumer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaKeyConsumer.class);

    @KafkaListener(topics = "multipartitionstopic", concurrency = "3")
    public void consume(ConsumerRecord<String, String> message) throws InterruptedException {
        logger.info("Key: {}, Partition: {}, Message: {}", message.key(), message.partition(), message.value());
        Thread.sleep(1000);
    }
}