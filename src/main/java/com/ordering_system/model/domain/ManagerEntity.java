package com.ordering_system.model.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "manager")
public class ManagerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    private String passportNumber;
    private String phoneNumber;
    private String password;
    @OneToMany(mappedBy = "manager")
    private List<RestaurantEntity> restaurantEntity;

    public ManagerEntity() {
    }

    public ManagerEntity( String firstName, String lastName, String passportNumber, String phoneNumber, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.passportNumber = passportNumber;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
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

    public List<RestaurantEntity> getRestaurantEntity() {
        return restaurantEntity;
    }

    public void setRestaurantEntity(List<RestaurantEntity> restaurantEntity) {
        this.restaurantEntity = restaurantEntity;
    }
}
