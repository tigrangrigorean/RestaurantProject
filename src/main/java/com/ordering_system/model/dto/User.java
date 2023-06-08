package com.ordering_system.model.dto;

import java.util.Date;


public class User {
    private String firstName;
    private String lastName;
    private long addressId;
    private Date birthday;
    private String phoneNumber;
    private String password;
    private String email;

    public User(String firstName, String lastName, long addressId, Date birthday,
                String phoneNumber, String password, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.addressId = addressId;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.email = email;
    }

    public User(){}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getAddressId() {
        return addressId;
    }

    public void setAddressId(long addressId) {
        this.addressId = addressId;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
