package com.jp.kafkaconsumer.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jp.kafkaconsumer.entity.Image;
import com.jp.kafkaconsumer.entity.Invoice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

//@Service
public class InvoiceConsumer {

    private static final Logger logger = LoggerFactory.getLogger(FoodOrderConsumer.class);

    private ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "invoicetopic", containerFactory = "invoiceDltContainerFactory")
    public void consume(String message) throws JsonProcessingException {
        var invoice = objectMapper.readValue(message, Invoice.class);

        if (invoice.getAmount() < 1) {
            throw new IllegalArgumentException("Invalid amount: " + invoice.getAmount() + " for invoice: " + invoice.getNumber());
        }

        logger.info("Processing invoice: {}", invoice);
    }
}