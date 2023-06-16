package com.ordering_system.service.mailsender.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity

public class ChangePassword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String userEmail;
    private String pin;
    private String expirationTime;

    public ChangePassword(long id, String userEmail, String pin, String expirationTime) {
        this.id = id;
        this.userEmail = userEmail;
        this.pin = pin;
        this.expirationTime = expirationTime;
    }

    public ChangePassword() {
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(String expirationTime) {
        this.expirationTime = expirationTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
