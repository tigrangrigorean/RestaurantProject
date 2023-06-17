package com.ordering_system.service;

import com.ordering_system.model.dto.Deliver;

public interface DeliverService {

	Deliver getById(long id);
	Deliver save(Deliver Deliver);
	void update(long id, Deliver deliver);
	void delete(long id);
}
