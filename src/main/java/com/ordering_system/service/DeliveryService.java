package com.ordering_system.service;

import com.ordering_system.model.dto.Delivery;

public interface DeliveryService {

	Delivery getById(long id);
	Delivery save(Delivery Delivery);
	void update(long id, Delivery delivery);
	void delete(long id);
	public void updateStatusToDelivered(Delivery delivery);
}
