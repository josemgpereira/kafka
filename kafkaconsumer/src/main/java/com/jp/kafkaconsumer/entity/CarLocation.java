package com.jp.kafkaconsumer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CarLocation {

    private Long id;
    private Long timestamp;
    private int distance;
}