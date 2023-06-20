package com.ordering_system.api.controller.mailsender;

import com.ordering_system.service.mailsender.activation.MailActivation;
import org.springframework.web.bind.annotation.*;
    @RestController
    @RequestMapping("/mail")
    public class MailActivationController {
        private final MailActivation mailActivation;

        public MailActivationController(MailActivation mailActivation) {
            this.mailActivation = mailActivation;
        }
        @PostMapping("/activate")
        @ResponseBody
        public String activateMail(@RequestParam(name = "email") String email,
                                   @RequestParam(name="pin") String pin) {
            return mailActivation.activate(email,pin);
        }
    }
