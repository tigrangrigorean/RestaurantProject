package com.ordering_system.service.impl;

import com.ordering_system.model.domain.OrderEntity;
import com.ordering_system.model.domain.RestaurantEntity;
import com.ordering_system.model.domain.UserEntity;
import com.ordering_system.model.dto.*;
import com.ordering_system.model.enumeration.OrderStatus;
import com.ordering_system.model.exception.NoEnoughMoneyException;
import com.ordering_system.repository.FoodRepository;
import com.ordering_system.repository.OrderRepository;
import com.ordering_system.repository.RestaurantRepository;
import com.ordering_system.repository.UserRepository;
import com.ordering_system.service.OrderService;
import com.ordering_system.service.converter.Converter;
import com.ordering_system.service.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ordering_system.service.mailsender.GetMail;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final Converter converter;
    private final OrderRepository orderRepository;
    private final FoodRepository foodRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;
    private final UserServiceImpl userService;
    private final RestaurantServiceImpl restaurantService;
    private final GetMail getMail;

    @Autowired
    public OrderServiceImpl(Converter converter, OrderRepository orderRepository,
                            FoodRepository foodRepository, UserRepository userRepository,
                            RestaurantRepository restaurantRepository, GetMail getMail,
                            UserServiceImpl userService, RestaurantServiceImpl restaurantService) {
        this.converter = converter;
        this.orderRepository = orderRepository;
        this.foodRepository = foodRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
        this.getMail = getMail;
        this.userService = userService;
        this.restaurantService = restaurantService;
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
    public Order save(List<FoodDto> foodDtoList, Address address) {
        List<Long> foodListIds = new ArrayList<>();
        for (FoodDto foodDto : foodDtoList) {
            foodListIds.add(foodDto.getId());
        }
        double priceSum = 0;
        Order order = new Order();
        for (long foodId : foodListIds) {
            priceSum += foodRepository.findFoodEntityById(foodId).getPrice();
        }
        long id = userRepository.findUserEntityByEmail(getMail.mail).getId();
        double discount = priceSum / 100 * getDiscount(id);
        UserEntity userEntity = userRepository.findUserEntityById(id);
        order.setUserId(id);
        order.setOrderStatus(OrderStatus.ACCEPTED);
        order.setRestaurantName(foodRepository.findFoodEntityById(foodListIds.get(0)).getName());
        order.setPrice(priceSum);
        order.setDiscountedPrice(priceSum - discount);
        order.setDiscount(discount);
        order.setDeliveryCost(priceSum > 10000 ? 0.0 : 1000);
        order.setFoodIdList(foodListIds);
        order.setDate(LocalDate.now());
        order.setAddressToDelivery(Validator.checkAddress(address));
        if (!Validator.checkCard(userEntity.getCardNumber())) {
            throw new NoEnoughMoneyException("Incorrect card number");
        }
        orderRepository.save(converter.orderToEntity(order));
        doPay(order);
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
            orderRepository.deleteByCondition(id);
            orderRepository.deleteById(id);
        }
    }

    @Override
    public double getDiscount(long userId) {
        List<OrderEntity> orders = orderRepository.findOrderEntitiesByUserId(userId);
        double sum = 0;
        for (OrderEntity order : orders) {
            if (order.getDate().isAfter(LocalDate.now().minusDays(30))) {
                sum += order.getTotalPrice();
            }
        }
        if (sum > 1000) {
            return 10;
        }
        return 0;
    }

    public void doPay(Order order) {
        UserEntity userEntity = userRepository.findUserEntityById(order.getUserId());
        RestaurantEntity restaurantEntity = restaurantRepository.findRestaurantEntityByName(order.getRestaurantName());
        double restaurantBalance = restaurantRepository.findRestaurantEntityByName(order.getRestaurantName()).getBalance();
        User user = converter.entityToUser(userEntity);
        Restaurant restaurant = converter.entityToRestaurant(restaurantEntity);
        restaurant.setBalance(restaurantBalance + order.getPrice());
        userService.update(userEntity.getEmail(), user);
        restaurantService.update(restaurantEntity.getId(), restaurant);
    }
}
