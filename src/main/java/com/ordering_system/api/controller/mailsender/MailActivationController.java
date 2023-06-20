package com.ordering_system.api.controller.mailsender;

import com.ordering_system.service.mailsender.activation.MailActivation;
import com.ordering_system.service.mailsender.service.ChangePasswordService;
import org.springframework.web.bind.annotation.*;
    @RestController
    @RequestMapping("/mail")
    public class MailActivationController {
        private  final ChangePasswordService passwordService;
        private final MailActivation mailActivation;

        public MailActivationController(ChangePasswordService passwordService, MailActivation mailActivation) {
            this.passwordService = passwordService;
            this.mailActivation = mailActivation;
        }
        @PostMapping("/activate")
        @ResponseBody
        public String activateMail(@RequestParam(name = "email") String email,
                                   @RequestParam(name="pin") String pin) {
            return mailActivation.activate(email,pin);
        }
        @PostMapping("/send_pin")
        @ResponseBody
        public void pinForActivation(@RequestParam(name = "email") String email){
             passwordService.sendPin(email);
        }
    }
