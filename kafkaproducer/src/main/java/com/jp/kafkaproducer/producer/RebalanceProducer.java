package com.jp.kafkaproducer.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

//@Service
@RequiredArgsConstructor
public class RebalanceProducer {

    private final KafkaTemplate<String, String > kafkaTemplate;

    private int i = 0;

    @Scheduled(fixedRate = 1000)
    public void sendMessage() {
        i++;
        kafkaTemplate.send("rebalancetopic", "Counter is " + i);
    }
}