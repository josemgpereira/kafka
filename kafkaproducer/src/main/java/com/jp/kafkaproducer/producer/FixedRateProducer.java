package com.jp.kafkaproducer.producer;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

//@Service
@RequiredArgsConstructor
public class FixedRateProducer {

    private static final Logger logger = LoggerFactory.getLogger(FixedRateProducer.class);

    private final KafkaTemplate<String, String > kafkaTemplate;

    private int i = 0;

    @Scheduled(fixedRate = 5000)
    public void sendMessage() {
        i++;
        logger.info("i: " + i);
        kafkaTemplate.send("fixedratetopic", "Fixed rate: " + i);
    }
}