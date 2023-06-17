package com.ordering_system.model.dto;

import com.ordering_system.model.enumeration.OrderStatus;

import java.time.LocalDate;
import java.util.List;


public class Order {
    private double price;
    private double discount;
    private double discountedPrice;
    private double deliveryCost;
    private List<Long> foodId;
    private OrderStatus orderStatus;
    private String restaurantName;
    private long userId;
    private LocalDate date;
    private Address addressToDelivery;

    public Order(double price, double discount, double discountedPrice, double deliveryCost, List<Long> foodId, OrderStatus orderStatus, String restaurantName, long userId, LocalDate date) {
        this.price = price;
        this.discount = discount;
        this.discountedPrice = discountedPrice;
        this.deliveryCost = deliveryCost;
        this.foodId = foodId;
        this.orderStatus = orderStatus;
        this.restaurantName = restaurantName;
        this.userId = userId;
        this.date = date;
    }

    public Order() {
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(double discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public double getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(double deliveryCost) {
        this.deliveryCost = deliveryCost;
    }

    public Address getAddressToDelivery() {
        return addressToDelivery;
    }

    public void setAddressToDelivery(Address addressToDelivery) {
        this.addressToDelivery = addressToDelivery;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public List<Long> getFoodId() {
        return foodId;
    }

    public void setFoodId(List<Long> foodId) {
        this.foodId = foodId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Long> getFoodIdList() {
        return foodId;
    }

    public void setFoodIdList(List<Long> foodId) {
        this.foodId = foodId;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
