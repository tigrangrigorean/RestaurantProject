package com.ordering_system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ordering_system.model.domain.DeliverEntity;
import com.ordering_system.model.dto.Deliver;
import com.ordering_system.model.dto.Order;
import com.ordering_system.model.enumeration.OrderStatus;
import com.ordering_system.repository.DeliverRepository;
import com.ordering_system.repository.OrderRepository;
import com.ordering_system.repository.UserRepository;
import com.ordering_system.service.DeliverService;
import com.ordering_system.service.converter.Converter;
import com.ordering_system.service.validator.Validator;

@Service
public class DeliverServiceImpl implements DeliverService {
	
	private final Converter converter;
	private final DeliverRepository deliverRepository;
	private final UserRepository userRepository;
	private final OrderRepository orderRepository;
	private final OrderServiceImpl orderService;
	
	@Autowired
	public DeliverServiceImpl(
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
	public Deliver getById(long id) {
		Validator.checkId(id);
		Validator.checkEntity(deliverRepository.findDeliverEntityById(id));
		return converter.entityToDeliver(deliverRepository.findDeliverEntityById(id));
	}

	@Override
	public Deliver save(Deliver deliver) {
		Validator.checkEntity(deliver);
		Validator.checkId(deliver.getUserId());
		Validator.checkId(deliver.getOrderId());
		deliverRepository.save(converter.deliverToEntity(deliver));
		Order order = converter.entityToOrder(orderRepository.findOrderEntityById(deliver.getOrderId()));
		order.setOrderStatus(OrderStatus.IN_DELIVERY);
		orderService.update(deliver.getOrderId(), order);
		return deliver;
	}

	@Override
	public void update(long id, Deliver deliver) {
		DeliverEntity deliverEntity = deliverRepository.findDeliverEntityById(id);
		Validator.checkEntity(deliver);
		Validator.checkEntity(deliverEntity);
		Validator.checkId(id);
		Validator.checkEntity(deliver);
		if(Validator.checkId(deliver.getUserId())) {
			deliverEntity.setUserEntity(userRepository.findUserEntityById(deliver.getUserId()));
		}
		if(Validator.checkId(deliver.getOrderId())) {
			deliverEntity.setOrderEntity(orderRepository.findOrderEntityById(deliver.getOrderId()));
		}
	}

	@Override
	public void delete(long id) {
		Validator.checkId(id);
		if(Validator.checkEntity(deliverRepository.findDeliverEntityById(id))) {
		deliverRepository.deleteById(id);
		}
	}
	

}
