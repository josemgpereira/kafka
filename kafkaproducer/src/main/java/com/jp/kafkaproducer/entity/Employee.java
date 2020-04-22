package com.jp.kafkaproducer.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jp.kafkaproducer.json.CustomLocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Employee {

    @JsonProperty("employee_id")
    private Long id;

    @JsonProperty("employee_name")
    private String name;

    @JsonProperty("birth_date")
    @JsonSerialize(using = CustomLocalDateSerializer.class)
    private LocalDate birthDate;
}