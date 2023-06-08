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
            restaurantEntity.setName(restaurant.getName());
        }
        if (restaurant.getTin() != null) {
            restaurantEntity.setTin(restaurant.getTin());
        }
        if (addressRepository.findAddressEntityById(restaurant.getAddressId())!=null) {
            restaurantEntity.setAddress(addressRepository.findAddressEntityById(restaurant.getAddressId()));
        }
        if (managerRepository.findManagerEntityById(restaurant.getManagerId()) != null) {
            restaurantEntity.setManager(managerRepository.findManagerEntityById(restaurant.getManagerId()));
        }
        if (restaurant.getFoundDate() != null) {
            restaurantEntity.setFoundDate(restaurant.getFoundDate());
        }
        if (restaurant.getRegistrationDate() != null) {
            restaurantEntity.setRegistrationDate(restaurant.getRegistrationDate());
        }
        if (restaurant.getPhoneNumber() != null) {
            restaurantEntity.setPhoneNumber(restaurant.getPhoneNumber());

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
