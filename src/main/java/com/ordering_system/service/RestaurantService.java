package com.ordering_system.service;

import com.ordering_system.model.dto.Restaurant;
import com.ordering_system.model.dto.RestaurantAndAddressDto;

import java.util.List;

public interface RestaurantService {
    Restaurant getById(long id);
    List<Restaurant> getAll();
    Restaurant save(RestaurantAndAddressDto restaurantAndAddressDto);
    void update(long id,Restaurant restaurant);
    public void verifyRestaurant(long id);
    void delete(long id);
}
