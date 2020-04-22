package com.jp.kafkaproducer.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jp.kafkaproducer.entity.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

//@Service
@RequiredArgsConstructor
public class EmployeeJsonProducer {

    private final KafkaTemplate<String, String > kafkaTemplate;

    private ObjectMapper objectMapper = new ObjectMapper();

    public void sendMessage(Employee employee) throws JsonProcessingException {
        var json = objectMapper.writeValueAsString(employee);
        kafkaTemplate.send("employeetopic", json);
    }
}