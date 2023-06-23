package com.ordering_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@SpringBootApplication
@EnableScheduling

public class OrderingSystemApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderingSystemApplication.class);

    public static void main(String[] args) {

        LOGGER.trace("Message logged at TRACE level");
        LOGGER.debug("Message logged at DEBUG level");
        LOGGER.info("Message logged at INFO level");
        LOGGER.warn("Message logged at WARN level");
        LOGGER.error("Message logged at ERROR level");
        SpringApplication.run(OrderingSystemApplication.class, args);
    }
}