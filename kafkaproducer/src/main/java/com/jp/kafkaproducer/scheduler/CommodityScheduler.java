package com.jp.kafkaproducer.scheduler;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.jp.kafkaproducer.entity.Commodity;
import com.jp.kafkaproducer.producer.CommodityProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

//@Service
@RequiredArgsConstructor
public class CommodityScheduler {

    private final CommodityProducer producer;
    private RestTemplate restTemplate = new RestTemplate();

    @Scheduled(fixedRate = 5000)
    public void fetchCommodities() {
        var commodities = restTemplate.exchange("http://localhost:8081/api/v1/commodity", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Commodity>>() {
                }).getBody();

        commodities.forEach(c -> {
            try {
                producer.sendMessage(c);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });
    }
}