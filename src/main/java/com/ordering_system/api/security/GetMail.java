package com.ordering_system.api.security;

import org.springframework.stereotype.Component;

@Component
public class GetMail {
    public   String mail;


    public void userMail(String userMail) {
        mail=userMail;
    }
}
