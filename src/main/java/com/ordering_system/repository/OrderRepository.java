package com.ordering_system.repository;

import com.ordering_system.model.domain.OrderEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    OrderEntity findOrderEntityById(long id);
    List<OrderEntity> findOrderEntitiesByUserId(long id);
    void deleteAllByDateBefore(LocalDate date);
    List<OrderEntity>findAllByDateBefore(LocalDate date);
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM orders_food_list WHERE order_entity_id = :conditionParam", nativeQuery = true)
    void deleteByCondition(@Param("conditionParam") long condition);
}
