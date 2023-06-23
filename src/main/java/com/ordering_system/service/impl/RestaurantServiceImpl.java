package com.ordering_system.service.impl;


import com.ordering_system.model.domain.RestaurantEntity;
import com.ordering_system.model.domain.UserEntity;
import com.ordering_system.model.dto.Restaurant;
import com.ordering_system.model.enumeration.Role;
import com.ordering_system.model.exception.EntityNotFoundException;
import com.ordering_system.repository.AddressRepository;
import com.ordering_system.repository.FoodRepository;
import com.ordering_system.repository.RestaurantRepository;
import com.ordering_system.repository.UserRepository;
import com.ordering_system.service.RestaurantService;
import com.ordering_system.service.converter.Converter;
import com.ordering_system.service.validator.Validator;
import com.ordering_system.service.mailsender.GetMail;
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
    private final UserRepository userRepository;
    private final GetMail getMail;

    @Autowired
    public RestaurantServiceImpl(Converter converter, RestaurantRepository restaurantRepository, AddressRepository addressRepository, FoodRepository foodRepository, UserRepository userRepository, GetMail getMail) {
        this.converter = converter;
        this.restaurantRepository = restaurantRepository;
        this.addressRepository = addressRepository;
        this.foodRepository = foodRepository;
        this.userRepository = userRepository;
        this.getMail = getMail;
    }
    @Override
    public Restaurant getById(long id) {
        Validator.checkId(id);
        RestaurantEntity restaurantEntity = restaurantRepository.findRestaurantEntityById(id);
        Validator.checkEntity(restaurantEntity);
        return converter.entityToRestaurant(restaurantEntity);
    }

    @Override
    public List<Restaurant> getAll() {
        return converter.entityListToRestaurantList(restaurantRepository.findAll());
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        Validator.checkEntity(restaurant);
        Validator.checkName(restaurant.getName());
        Validator.checkTin(restaurant.getTin());
        Validator.checkEntity(addressRepository.findAddressEntityById(restaurant.getAddressId()));
        UserEntity userEntity=userRepository.findUserEntityById(restaurant.getManagerId());
        Validator.checkEntity(userRepository.findUserEntityById(restaurant.getManagerId()));
        if(!userEntity.getRole().equals(Role.MANAGER)){
            throw new EntityNotFoundException("Manager by id "+restaurant.getManagerId()+" not found");
        }
        Validator.checkEmail(restaurant.getEmail());
        Validator.checkPhoneNumber(restaurant.getPhoneNumber());
        restaurantRepository.save(converter.restaurantToEntity(restaurant));
        return restaurant;
    }

    @Override
    public void update(long id, Restaurant restaurant) {
        Validator validator= new Validator(userRepository, restaurantRepository, getMail);
        Validator.checkId(id);
        RestaurantEntity restaurantEntity = restaurantRepository.findRestaurantEntityById(id);
        validator.checkAccess(restaurantEntity);
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
        if (restaurant.getAddressId()>0) {
            Validator.checkEntity(addressRepository.findAddressEntityById(restaurant.getAddressId()));
            long addressId = restaurantEntity.getAddress().getId();
            restaurantEntity.setAddress(addressRepository.findAddressEntityById(restaurant.getAddressId()));
            addressRepository.delete(addressRepository.findAddressEntityById(addressId));
        }
        if (restaurant.getManagerId()>0) {
        	Validator.checkEntity(userRepository.findUserEntityById(restaurant.getManagerId()));
            restaurantEntity.setUser(userRepository.findUserEntityById(restaurant.getManagerId()));
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
        if(restaurant.getBalance() >= 0) {
        	restaurantEntity.setBalance(restaurant.getBalance());
        }
        restaurantRepository.save(restaurantEntity);
    }

    @Override
    public void delete(long id) {
        Validator.checkId(id);
        if (Validator.checkEntity(restaurantRepository.findRestaurantEntityById(id))) {
            RestaurantEntity restaurantEntity=restaurantRepository.findRestaurantEntityById(id);
            Validator validator= new Validator(userRepository, restaurantRepository, getMail);
            validator.checkAccess(restaurantEntity);
            restaurantRepository.deleteById(id);
        }
    }
}
