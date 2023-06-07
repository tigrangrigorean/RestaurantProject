package com.ordering_system.service.impl;

import com.ordering_system.model.dto.Order;
import com.ordering_system.repository.OrderRepository;
import com.ordering_system.service.OrderService;
import com.ordering_system.service.converter.Converter;
import com.ordering_system.service.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final Converter converter;
    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(Converter converter, OrderRepository orderRepository) {
        this.converter = converter;
        this.orderRepository = orderRepository;
    }

    @Override
    public Order getById(long id) {
        Validator.checkId(id);
        Validator.checkEntity(orderRepository.findOrderEntityById(id));
        return converter.entityToOrder(orderRepository.findOrderEntityById(id));
    }

    @Override
    public List<Order> getAll() {
        return converter.entityToOrderList(orderRepository.findAll());
    }

    @Override
    public Order save(Order order) {
        Validator.checkEntity(order);
        orderRepository.save(converter.orderToEntity(order));
        return order;
    }

    @Override
    public Order update(long id, Order order) {
        return null;
    }

    @Override
    public void delete(long id) {
        Validator.checkId(id);
        if (Validator.checkEntity(orderRepository.findOrderEntityById(id))) {
            orderRepository.deleteById(id);
        }
    }
}
