package com.ordering_system.service.mailsender.activation;

import com.ordering_system.model.domain.UserEntity;
import com.ordering_system.repository.UserRepository;
import com.ordering_system.service.mailsender.model.ChangePassword;
import com.ordering_system.service.mailsender.pingenerator.PinGenerator;
import com.ordering_system.service.mailsender.repository.ChangePasswordRepository;
import com.ordering_system.service.mailsender.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
@Service
public class MailActivation {
    private final PinGenerator pinGenerator;
    private final EmailSenderService emailSenderService;
    private final UserRepository userRepository;
    private final ChangePassword changePassword;
    private final ChangePasswordRepository changePasswordRepository;
@Autowired
    public MailActivation(PinGenerator pinGenerator,
                          EmailSenderService emailSenderService,
                          UserRepository userRepository, ChangePassword changePassword, ChangePasswordRepository changePasswordRepository) {
        this.pinGenerator = pinGenerator;
        this.emailSenderService = emailSenderService;
        this.userRepository = userRepository;
        this.changePassword = changePassword;
        this.changePasswordRepository = changePasswordRepository;
    }

    public void sendPin(String mail) {
        String pin = pinGenerator.generatePin();

        emailSenderService.sendEmail(mail, pin,
                "Use this code to activate your account");
        ChangePassword changePassword=changePasswordRepository.findByUserEmail(mail);
        if(changePassword!=null){
            changePassword.setPin(pin);
        }
        changePasswordRepository.save(new ChangePassword(mail,pin, LocalTime.now().plusMinutes(5).toString()));

        }

    public String activate(String email, String pin) {
        ChangePassword changePassword=changePasswordRepository.findByUserEmail(email);
        if(pin.equals(changePassword.getPin())){
            LocalTime expireTime=LocalTime.parse(changePassword.getExpirationTime());
            if (expireTime.isBefore(LocalTime.now())) {
                throw new RuntimeException("Not valid activation code");
            }
            UserEntity userEntity= userRepository.findUserEntityByEmail(email);
            if(userEntity.isActivated()){
                return "Your email is already activated";
            }
            userEntity.setActivated(true);
            userRepository.save(userEntity);
        }
        return "Email successfully activated";
    }
}
