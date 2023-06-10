package com.ordering_system.model.dto;

import com.ordering_system.model.enumeration.OrderStatus;

import java.util.List;


public class Order {
    private double price;
    private List<Long> foodId;
    private OrderStatus orderStatus;
    private String RestaurantName;

    public String getRestaurantName() {
        return RestaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        RestaurantName = restaurantName;
    }

    public Order(double price, List<Long> foodId, OrderStatus orderStatus, String restaurantName) {
        this.price = price;
        this.foodId = foodId;
        this.orderStatus = orderStatus;
        RestaurantName = restaurantName;
    }
    public Order(){}

    public double getPrice() {
        return price;
    }

    public List<Long> getFoodId() {
        return foodId;
    }

    public void setFoodId(List<Long> foodId) {
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
