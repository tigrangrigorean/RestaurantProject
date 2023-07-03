package com.ordering_system.model.dto;

import com.ordering_system.model.enumeration.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;


public class Order {
    private double price;
    private double discount;
    private double discountedPrice;
    private double deliveryCost;
    private List<Food> foodList;
    private OrderStatus orderStatus;
    private String restaurantName;
    private long userId;
    private LocalDateTime date;
    private String addressToDelivery;

    public Order(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public Order(double price, double discount, double discountedPrice, double deliveryCost, List<Food> foodList, OrderStatus orderStatus, String restaurantName, long userId, LocalDateTime date) {
        this.price = price;
        this.discount = discount;
        this.discountedPrice = discountedPrice;
        this.deliveryCost = deliveryCost;
        this.foodList = foodList;
        this.orderStatus = orderStatus;
        this.restaurantName = restaurantName;
        this.userId = userId;
        this.date = date;
    }

    public Order() {
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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

    public List<Food> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<Food> foodList) {
        this.foodList = foodList;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getAddressToDelivery() {
        return addressToDelivery;
    }

    public void setAddressToDelivery(String addressToDelivery) {
        this.addressToDelivery = addressToDelivery;
    }
}
