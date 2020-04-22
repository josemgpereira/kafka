package com.jp.kafkaconsumer.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

//@Service
public class HelloKafkaConsumer {

    private static final Logger logger = LoggerFactory.getLogger(FixedRateConsumer.class);

    @KafkaListener(topics = "hellotopic")
    public void consume(String message) {
        logger.info("Consuming: {}", message);
    }
}