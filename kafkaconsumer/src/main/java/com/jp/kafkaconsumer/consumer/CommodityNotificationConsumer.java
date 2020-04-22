package com.jp.kafkaconsumer.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jp.kafkaconsumer.entity.Commodity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

//@Service
public class CommodityNotificationConsumer {

    private static final Logger logger = LoggerFactory.getLogger(CommodityNotificationConsumer.class);

    private ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "commoditytopic", groupId = "notification")
    public void consume(String message) throws JsonProcessingException {
        var commodity = objectMapper.readValue(message, Commodity.class);
        logger.info("Notification logic for {}", commodity);
    }
}