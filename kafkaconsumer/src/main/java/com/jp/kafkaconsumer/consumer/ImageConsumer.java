package com.jp.kafkaconsumer.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jp.kafkaconsumer.entity.Image;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.net.http.HttpConnectTimeoutException;

//@Service
public class ImageConsumer {

    private static final Logger logger = LoggerFactory.getLogger(FoodOrderConsumer.class);

    private ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "imagetopic", containerFactory = "imageRetryContainerFactory")
    public void consume(String message) throws JsonProcessingException, HttpConnectTimeoutException {
        var image = objectMapper.readValue(message, Image.class);

        if (image.getType().equalsIgnoreCase("svg")) {
            throw new HttpConnectTimeoutException("Simulate failed API call");
        }

        logger.info("Processing image: {}", image);
    }
}