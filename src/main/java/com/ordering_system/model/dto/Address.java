package com.ordering_system.model.dto;


import java.util.Objects;

import com.ordering_system.model.exception.IncorrectAddressException;

public class Address {


    private String city;
    private String street;
    private String building;
    private String apartment;

    public Address(String city, String street, String building, String apartment) {
        this.city = city;
        this.street = street;
        this.building = building;
        this.apartment = apartment;
    }
    public Address(){

    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    @Override
    public String toString() {
        if(street==null||building==null){
            throw new IncorrectAddressException("Incorrect address: exp 48 Ave. Mashtots, 2/1");
        }
        String address;
        address = building+" "+street+" Str.,";
        if(apartment!=null){
            address+=apartment+" ,";
        }
        address +=" Yerevan, RA";

        return address;
    }
	@Override
	public int hashCode() {
		return Objects.hash(apartment, building, city, street);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		return Objects.equals(apartment, other.apartment) && Objects.equals(building, other.building)
				&& Objects.equals(city, other.city) && Objects.equals(street, other.street);
	}
}
