package com.ordering_system.api.controller.mailsender;

import com.ordering_system.service.mailsender.service.ChangePasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/password")
public class ChangePasswordController {
    private final ChangePasswordService changePasswordService;

    @Autowired
    public ChangePasswordController(ChangePasswordService changePasswordService) {
        this.changePasswordService = changePasswordService;
    }
    @GetMapping("/reset")
    public void resetPassword(@RequestParam String mail) throws InterruptedException {
        changePasswordService.sendPin(mail);
    }
    @PostMapping("/change")
    @ResponseBody
    public void changePassword(@RequestParam(name = "email") String email,
                               @RequestParam (name = "newPassword") String newPassword,
                               @RequestParam(name="pin") String pin) {
        changePasswordService.change(email,newPassword,pin);
    }
}
