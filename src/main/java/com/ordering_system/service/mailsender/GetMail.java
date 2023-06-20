package com.ordering_system.service.mailsender;

import org.springframework.stereotype.Component;

@Component
public class GetMail {
    private  String mail;

    public GetMail() {
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public GetMail(String mail) {
        this.mail = mail;
    }

    public void userMail(String userMail) {
        mail=userMail;
    }
}
