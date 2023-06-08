package com.ordering_system.service.impl;


import com.ordering_system.model.domain.FoodEntity;
import com.ordering_system.model.dto.Food;
import com.ordering_system.repository.FoodRepository;
import com.ordering_system.repository.RestaurantRepository;
import com.ordering_system.service.FoodService;
import com.ordering_system.service.converter.Converter;
import com.ordering_system.service.validator.Validator;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodServiceImpl implements FoodService {
	
    private final FoodRepository foodRepository;
    private final Converter converter;
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public FoodServiceImpl(FoodRepository foodRepository, Converter converter,
    		RestaurantRepository restaurantRepository) {
        this.foodRepository = foodRepository;
        this.converter = converter;
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public Food getById(long id) {
    	Validator.checkId(id);
    	Validator.checkEntity(foodRepository.findFoodEntityById(id));
        return converter.entityToFood(foodRepository.findFoodEntityById(id));
    }


    @Override
    public Food save(Food food) {
    	Validator.checkEntity(food);
    	Validator.checkPrice(food.getPrice());
    	Validator.checkEntity(food.getName());
    	Validator.checkEntity(food.getIngredient());
    	Validator.checkId(food.getRestaurantId());
    	Validator.checkEntity(restaurantRepository.findRestaurantEntityById(food.getRestaurantId()));
    	
        foodRepository.save(converter.foodToEntity(food));
        return food;
    }

    @Override
    public void update(long id, Food food) {
        FoodEntity foodEntity = foodRepository.findFoodEntityById(id);
        
        	Validator.checkEntity(food);
            if (food.getName() != null) {
                foodEntity.setName(food.getName());
            }
            if (food.getIngredient() != null) {
                foodEntity.setIngredient(food.getIngredient());
            }
            if (Validator.checkPrice(id)) {
                foodEntity.setPrice(food.getPrice());
        }
        foodRepository.save(foodEntity);
    }

    @Override
    public void delete(long id) {
    	Validator.checkId(id);
    	Validator.checkEntity(foodRepository.findFoodEntityById(id));
        foodRepository.deleteById(id);
    }

    @Override
    public List<Food> getAllByRestaurantId(long id) {
        return converter.entityListToFoodList(foodRepository.findFoodEntitiesByRestaurantEntityId(id));
    }
}
