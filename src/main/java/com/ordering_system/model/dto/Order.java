package com.ordering_system.model.dto;

import com.ordering_system.model.enumeration.OrderStatus;

import java.time.LocalDate;
import java.util.List;


public class Order {
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    private double price;
    private List<Long> foodId;
    private OrderStatus orderStatus;
    private String restaurantName;
    private long userId;
    private LocalDate date;

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public Order(double price, List<Long> foodId, OrderStatus orderStatus, String restaurantName, long userId, LocalDate date) {
        this.price = price;
        this.foodId = foodId;
        this.orderStatus = orderStatus;
        this.restaurantName = restaurantName;
        this.userId=userId;
        this.date=date;
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

    public Order(){}

    public double getPrice() {
        return price;
    }

    public List<Long> getFoodIdList() {
        return foodId;
    }

    public void setFoodIdList(List<Long> foodId) {
        this.foodId = foodId;
    }

    public void setPrice(double price) {
        this.price = price;
    }



    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
