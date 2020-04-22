package com.jp.kafkaconsumer.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jp.kafkaconsumer.entity.CarLocation;
import com.jp.kafkaconsumer.error.handler.GlobalErrorHandler;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.TopicPartition;
import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.SeekToCurrentErrorHandler;
import org.springframework.kafka.listener.adapter.RecordFilterStrategy;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

@Configuration
@RequiredArgsConstructor
public class KafkaConfig {

    private final KafkaProperties kafkaProperties;

    @Bean
    public ConsumerFactory<Object, Object> consumerFactory() {
        var properties = kafkaProperties.buildConsumerProperties();
        properties.put(ConsumerConfig.METADATA_MAX_AGE_CONFIG, "120000");
        return new DefaultKafkaConsumerFactory<>(properties);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<Object, Object> farLocationContainerFactory(ConcurrentKafkaListenerContainerFactoryConfigurer configurer) {
        var factory = new ConcurrentKafkaListenerContainerFactory<>();
        configurer.configure(factory, consumerFactory());
        factory.setRecordFilterStrategy(new RecordFilterStrategy<Object, Object>() {
            ObjectMapper objectMapper = new ObjectMapper();

            @Override
            public boolean filter(ConsumerRecord<Object, Object> consumerRecord) {
                try {
                    var carLocation = objectMapper.readValue(consumerRecord.value().toString(), CarLocation.class);
                    return carLocation.getDistance() <= 100;
                } catch (JsonProcessingException e) {
                    return false;
                }
            }
        });
        return factory;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<Object, Object> kafkaListenerContainerFactory(ConcurrentKafkaListenerContainerFactoryConfigurer configurer) {
        var factory = new ConcurrentKafkaListenerContainerFactory<>();
        configurer.configure(factory, consumerFactory());
        factory.setErrorHandler(new GlobalErrorHandler());
        return factory;
    }

    private RetryTemplate createRetryTemplate() {
        var retryTemplate = new RetryTemplate();
        var retryPolicy = new SimpleRetryPolicy(3);
        retryTemplate.setRetryPolicy(retryPolicy);
        var backOffPolicy = new FixedBackOffPolicy();
        backOffPolicy.setBackOffPeriod(10_000);
        retryTemplate.setBackOffPolicy(backOffPolicy);
        return retryTemplate;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<Object, Object> imageRetryContainerFactory(ConcurrentKafkaListenerContainerFactoryConfigurer configurer) {
        var factory = new ConcurrentKafkaListenerContainerFactory<>();
        configurer.configure(factory, consumerFactory());
        factory.setErrorHandler(new GlobalErrorHandler());
        factory.setRetryTemplate(createRetryTemplate());
        return factory;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<Object, Object> invoiceDltContainerFactory(
            ConcurrentKafkaListenerContainerFactoryConfigurer configurer, KafkaTemplate kafkaTemplate) {
        var factory = new ConcurrentKafkaListenerContainerFactory<>();
        configurer.configure(factory, consumerFactory());
        var recoverer = new DeadLetterPublishingRecoverer(kafkaTemplate,
                (record, ex) -> new TopicPartition("inovicedlttopic", record.partition()));
        factory.getContainerProperties().setAckOnError(false);
        var errorHandler = new SeekToCurrentErrorHandler(recoverer, 5);
        factory.setErrorHandler(errorHandler);
        return factory;
    }
}