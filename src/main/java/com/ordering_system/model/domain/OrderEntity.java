package com.ordering_system.model.domain;


import com.ordering_system.model.enumeration.OrderStatus;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long userId;
    private double price;
    @ManyToMany
    private List<FoodEntity> foodList;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    private String restaurantName;


    public OrderEntity(long id, double price, List<FoodEntity> foodList,
                       OrderStatus orderStatus, String restaurantName) {
        this.id = id;
        this.price = price;
        this.foodList = foodList;
        this.orderStatus = orderStatus;
        this.restaurantName = restaurantName;
    }

    public OrderEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<FoodEntity> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<FoodEntity> foodList) {
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
}


