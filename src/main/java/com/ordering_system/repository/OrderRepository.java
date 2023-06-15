package com.ordering_system.repository;

import com.ordering_system.model.domain.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    OrderEntity findOrderEntityById(long id);
    List<OrderEntity> findOrderEntitiesByUserId(long id);
}
