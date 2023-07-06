package com.ordering_system.service.impl;

import com.ordering_system.model.domain.*;
import com.ordering_system.model.domain.OrderEntity;
import com.ordering_system.model.domain.RestaurantEntity;
import com.ordering_system.model.domain.UserEntity;
import com.ordering_system.model.dto.*;
import com.ordering_system.model.enumeration.OrderStatus;
import com.ordering_system.model.exception.AccessDeniedException;
import com.ordering_system.model.exception.EntityNotFoundException;
import com.ordering_system.model.exception.InvalidOrderException;
import com.ordering_system.model.exception.NotValidCardException;
import com.ordering_system.repository.FoodRepository;
import com.ordering_system.repository.OrderRepository;
import com.ordering_system.repository.RestaurantRepository;
import com.ordering_system.repository.UserRepository;
import com.ordering_system.service.OrderService;
import com.ordering_system.service.converter.Converter;
import com.ordering_system.service.validator.Validator;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ordering_system.service.mailsender.GetMail;
import java.time.LocalDateTime;
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
    private final GetMail getMail;
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    public OrderServiceImpl(Converter converter, OrderRepository orderRepository,
                            FoodRepository foodRepository, UserRepository userRepository,
                            RestaurantRepository restaurantRepository, GetMail getMail,
                            UserServiceImpl userService) {
        this.converter = converter;
        this.orderRepository = orderRepository;
        this.foodRepository = foodRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
        this.getMail = getMail;
        this.userService = userService;
    }

    @Override
    @Transactional
    public Order getById(long id) {
        LOGGER.info("In method getById in OrderServiceImpl class");
        Validator.checkId(id);
        OrderEntity orderEntity = orderRepository.findOrderEntityById(id);
        if (userRepository.findUserEntityByEmail(getMail.getMail()).getId() != orderEntity.getUserId()) {
            throw new AccessDeniedException("User can't view other orders");
        }
        Validator.checkEntity(orderEntity);
        Order order = converter.entityToOrder(orderEntity);
        LOGGER.info("GetById method passed in OrderServiceImpl class");
        return order;
    }
    @Override
    @Transactional
    public List<Order> getOrdersByUser(long id) {
        LOGGER.info("In method getOrdersByUser in OrderServiceImpl class");
        Validator.checkId(id);
        List<OrderEntity> orderEntitylist = orderRepository.findOrderEntitiesByUserId(id);
        if (userRepository.findUserEntityByEmail(getMail.getMail()).getId() != orderEntitylist.get(0).getUserId()) {
            throw new AccessDeniedException("User can't view other orders");
        }
        List<Order> orderList = converter.entityToOrderList(orderEntitylist);
        LOGGER.info("GetOrdersByUser method passed in OrderServiceImpl class");
        return orderList;
    }
    @Override
    public List<Order> getAll() {
        LOGGER.info("In method getAll in OrderServiceImpl class");
        List<Order> orderList = converter.entityToOrderList(orderRepository.findAll());
        LOGGER.info("GetAll method passed in OrderServiceImpl class");
        return orderList;
    }
    @Override
    public Order save(List<Food> foodList, Address address) {
        OrderEntity orderEntity = new OrderEntity();
        LOGGER.info("In method save in OrderServiceImpl class");
        List<FoodEntity> foodEntityList = new ArrayList<>();
        long id = foodList.get(0).getRestaurantId();
        for (Food food : foodList) {
            if(foodRepository.findFoodEntityById(food.getId()).getRestaurantEntity().getId() == food.getRestaurantId()) {
                foodEntityList.add(foodRepository.findFoodEntityById(food.getId()));
            }
            else
                throw new InvalidOrderException("Meals must be from the same restaurant");
        }
        orderEntity.setFoodList(foodEntityList);
        orderEntity.setOrderStatus(OrderStatus.ACCEPTED);
        orderEntity.setRestaurantName(restaurantRepository.findRestaurantEntityById(foodEntityList.get(0).getRestaurantEntity().getId()).getName());
        orderEntity.setAddressToDelivery(address.toString());
        orderEntity.setDate(LocalDateTime.now());
        long userId=userRepository.findUserEntityByEmail(getMail.getMail()).getId();
        orderEntity.setUserId(userId);
        double totalPrice=0;
        for (FoodEntity foodEntity : foodEntityList) {
            totalPrice+=foodEntity.getPrice();
        }
        double discount=totalPrice*getDiscount(userId)/100;
        orderEntity.setDiscount(discount);
        orderEntity.setDiscountedPrice(totalPrice-discount);
        orderEntity.setDeliveryCost(totalPrice>10000?0:1000);
        orderEntity.setTotalPrice(totalPrice);
        orderRepository.save(orderEntity);
        doPay(orderEntity.getRestaurantName(),totalPrice-discount);
        LOGGER.info("Save method passed in OrderServiceImpl class");
        return converter.entityToOrder(orderEntity);
    }

    //TODO DELETE
    @Override
    public void update(long id, Order order) {
        LOGGER.info("In method update in OrderServiceImpl class");
        Validator.checkId(id);
        OrderEntity orderEntity = orderRepository.findOrderEntityById(id);
        Validator.checkEntity(order);
        Validator.checkEntity(orderEntity);
        if (order.getPrice() > 0) {
            orderEntity.setTotalPrice(order.getPrice());
        }
        if (order.getOrderStatus() != null) {
            orderEntity.setOrderStatus(order.getOrderStatus());
        }
        if (order.getDate() != null) {
            orderEntity.setDate(order.getDate());
        }
        if (order.getAddressToDelivery() != null) {
            orderEntity.setAddressToDelivery(order.getAddressToDelivery().toString());
        }
        if (order.getDiscount() > 0) {
            orderEntity.setDiscount(order.getDiscount());
        }
        if (order.getDiscountedPrice() > 0) {
            orderEntity.setDiscountedPrice(order.getDiscountedPrice());
        }
        if (order.getFoodList().size()>0) {

            orderEntity.setFoodList(converter.foodListToEntityList(order.getFoodList()));
        }
        if (order.getUserId() > 0) {
            orderEntity.setUserId(order.getUserId());
        }
        if (order.getDeliveryCost() > 0) {
            orderEntity.setDeliveryCost(order.getDeliveryCost());
        }
        if (order.getRestaurantName() != null) {
            orderEntity.setRestaurantName(order.getRestaurantName());
        }
        orderRepository.save(orderEntity);
        LOGGER.info("Update method passed in OrderServiceImpl class");
    }


    @Override
    public void delete(long id) {
        LOGGER.info("In method delete in OrderServiceImpl class");
        Validator.checkId(id);
        if (Validator.checkEntity(orderRepository.findOrderEntityById(id))) {
            orderRepository.deleteByCondition(id);
            orderRepository.deleteById(id);
        }
        LOGGER.info("Delete method passed in OrderServiceImpl class");
    }

    @Override
    public double getDiscount(long userId) {
        LOGGER.info("In method getDiscount in OrderServiceImpl class");
        List<OrderEntity> orders = orderRepository.findOrderEntitiesByUserId(userId);
        double sum = 0;
        for (OrderEntity order : orders) {
            if (order.getDate().isAfter(LocalDateTime.now().minusDays(30))) {
                sum += order.getTotalPrice();
            }
        }
        if (sum > 1000) {

            return 10;
        }
        LOGGER.info("GetDiscount method passed in OrderServiceImpl class");
        return 0;
    }

    public void doPay(String restaurantName,double totalPrice) {
        LOGGER.info("In method doPay in OrderServiceImpl class");
        RestaurantEntity restaurantEntity = restaurantRepository.findRestaurantEntityByName(restaurantName);
        restaurantEntity.setBalance(restaurantEntity.getBalance() + totalPrice);
        restaurantRepository.save(restaurantEntity);
        LOGGER.info("doPay method passed in OrderServiceImpl class");
    }
}
