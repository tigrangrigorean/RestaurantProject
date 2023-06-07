package com.ordering_system.service;


import com.ordering_system.model.dto.Food;

import java.util.List;

public interface FoodService {
    Food getById(long id);
    List<Food> getAllByRestaurantId(long id);
    Food save(Food food);
    void update(long id, Food food);

    void delete(long id);
}
