package com.ordering_system.service;

import com.ordering_system.model.dto.Manager;
import com.ordering_system.model.dto.Order;

import java.util.List;

public interface OrderService {
    Order getById(long id);
    List<Order> getAll();
    Order save(Order order);
    Order update(long id, Order order);
    void delete(long id);
}
