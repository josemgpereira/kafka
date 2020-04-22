package com.jp.kafkaproducer.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jp.kafkaproducer.entity.Commodity;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

//@Service
@RequiredArgsConstructor
public class CommodityProducer {

    private final KafkaTemplate<String, String > kafkaTemplate;

    private ObjectMapper objectMapper = new ObjectMapper();

    public void sendMessage(Commodity commodity) throws JsonProcessingException {
        var json = objectMapper.writeValueAsString(commodity);
        kafkaTemplate.send("commoditytopic", commodity.getName(), json);
    }
}