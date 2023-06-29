package com.ordering_system.service.impl;

import com.ordering_system.model.domain.*;
import com.ordering_system.model.domain.OrderEntity;
import com.ordering_system.model.domain.RestaurantEntity;
import com.ordering_system.model.domain.UserEntity;
import com.ordering_system.model.dto.*;
import com.ordering_system.model.enumeration.OrderStatus;
import com.ordering_system.model.exception.AccessDeniedException;
import com.ordering_system.model.exception.EntityNotFoundException;
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
    public List<Order> save(List<FoodDto> foodDtoList, Address address) {
        LOGGER.info("In method save in OrderServiceImpl class");
        List<FoodEntity> foodEntities = new ArrayList<>();
        List<Long> foodListIds = new ArrayList<>();
        try {
            for (FoodDto foodDto : foodDtoList) {
                foodEntities.add(foodRepository.findFoodEntityById(foodDto.getId()));
            }
        } catch (NullPointerException e) {
            throw new EntityNotFoundException("Food not found");
        }
        List<String> restaurants=new ArrayList<>();
        for (FoodEntity food : foodEntities) {
            String restaurantName=food.getRestaurantEntity().getName();
            if(!restaurants.contains(restaurantName)){
                restaurants.add(restaurantName);
            }
        }
        List<Order> orderList= new ArrayList<>();
        for (String restaurant : restaurants) {
            orderList.add(new Order(restaurant));
        }
        for (FoodEntity foodEntity : foodEntities) {
            for (Order order : orderList) {
                if(order.getRestaurantName().equals(foodEntity.getRestaurantEntity().getName())){
                    order.setPrice(order.getPrice()+foodEntity.getPrice());
                }
            }
        }
        UserEntity userEntity = userRepository.findUserEntityByEmail(getMail.getMail());
        for (Order order : orderList) {
            double discount = order.getPrice() / 100 * getDiscount(userEntity.getId());
            order.setUserId(userEntity.getId());
            order.setOrderStatus(OrderStatus.ACCEPTED);
            order.setDiscountedPrice(order.getPrice() - discount);
            order.setDiscount(discount);
            order.setDeliveryCost(order.getPrice() > 10000 ? 0.0 : 1000);
            order.setFoodIdList(foodListIds);
            order.setDate(LocalDateTime.now());
            order.setAddressToDelivery(Validator.checkAddress(address));
            if (!Validator.checkCard(userEntity.getCardNumber())) {
                throw new NotValidCardException("Incorrect card number");
            }
            orderRepository.save(converter.orderToEntity(order));
            doPay(order);
        }
        LOGGER.info("Save method passed in OrderServiceImpl class");
        return orderList;
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
        if (order.getFoodIdList() != null) {
            List<Long> ids = order.getFoodIdList();
            List<FoodEntity> foodEntityList = new ArrayList<>();
            for (Long foodId : ids) {
                foodEntityList.add(foodRepository.findFoodEntityById(foodId));
            }
            orderEntity.setFoodList(foodEntityList);
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

    public void doPay(Order order) {
        LOGGER.info("In method doPay in OrderServiceImpl class");
        RestaurantEntity restaurantEntity = restaurantRepository.findRestaurantEntityByName(order.getRestaurantName());
        double restaurantBalance = restaurantRepository.findRestaurantEntityByName(order.getRestaurantName()).getBalance();
        restaurantEntity.setBalance(restaurantBalance + order.getPrice());
        restaurantRepository.save(restaurantEntity);
        LOGGER.info("doPay method passed in OrderServiceImpl class");
    }
}
