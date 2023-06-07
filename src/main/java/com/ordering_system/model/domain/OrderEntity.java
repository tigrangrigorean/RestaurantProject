package com.ordering_system.model.domain;//package com.domain.entity;
//
//import com.domain.enumeration.OrderStatus;
//import com.domain.model.Food;
//import jakarta.persistence.*;
//
//import java.util.List;
//
//@Entity
//@Table(name = "orders")
//public class OrderEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//    private double price;
//    @ManyToOne
//    private FoodEntity foodList;
//    @Enumerated(EnumType.STRING)
//    private OrderStatus orderStatus;
//
//    public OrderEntity(long id, double price, List<FoodEntity> foodList, OrderStatus orderStatus) {
//        this.id = id;
//        this.price = price;
//        this.foodList = foodList;
//        this.orderStatus = orderStatus;
//    }
//
//    public OrderEntity() {
//    }
//
//    public List<FoodEntity> getFoodList() {
//        return foodList;
//    }
//
//    public void setFoodList(List<FoodEntity> foodList) {
//        this.foodList = foodList;
//    }
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public double getPrice() {
//        return price;
//    }
//
//    public void setPrice(double price) {
//        this.price = price;
//    }
//
////    public List<FoodEntity> getFoodList() {
////        return foodList;
////    }
////
////    public void setFoodList(List<FoodEntity> foodList) {
////        this.foodList = foodList;
////    }
//
//    public OrderStatus getOrderStatus() {
//        return orderStatus;
//    }
//
//    public void setOrderStatus(OrderStatus orderStatus) {
//        this.orderStatus = orderStatus;
//    }
//}
//
//
