package com.ordering_system.service.impl;

import com.ordering_system.model.domain.OrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ordering_system.model.domain.DeliveryEntity;
import com.ordering_system.model.dto.Delivery;
import com.ordering_system.model.enumeration.OrderStatus;
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
	private final UserRepository userRepository;
	private final OrderRepository orderRepository;
	private final OrderServiceImpl orderService;
	
	@Autowired
	public DeliveryServiceImpl(
			Converter converter,
			DeliverRepository deliverRepository,
			UserRepository userRepository,
			OrderRepository orderRepository,
			OrderServiceImpl orderService) {
		this.converter = converter;
		this.deliverRepository = deliverRepository;
		this.userRepository = userRepository;
		this.orderRepository = orderRepository;
		this.orderService = orderService;
	}

	@Override
	public Delivery getById(long id) {
		Validator.checkId(id);
		Validator.checkEntity(deliverRepository.findDeliverEntityById(id));
		return converter.entityToDeliver(deliverRepository.findDeliverEntityById(id));
	}

	@Override
	public Delivery save(Delivery delivery) {
		Validator.checkEntity(delivery);
		Validator.checkId(delivery.getUserId());
		Validator.checkId(delivery.getOrderId());
		deliverRepository.save(converter.deliverToEntity(delivery));
		OrderEntity orderEntity = orderRepository.findOrderEntityById(delivery.getOrderId());
		orderEntity.setOrderStatus(OrderStatus.IN_DELIVERY);
		orderRepository.save(orderEntity);
		return delivery;
	}

	@Override
	public void update(long id, Delivery delivery) {
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
	}

	@Override
	public void updateStatusToDelivered(Delivery delivery){
		OrderEntity orderEntity = orderRepository.findOrderEntityById(delivery.getOrderId());
		orderEntity.setOrderStatus(OrderStatus.DELIVERED);
		orderService.update(delivery.getOrderId(), converter.entityToOrder(orderEntity));
	}

	@Override
	public void delete(long id) {
		Validator.checkId(id);
		if(Validator.checkEntity(deliverRepository.findDeliverEntityById(id))) {
		deliverRepository.deleteById(id);
		}
	}
	

}
