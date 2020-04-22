package com.jp.kafkaproducer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Commodity {

    private String name;
    private double price;
    private String measurement;
    private long timestamp;

    public void setPrice(double price) {
        this.price = Math.round(price * 100d) / 100d;
    }
}