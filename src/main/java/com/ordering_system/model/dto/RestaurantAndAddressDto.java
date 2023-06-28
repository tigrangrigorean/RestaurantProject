package com.ordering_system.model.dto;

public class RestaurantAndAddressDto {
    private Restaurant restaurant;
    private Address address;

    public RestaurantAndAddressDto() {

    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public RestaurantAndAddressDto(Restaurant restaurant, Address address) {
        this.restaurant = restaurant;
        this.address = address;
    }
}
