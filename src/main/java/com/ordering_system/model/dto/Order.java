package com.ordering_system.model.dto;

import com.ordering_system.model.enumeration.OrderStatus;

import java.util.List;


public class Order {
    private double price;
    private List<Food> foodList;
    private OrderStatus orderStatus;

    public Order(double price, List<Food> foodList, OrderStatus orderStatus) {
        this.price = price;
        this.foodList = foodList;
        this.orderStatus = orderStatus;
    }
    public Order(){}

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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
}
