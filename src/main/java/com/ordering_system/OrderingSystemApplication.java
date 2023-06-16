package com.ordering_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling

public class OrderingSystemApplication {

    public static void main(String[] args) {
         SpringApplication.run(OrderingSystemApplication.class, args);
    }
}