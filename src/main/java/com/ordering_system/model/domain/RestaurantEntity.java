package com.ordering_system.model.domain;

import jakarta.persistence.*;



import java.util.Date;
import java.util.List;

@Entity
@Table(name = "restaurant")
public class RestaurantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String tin;
    @OneToMany(mappedBy = "restaurantEntity",
            cascade = CascadeType.ALL)
    private List<FoodEntity> foodEntityList;
    @OneToOne(cascade = CascadeType.ALL)
    private AddressEntity address;
    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.PERSIST})
    @JoinColumn(name = "manager_id")

    private ManagerEntity manager;
    private Date foundDate;
    private Date registrationDate;
    private String phoneNumber;
    private String email;

    public RestaurantEntity() {
    }

    public RestaurantEntity( String name, String tin, List<FoodEntity> foodEntityList, AddressEntity address, ManagerEntity manager, Date foundDate, Date registrationDate, String phoneNumber, String email) {
        this.name = name;
        this.tin = tin;
        this.foodEntityList = foodEntityList;
        this.address = address;
        this.manager = manager;
        this.foundDate = foundDate;
        this.registrationDate = registrationDate;
        this.phoneNumber = phoneNumber;
        this.email = email;
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

    public List<FoodEntity> getFoodEntityList() {
        return foodEntityList;
    }

    public void setFoodEntityList(List<FoodEntity> foodEntityList) {
        this.foodEntityList = foodEntityList;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
