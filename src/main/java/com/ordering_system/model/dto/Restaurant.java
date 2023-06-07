package com.ordering_system.model.dto;


import java.util.Date;
import java.util.List;

public class Restaurant {
    private String name;
    private String tin;
    private Address address;
    private List<Food> foodList;
    private Manager manager;
    private Date foundDate;
    private Date registrationDate;
    private String phoneNumber;
    private String email;
    public Restaurant() {
    }
    public Restaurant(String name, String tin,
                      Address address,
                      Manager manager, Date foundDate,
                      Date registrationDate, String phoneNumber, String email) {
        this.name = name;
        this.tin = tin;
        this.address = address;
        this.manager = manager;
        this.foundDate = foundDate;
        this.registrationDate = registrationDate;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public List<Food> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<Food> foodList) {
        this.foodList = foodList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTin() {
        return tin;
    }

    public void setTin(String tin) {
        this.tin = tin;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public Date getFoundDate() {
        return foundDate;
    }

    public void setFoundDate(Date foundDate) {
        this.foundDate = foundDate;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
