package com.ordering_system.repository;

import com.ordering_system.model.domain.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<RestaurantEntity,Long> {
	
    RestaurantEntity findRestaurantEntityById(long id);
    RestaurantEntity findRestaurantEntityByName(String name);

    List<RestaurantEntity> findRestaurantEntitiesByActivatedIsTrue();
    List<RestaurantEntity> findRestaurantEntitiesByActivatedIsFalse();
    RestaurantEntity findRestaurantEntitiesByAddress_Id(long addressId);

}
