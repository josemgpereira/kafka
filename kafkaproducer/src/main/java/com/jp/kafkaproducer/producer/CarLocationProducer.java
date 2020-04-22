package com.jp.kafkaproducer.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jp.kafkaproducer.entity.CarLocation;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

//@Service
@RequiredArgsConstructor
public class CarLocationProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    private ObjectMapper objectMapper = new ObjectMapper();

    public void send(CarLocation carLocation) throws JsonProcessingException {
        var json = objectMapper.writeValueAsString(carLocation);
        kafkaTemplate.send("locationtopic", String.valueOf(carLocation.getId()), json);
    }
}