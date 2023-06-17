package com.ordering_system.service;


import com.ordering_system.model.dto.Address;
import com.ordering_system.model.dto.Food;
import com.ordering_system.model.dto.FoodDto;
import com.ordering_system.model.dto.Order;

import java.util.List;

public interface OrderService {
    Order getById(long id);
    List<Order> getAll();
    Order save(List<FoodDto> foodDtoList, Address address);
    void update(long id, Order order);
    void delete(long id);
    public double getDiscount(long userId);
}
