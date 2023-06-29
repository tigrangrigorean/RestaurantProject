package com.ordering_system.model.dto;


import java.util.Date;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

public class Restaurant {
    private String name;
    private String tin;
    @Schema(hidden = true)
    private long addressId;
    @Schema(hidden = true)
    private long managerId;
    @Schema(type = "string", format = "date", example = "2023-06-12")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date foundDate;
    @Schema(type = "string", format = "date", example = "2023-06-12")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date registrationDate;
    private String phoneNumber;
    private String email;
    @Schema(hidden = true)
    private double balance;

    public Restaurant() {
    }

    public Restaurant(String name, String tin, long addressId, long managerId, Date foundDate, Date registrationDate,
    		String phoneNumber, String email, double balance) {
        this.name = name;
        this.tin = tin;
        this.addressId = addressId;
        this.managerId = managerId;
        this.foundDate = foundDate;
        this.registrationDate = registrationDate;
        this.phoneNumber = phoneNumber;
        this.email = email.toLowerCase();
        this.balance = balance;
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

    public long getAddressId() {
        return addressId;
    }

    public void setAddressId(long addressId) {
        this.addressId = addressId;
    }

    public long getManagerId() {
        return managerId;
    }

    public void setManagerId(long managerId) {
        this.managerId = managerId;
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
