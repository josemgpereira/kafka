package com.jp.kafkaproducer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Invoice {

    private long number;
    private double amount;
    private String currency;
}