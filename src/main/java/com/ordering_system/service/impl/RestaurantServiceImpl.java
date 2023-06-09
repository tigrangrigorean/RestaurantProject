package com.ordering_system.service.impl;


import com.ordering_system.model.domain.RestaurantEntity;
import com.ordering_system.model.dto.Restaurant;
import com.ordering_system.repository.AddressRepository;
import com.ordering_system.repository.FoodRepository;
import com.ordering_system.repository.ManagerRepository;
import com.ordering_system.repository.RestaurantRepository;
import com.ordering_system.service.RestaurantService;
import com.ordering_system.service.converter.Converter;
import com.ordering_system.service.validator.Validator;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class RestaurantServiceImpl implements RestaurantService {

    private final Converter converter;
    private final RestaurantRepository restaurantRepository;
    private final AddressRepository addressRepository;
    private final FoodRepository foodRepository;
    private final ManagerRepository managerRepository;

    @Autowired
    public RestaurantServiceImpl(Converter converter, RestaurantRepository restaurantRepository, AddressRepository addressRepository, FoodRepository foodRepository, ManagerRepository managerRepository) {
        this.converter = converter;
        this.restaurantRepository = restaurantRepository;
        this.addressRepository = addressRepository;
        this.foodRepository = foodRepository;
        this.managerRepository = managerRepository;
    }

    @Override
    public Restaurant getById(long id) {
        Validator.checkId(id);
        Validator.checkEntity(restaurantRepository.findRestaurantEntityById(id));
        return converter.entityToRestaurant(restaurantRepository.findRestaurantEntityById(id));
    }

    @Override
    public List<Restaurant> getAll() {
        return converter.entityListToRestaurantList(restaurantRepository.findAll());
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        Validator.checkEntity(restaurant);
        Validator.checkEntity(restaurant.getName());
        Validator.checkTin(restaurant.getTin());
        Validator.checkEntity(addressRepository.findAddressEntityById(restaurant.getAddressId()));
        Validator.checkEntity(managerRepository.findManagerEntityById(restaurant.getManagerId()));
        Validator.checkEmail(restaurant.getEmail());
        Validator.checkPhoneNumber(restaurant.getPhoneNumber());
        restaurantRepository.save(converter.restaurantToEntity(restaurant));
        return restaurant;
    }

    @Override
    public void update(long id, Restaurant restaurant) {
        Validator.checkId(id);
        RestaurantEntity restaurantEntity = restaurantRepository.findRestaurantEntityById(id);
        Validator.checkEntity(restaurant);
        Validator.checkEntity(restaurantEntity);
        if (restaurant.getName() != null) {
            Validator.checkName(restaurant.getName());
            restaurantEntity.setName(restaurant.getName());
        }
        if (restaurant.getTin() != null) {
        	Validator.checkTin(restaurant.getTin());
            restaurantEntity.setTin(restaurant.getTin());
        }
        if (Validator.checkId(restaurant.getAddressId())) {
        	Validator.checkEntity(addressRepository.findAddressEntityById(restaurant.getAddressId()));
            restaurantEntity.setAddress(addressRepository.findAddressEntityById(restaurant.getAddressId()));
        }
        if (Validator.checkId(restaurant.getManagerId())) {
        	Validator.checkEntity(managerRepository.findManagerEntityById(restaurant.getManagerId()));
            restaurantEntity.setManager(managerRepository.findManagerEntityById(restaurant.getManagerId()));
        }
        if (restaurant.getFoundDate() != null) {
            restaurantEntity.setFoundDate(restaurant.getFoundDate());
        }
        if (restaurant.getRegistrationDate() != null) {
            restaurantEntity.setRegistrationDate(restaurant.getRegistrationDate());
        }
        if (restaurant.getPhoneNumber() != null) {
        	Validator.checkPhoneNumber(restaurant.getPhoneNumber());
            restaurantEntity.setPhoneNumber(restaurant.getPhoneNumber());
        }
        if (restaurant.getEmail() != null) {
            Validator.checkEmail(restaurant.getEmail());
            restaurantEntity.setEmail(restaurant.getEmail());
        }
        restaurantRepository.save(restaurantEntity);
    }

    @Override
    public void delete(long id) {
        Validator.checkId(id);
        if (Validator.checkEntity(restaurantRepository.findRestaurantEntityById(id))) {
            restaurantRepository.deleteById(id);
        }
    }
}
