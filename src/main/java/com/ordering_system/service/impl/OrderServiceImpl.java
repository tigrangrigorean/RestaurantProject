package com.ordering_system.service.impl;

import com.ordering_system.model.domain.OrderEntity;
import com.ordering_system.model.dto.FoodDto;
import com.ordering_system.model.dto.Order;
import com.ordering_system.model.enumeration.OrderStatus;
import com.ordering_system.repository.FoodRepository;
import com.ordering_system.repository.OrderRepository;
import com.ordering_system.repository.RestaurantRepository;
import com.ordering_system.repository.UserRepository;
import com.ordering_system.service.OrderService;
import com.ordering_system.service.converter.Converter;
import com.ordering_system.service.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ordering_system.api.security.GetMail;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final Converter converter;
    private final OrderRepository orderRepository;
    private final FoodRepository foodRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;
    private final GetMail getMail;

    @Autowired
    public OrderServiceImpl(Converter converter, OrderRepository orderRepository, FoodRepository foodRepository, UserRepository userRepository, RestaurantRepository restaurantRepository, GetMail getMail) {
        this.converter = converter;
        this.orderRepository = orderRepository;
        this.foodRepository = foodRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
        this.getMail = getMail;
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
        long id=userRepository.findUserEntityByEmail(getMail.mail).getId();
        order.setUserId(id);
        order.setOrderStatus(OrderStatus.ACCEPTED);
        order.setRestaurantName(foodRepository.findFoodEntityById(foodListIds.get(0)).getName());
        order.setPrice(priceSum-(priceSum/100*getDiscount(id)));
        order.setFoodIdList(foodListIds);
        order.setDate(LocalDate.now());
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

    @Override
    public  double getDiscount(long userId) {
        List<OrderEntity> orders = orderRepository.findOrderEntitiesByUserId(userId);
        double sum = 0;
        for (OrderEntity order : orders) {
            if(order.getDate().isAfter(LocalDate.now().minusDays(30))) {
                sum += order.getPrice();
            }
        }

        if(sum>1000){
            return 10;
        }
        return 0;
    }

}
