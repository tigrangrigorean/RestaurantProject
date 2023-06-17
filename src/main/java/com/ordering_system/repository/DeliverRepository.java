package com.ordering_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ordering_system.model.domain.DeliverEntity;

@Repository
public interface DeliverRepository extends JpaRepository<DeliverEntity,Long>{
	DeliverEntity findDeliverEntityById(long id);
}
