package com.jp.kafkaproducer;

import com.jp.kafkaproducer.entity.Employee;
import com.jp.kafkaproducer.entity.FoodOrder;
import com.jp.kafkaproducer.entity.SimpleNumber;
import com.jp.kafkaproducer.producer.*;
import com.jp.kafkaproducer.service.ImageService;
import com.jp.kafkaproducer.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDate;

@SpringBootApplication
@RequiredArgsConstructor
//@EnableScheduling
public class KafkaProducerApplication implements CommandLineRunner {

    //private final HelloKafkaProducer helloKafkaProducer;
    //private final KafkaKeyProducer kafkaKeyProducer;
    //private final EmployeeJsonProducer employeeJsonProducer;
    //private final FoodOrderProducer foodOrderProducer;
    //private final SimpleNumberProducer simpleNumberProducer;
    //private final ImageService imageService;
    //private final ImageProducer imageProducer;
    private final InvoiceService invoiceService;
    private final InvoiceProducer invoiceProducer;

    public static void main(String[] args) {
        SpringApplication.run(KafkaProducerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //helloKafkaProducer.sendHello("José" + Math.random());
        /*
        for (int i = 0; i < 100; i++) {
            var key = "key-" + (i % 4);
            var data = "data " + i + " with key " + key;
            kafkaKeyProducer.send(key, data);
            Thread.sleep(500);
        }
        */
        /*
        for (int i = 1; i < 5; i++) {
            var employee = new Employee(Long.valueOf(i), "Employee" + i, LocalDate.now());
            employeeJsonProducer.sendMessage(employee);
        }
        */
        /*
        var chickenOrder = new FoodOrder(3, "Chicken");
        var fishOrder = new FoodOrder(10, "Fish");
        var pizzaOrder = new FoodOrder(5, "Pizza");
        foodOrderProducer.send(chickenOrder);
        foodOrderProducer.send(fishOrder);
        foodOrderProducer.send(pizzaOrder);

        for (int i = 100; i < 103; i++) {
            var simpleNumber = new SimpleNumber(i);
            simpleNumberProducer.send(simpleNumber);
        }
        */
        /*
        var image1 = imageService.generateImage("jpg");
        var image2 = imageService.generateImage("svg");
        var image3 = imageService.generateImage("png");
        imageProducer.send(image1);
        imageProducer.send(image2);
        imageProducer.send(image3);
        */
        /*
        for (int i = 0; i < 10; i++) {
            var invoice = invoiceService.generateInvoice();
            if (i >= 5) {
                invoice.setAmount(-1);
            }
            invoiceProducer.send(invoice);
        }
        */
    }
}