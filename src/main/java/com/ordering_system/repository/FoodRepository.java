package com.ordering_system.repository;

import com.ordering_system.model.domain.FoodEntity;
import com.ordering_system.model.domain.RestaurantEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<FoodEntity,Long> {
    List<FoodEntity> findFoodEntitiesByRestaurantEntityId(long id);
    FoodEntity findFoodEntityById(long id);
    FoodEntity findByRestaurantEntityAndName(RestaurantEntity restaurantEntity, String name);
}
