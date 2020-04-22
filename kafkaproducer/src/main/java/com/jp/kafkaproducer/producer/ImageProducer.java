package com.jp.kafkaproducer.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jp.kafkaproducer.entity.Image;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

//@Service
@RequiredArgsConstructor
public class ImageProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    private ObjectMapper objectMapper = new ObjectMapper();

    public void send(Image image) throws JsonProcessingException {
        var json = objectMapper.writeValueAsString(image);
        kafkaTemplate.send("imagetopic", image.getType(), json);
    }
}