package com.ordering_system.service.impl;

import com.ordering_system.model.domain.OrderEntity;
import com.ordering_system.model.dto.Order;
import com.ordering_system.repository.FoodRepository;
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
    private final FoodRepository foodRepository;

    @Autowired
    public OrderServiceImpl(Converter converter, OrderRepository orderRepository, FoodRepository foodRepository) {
        this.converter = converter;
        this.orderRepository = orderRepository;
        this.foodRepository = foodRepository;
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
        double priceSum=0;
        for (Long aLong : order.getFoodId()) {
            priceSum+=foodRepository.findFoodEntityById(aLong).getPrice();
        }
        order.setPrice(priceSum);
        orderRepository.save(converter.orderToEntity(order));
        return order;
    }

    //TODO
    @Override
    public void update(long id, Order order) {
        Validator.checkId(id);
        OrderEntity orderEntity = orderRepository.findOrderEntityById(id);
        Validator.checkEntity(order);
        Validator.checkEntity(orderEntity);


        orderRepository.save(orderEntity);
    }

    @Override
    public void delete(long id) {
        Validator.checkId(id);
        if (Validator.checkEntity(orderRepository.findOrderEntityById(id))) {
            orderRepository.deleteById(id);
        }
    }
}
