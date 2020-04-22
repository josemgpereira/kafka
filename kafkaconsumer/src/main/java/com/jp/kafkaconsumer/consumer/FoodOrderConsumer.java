package com.jp.kafkaconsumer.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jp.kafkaconsumer.entity.FoodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

//@Service
public class FoodOrderConsumer {

    private static final Logger logger = LoggerFactory.getLogger(FoodOrderConsumer.class);

    private ObjectMapper objectMapper = new ObjectMapper();

    private static final int MAX_AMOUNT_ORDER = 7;

    @KafkaListener(topics = "foodordertopic", errorHandler = "myFoodOrderErrorHandler")
    public void consume(String message) throws JsonProcessingException {
        var foodOrder = objectMapper.readValue(message, FoodOrder.class);

        if (foodOrder.getAmount() > MAX_AMOUNT_ORDER) {
            throw new IllegalArgumentException("Food order amount is too many");
        }

        logger.info("Food order valid: {}", foodOrder);
    }
}