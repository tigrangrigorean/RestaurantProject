package com.ordering_system.repository;

import com.ordering_system.model.domain.AddressEntity;
import com.ordering_system.model.domain.RestaurantEntity;
import com.ordering_system.model.dto.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<RestaurantEntity,Long> {
	
    RestaurantEntity findRestaurantEntityById(long id);
    RestaurantEntity findRestaurantEntityByName(String name);
    RestaurantEntity findRestaurantEntityByNameOrTinOrEmailOrPhoneNumber(
            String name, String tin,String email,String phoneNumber);
    List<RestaurantEntity> findRestaurantEntitiesByActivatedIsTrue();
    List<RestaurantEntity> findRestaurantEntitiesByActivatedIsFalse();
    RestaurantEntity findRestaurantEntitiesByAddress_Id(long addressId);
    RestaurantEntity findRestaurantEntityByTin(String tin);
    RestaurantEntity findRestaurantEntityByPhoneNumber(String phoneNumber);
    RestaurantEntity findRestaurantEntityByEmail(String email);
}
