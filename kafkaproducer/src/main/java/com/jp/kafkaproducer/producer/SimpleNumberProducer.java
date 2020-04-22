package com.jp.kafkaproducer.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jp.kafkaproducer.entity.SimpleNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

//@Service
@RequiredArgsConstructor
public class SimpleNumberProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    private ObjectMapper objectMapper = new ObjectMapper();

    public void send(SimpleNumber simpleNumber) throws JsonProcessingException {
        var json = objectMapper.writeValueAsString(simpleNumber);
        kafkaTemplate.send("simplenumbertopic", json);
    }
}