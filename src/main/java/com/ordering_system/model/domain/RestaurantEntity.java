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
    @JoinColumn(name = "user_id")
    private UserEntity user;
    private Date foundDate;
    private Date registrationDate;
    private String phoneNumber;
    private String email;
    private double balance;

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    private boolean activated;

    public RestaurantEntity() {
    }

    public RestaurantEntity( String name, String tin, List<FoodEntity> foodEntityList, AddressEntity address,
    		UserEntity user, Date foundDate,
    		Date registrationDate, String phoneNumber,
    		String email, double balance) {
        this.name = name;
        this.tin = tin;
        this.foodEntityList = foodEntityList;
        this.address = address;
        this.user = user;
        this.foundDate = foundDate;
        this.registrationDate = registrationDate;
        this.phoneNumber = phoneNumber;
        this.email = email.toLowerCase();
        this.balance = balance;
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

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
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
        this.email = email.toLowerCase();
    }

	/**
	 * @return the balance
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}
}
