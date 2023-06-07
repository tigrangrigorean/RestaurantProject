package com.ordering_system.repository;

import com.ordering_system.model.domain.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<RestaurantEntity,Long> {
    RestaurantEntity findRestaurantEntityById(long id);
}
