package com.jp.kafkaproducer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FoodOrder {
    private int amount;
    private String item;
}