package com.jp.kafkaconsumer.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jp.kafkaconsumer.entity.CarLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

//@Service
public class CarLocationConsumer {

    private static final Logger logger = LoggerFactory.getLogger(CarLocationConsumer.class);

    ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "locationtopic", groupId = "all-location")
    public void listenAll(String message) throws JsonProcessingException {
        var carLocation = objectMapper.readValue(message, CarLocation.class);
        logger.info("listenAll: {}", carLocation);
    }

    @KafkaListener(topics = "locationtopic", groupId = "far-location", containerFactory = "farLocationContainerFactory")
    public void listenFar(String message) throws JsonProcessingException {
        var carLocation = objectMapper.readValue(message, CarLocation.class);
        logger.info("listenFar: {}", carLocation);
    }
}