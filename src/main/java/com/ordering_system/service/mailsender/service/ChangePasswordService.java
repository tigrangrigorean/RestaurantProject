package com.ordering_system.service.mailsender.service;

import com.ordering_system.service.mailsender.model.ChangePassword;
import com.ordering_system.service.mailsender.pingenerator.PinGenerator;
import com.ordering_system.service.mailsender.repository.ChangePasswordRepository;
import com.ordering_system.model.domain.UserEntity;
import com.ordering_system.repository.UserRepository;
import com.ordering_system.service.converter.Converter;
import com.ordering_system.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service

public class ChangePasswordService {
    private final ChangePasswordRepository changePasswordRepository;
    private final EmailSenderService emailSenderService;
    private final PinGenerator pinGenerator;
    private final UserServiceImpl userService;
    private final UserRepository userRepository;
    private final Converter converter;
    @Autowired

    public ChangePasswordService(ChangePasswordRepository changePasswordRepository, EmailSenderService emailSenderService, PinGenerator pinGenerator, UserServiceImpl userService, UserRepository userRepository, Converter converter) {
        this.changePasswordRepository = changePasswordRepository;
        this.emailSenderService = emailSenderService;
        this.pinGenerator = pinGenerator;
        this.userService = userService;
        this.userRepository = userRepository;
        this.converter = converter;
    }

    public void sendPin(String mail) {
        String pin = pinGenerator.generatePin();
        emailSenderService.sendEmail(mail, pin, "Pin for reset password");
        if(changePasswordRepository.findByUserEmail(mail)!=null){
            changePasswordRepository
                    .deleteById(changePasswordRepository
                            .findByUserEmail(mail)
                            .getId());
        }
        ChangePassword changePassword = new ChangePassword();
        changePassword.setPin(pin);
        changePassword.setUserEmail(mail);
        changePassword.setExpirationTime(LocalTime.now().plusMinutes(3).toString());
        changePasswordRepository.save(changePassword);
    }

    public static void main(String[] args) {
        System.out.println(LocalTime.now());
    }

    public ChangePassword getChangePassword(String email) {
        return changePasswordRepository.findByUserEmail(email);
    }

    public String change(String mail, String newPassword, String pin) {
        ChangePassword changePassword = getChangePassword(mail);
        if (changePassword == null) {
            throw new RuntimeException("Wrong Email");
        }
        LocalTime expireTime=LocalTime.parse(changePassword.getExpirationTime());
        if (expireTime.isBefore(LocalTime.now())) {
            throw new RuntimeException("Not valid pin");
        }
        UserEntity user = converter.userToEntity(userService.getByEmail(mail));
        user.setPassword(newPassword);
        userService.update(mail,converter.entityToUser(user));
        return "password changed successfully";
    }
}
