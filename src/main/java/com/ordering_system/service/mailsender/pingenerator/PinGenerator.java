package com.ordering_system.service.mailsender.pingenerator;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class PinGenerator {
    public String generatePin(){
        Random random = new Random();
        return String.valueOf(random.nextInt(100000,999999));
    }
}
