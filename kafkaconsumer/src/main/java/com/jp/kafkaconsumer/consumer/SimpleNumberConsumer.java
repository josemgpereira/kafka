package com.jp.kafkaconsumer.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jp.kafkaconsumer.entity.FoodOrder;
import com.jp.kafkaconsumer.entity.SimpleNumber;
import org.apache.kafka.common.requests.IsolationLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

//@Service
public class SimpleNumberConsumer {

    private static final Logger logger = LoggerFactory.getLogger(FoodOrderConsumer.class);

    private ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "simplenumbertopic")
    public void consume(String message) throws JsonProcessingException {
        var simpleNumber = objectMapper.readValue(message, SimpleNumber.class);

        if (simpleNumber.getNumber() %2 != 0) {
            throw new IllegalArgumentException("Odd number");
        }

        logger.info("Valid number: {}", simpleNumber);
    }
}