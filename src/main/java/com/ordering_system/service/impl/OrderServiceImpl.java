package com.ordering_system.service.impl;

import com.ordering_system.model.domain.FoodEntity;
import com.ordering_system.model.domain.OrderEntity;
import com.ordering_system.model.dto.Food;
import com.ordering_system.model.dto.FoodDto;
import com.ordering_system.model.dto.Order;
import com.ordering_system.model.enumeration.OrderStatus;
import com.ordering_system.repository.FoodRepository;
import com.ordering_system.repository.OrderRepository;
import com.ordering_system.repository.RestaurantRepository;
import com.ordering_system.service.OrderService;
import com.ordering_system.service.converter.Converter;
import com.ordering_system.service.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final Converter converter;
    private final OrderRepository orderRepository;
    private final FoodRepository foodRepository;
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public OrderServiceImpl(Converter converter, OrderRepository orderRepository, FoodRepository foodRepository, RestaurantRepository restaurantRepository) {
        this.converter = converter;
        this.orderRepository = orderRepository;
        this.foodRepository = foodRepository;
        this.restaurantRepository = restaurantRepository;
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
    public Order save(List<FoodDto> foodDtoList) {
        List<Long> foodListIds = new ArrayList<>();
        for (FoodDto foodDto : foodDtoList) {
            foodListIds.add(foodDto.getId());
        }

        double priceSum=0;
        Order order = new Order();
        for (long foodId : foodListIds) {
            priceSum += foodRepository.findFoodEntityById(foodId).getPrice();
        }
        order.setOrderStatus(OrderStatus.ACCEPTED);
        order.setRestaurantName(foodRepository.findFoodEntityById(foodListIds.get(0)).getName());
        order.setPrice(priceSum);
        order.setFoodIdList(foodListIds);
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
