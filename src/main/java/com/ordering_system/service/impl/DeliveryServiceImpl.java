package com.ordering_system.service.impl;

import com.ordering_system.model.domain.OrderEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ordering_system.model.domain.DeliveryEntity;
import com.ordering_system.model.dto.Delivery;
import com.ordering_system.model.dto.User;
import com.ordering_system.model.enumeration.OrderStatus;
import com.ordering_system.model.enumeration.Role;
import com.ordering_system.repository.DeliverRepository;
import com.ordering_system.repository.OrderRepository;
import com.ordering_system.repository.UserRepository;
import com.ordering_system.service.DeliveryService;
import com.ordering_system.service.converter.Converter;
import com.ordering_system.service.validator.Validator;

@Service
public class DeliveryServiceImpl implements DeliveryService {
	
	private final Converter converter;
	private final DeliverRepository deliverRepository;
	private final UserServiceImpl userService;
	private final UserRepository userRepository;
	private final OrderRepository orderRepository;
	private final OrderServiceImpl orderService;
	private static final Logger LOGGER = LoggerFactory.getLogger(DeliveryService.class);
	
	@Autowired
	public DeliveryServiceImpl(
			Converter converter,
			DeliverRepository deliverRepository,
			UserRepository userRepository,
			OrderRepository orderRepository,
			OrderServiceImpl orderService,
			UserServiceImpl userService) {
		this.converter = converter;
		this.deliverRepository = deliverRepository;
		this.userRepository = userRepository;
		this.orderRepository = orderRepository;
		this.orderService = orderService;
		this.userService = userService;
	}

	@Override
	public Delivery getById(long id) {
		LOGGER.info("In method getById in DeliveryServiceImpl class");
		Validator.checkId(id);
		Validator.checkEntity(deliverRepository.findDeliverEntityById(id));
		Delivery delivery = converter.entityToDeliver(deliverRepository.findDeliverEntityById(id));
		LOGGER.info("GetById method passed in DeliveryServiceImpl class");
		return delivery;
	}
	
	public void singUpDelivery(User user) {
		user.setRole(Role.DELIVERY);
		userService.save(user);
	}

	@Override
	public Delivery save(Delivery delivery) {
		LOGGER.info("In method save in DeliveryServiceImpl class");
		Validator.checkEntity(delivery);
		Validator.checkId(delivery.getUserId());
		Validator.checkId(delivery.getOrderId());
		deliverRepository.save(converter.deliverToEntity(delivery));
		OrderEntity orderEntity = orderRepository.findOrderEntityById(delivery.getOrderId());
		orderEntity.setOrderStatus(OrderStatus.IN_DELIVERY);
		orderRepository.save(orderEntity);
		LOGGER.info("Save method passed in DeliveryServiceImpl class");
		return delivery;
	}

	@Override
	public void update(long id, Delivery delivery) {
		LOGGER.info("In method update in DeliveryServiceImpl class");
		DeliveryEntity deliveryEntity = deliverRepository.findDeliverEntityById(id);
		Validator.checkEntity(delivery);
		Validator.checkEntity(deliveryEntity);
		Validator.checkId(id);
		Validator.checkEntity(delivery);
		if(Validator.checkId(delivery.getUserId())) {
			deliveryEntity.setUserId(delivery.getUserId());
		}
		if(Validator.checkId(delivery.getOrderId())) {
			deliveryEntity.setOrderId(delivery.getOrderId());
		}
		LOGGER.info("Update method passed in DeliveryServiceImpl class");
	}

	@Override
	public void updateStatusToDelivered(Delivery delivery){
		LOGGER.info("In method UpdateStatusToDelivered in DeliveryServiceImpl class");
		OrderEntity orderEntity = orderRepository.findOrderEntityById(delivery.getOrderId());
		orderEntity.setOrderStatus(OrderStatus.DELIVERED);
		orderService.update(delivery.getOrderId(), converter.entityToOrder(orderEntity));
		LOGGER.info("UpdateStatusToDelivered method passed in DeliveryServiceImpl class");
	}

	@Override
	public void delete(long id) {
		LOGGER.info("In method delete in DeliveryServiceImpl class");
		Validator.checkId(id);
		if(Validator.checkEntity(deliverRepository.findDeliverEntityById(id))) {
		deliverRepository.deleteById(id);
		}
		LOGGER.info("Delete method passed in DeliveryServiceImpl class");
	}
	

}
