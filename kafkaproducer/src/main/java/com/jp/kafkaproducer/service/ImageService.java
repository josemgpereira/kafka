package com.jp.kafkaproducer.service;

import com.jp.kafkaproducer.entity.Image;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class ImageService {

    private static int counter = 0;

    public Image generateImage(String type) {
        counter++;
        var name = "image" + counter;
        var size = ThreadLocalRandom.current().nextLong(100, 10_000);
        return new Image(name, size, type);
    }
}