package com.jp.kafkaconsumer.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

//@Service
public class FixedRateConsumer {

    private static final Logger logger = LoggerFactory.getLogger(FixedRateConsumer.class);


    @KafkaListener(topics = "fixedratetopic")
    public void consume(String message) {
        logger.info("Consuming: {}", message);
    }
}