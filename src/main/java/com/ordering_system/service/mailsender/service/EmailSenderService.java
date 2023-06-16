package com.ordering_system.service.mailsender.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {
    private  final JavaMailSender mailSender;
@Autowired
    public EmailSenderService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }


    public void  sendEmail(String toEmail,String text,String subject){
        SimpleMailMessage message= new SimpleMailMessage();
        message.setFrom("restordering@gmail.com");
        message.setTo(toEmail);
        message.setText(text);
        message.setSubject(subject);
        mailSender.send(message);
    }
}
