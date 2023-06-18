package com.ordering_system.model.domain;


import com.ordering_system.model.enumeration.OrderStatus;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import org.hibernate.engine.internal.Cascade;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long userId;
    private double totalPrice;
    private double discount;
    private double discountedPrice;
    private double deliveryCost;
    @ManyToMany(cascade = CascadeType.REMOVE,
    fetch = FetchType.EAGER)
    private List<FoodEntity> foodList;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    private String restaurantName;
    private String addressToDelivery;
    private LocalDate date;

    public OrderEntity(long id, double totalPrice, List<FoodEntity> foodList,
                       OrderStatus orderStatus, String restaurantName, long userId, LocalDate date) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.foodList = foodList;
        this.orderStatus = orderStatus;
        this.restaurantName = restaurantName;
        this.userId = userId;
        this.date = date;
    }

    public OrderEntity() {
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
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

    public String getAddressToDelivery() {
        return addressToDelivery;
    }

    public void setAddressToDelivery(String addressToDelivery) {
        this.addressToDelivery = addressToDelivery;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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


