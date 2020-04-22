package com.jp.kafkaproducer.scheduler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jp.kafkaproducer.entity.CarLocation;
import com.jp.kafkaproducer.producer.CarLocationProducer;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

//@Service
public class CarLocationScheduler {

    private static final Logger logger = LoggerFactory.getLogger(CarLocationScheduler.class);

    @Autowired
    private CarLocationProducer carLocationProducer;

    private CarLocation carOne;
    private CarLocation carTwo;
    private CarLocation carThree;

    private CarLocationScheduler() {
        var now = System.currentTimeMillis();
        carOne = new CarLocation(1L, now, 0);
        carTwo = new CarLocation(2L, now, 110);
        carThree = new CarLocation(3L, now, 95);
    }

    @Scheduled(fixedRate = 10000)
    public void generateCarLocation() throws JsonProcessingException {
        var now = System.currentTimeMillis();
        carOne.setTimestamp(now);
        carTwo.setTimestamp(now);
        carThree.setTimestamp(now);
        carOne.setDistance(carOne.getDistance() + 1);
        carTwo.setDistance(carTwo.getDistance() - 1);
        carThree.setDistance(carThree.getDistance() + 1);
        carLocationProducer.send(carOne);
        logger.info("Sent {}", carOne);
        carLocationProducer.send(carTwo);
        logger.info("Sent {}", carTwo);
        carLocationProducer.send(carThree);
        logger.info("Sent {}", carThree);
    }
}