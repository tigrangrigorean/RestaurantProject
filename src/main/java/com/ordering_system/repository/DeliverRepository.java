package com.ordering_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ordering_system.model.domain.DeliveryEntity;

@Repository
public interface DeliverRepository extends JpaRepository<DeliveryEntity,Long>{
	DeliveryEntity findDeliverEntityById(long id);
}
