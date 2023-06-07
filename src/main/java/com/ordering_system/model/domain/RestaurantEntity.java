package com.ordering_system.model.domain;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;


import java.util.Date;
import java.util.List;

@Entity
@Table(name = "restaurant")
public class RestaurantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Nonnull
    private String name;
    @Nonnull
    private String tin;
    @OneToMany(mappedBy = "restaurantEntity",
            cascade = CascadeType.ALL)
    private List<FoodEntity> foodEntityList;
    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "address_id")

    private AddressEntity address;
    @ManyToOne
    @JoinColumn(name = "manager_id")

    private ManagerEntity manager;
    @Nonnull
    private Date foundDate;
    @Nonnull
    private Date registrationDate;
    @Nonnull
    private String phoneNumber;
    public RestaurantEntity() {
    }
    public RestaurantEntity(long id, String name, String tin, AddressEntity address, ManagerEntity manager, Date foundDate, Date registrationDate, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.tin = tin;
        this.address = address;
        this.manager = manager;
        this.foundDate = foundDate;
        this.registrationDate = registrationDate;
        this.phoneNumber = phoneNumber;
    }

    public List<FoodEntity> getFoodEntityList() {
        return foodEntityList;
    }

    public void setFoodEntityList(List<FoodEntity> foodEntityList) {
        this.foodEntityList = foodEntityList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
    }

    public ManagerEntity getManager() {
        return manager;
    }

    public void setManager(ManagerEntity manager) {
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
}
