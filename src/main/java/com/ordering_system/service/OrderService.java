package com.ordering_system.service;


import com.ordering_system.model.dto.Address;
import com.ordering_system.model.dto.Food;
import com.ordering_system.model.dto.Order;

import java.util.List;

public interface OrderService {
    Order getById(long id);
    public List<Order> getOrdersByUser(long id);
    List<Order> getAll();
    Order save(List<Food> foodList, Address address);
    void update(long id, Order order);
    void delete(long id);
     double getDiscount(long userId);
}
