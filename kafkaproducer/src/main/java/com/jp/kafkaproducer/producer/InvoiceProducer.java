package com.jp.kafkaproducer.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jp.kafkaproducer.entity.Invoice;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

//@Service
@RequiredArgsConstructor
public class InvoiceProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    private ObjectMapper objectMapper = new ObjectMapper();

    public void send(Invoice invoice) throws JsonProcessingException {
        var json = objectMapper.writeValueAsString(invoice);
        kafkaTemplate.send("invoicetopic", String.valueOf(invoice.getNumber()), json);
    }
}