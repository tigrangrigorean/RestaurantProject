package com.ordering_system.service.impl;


import com.ordering_system.model.domain.FoodEntity;
import com.ordering_system.model.domain.RestaurantEntity;
import com.ordering_system.model.domain.UserEntity;
import com.ordering_system.model.dto.Food;
import com.ordering_system.model.enumeration.Role;
import com.ordering_system.model.exception.ActivationException;
import com.ordering_system.model.exception.EntityAlreadyExistsException;
import com.ordering_system.repository.FoodRepository;
import com.ordering_system.repository.RestaurantRepository;
import com.ordering_system.repository.UserRepository;
import com.ordering_system.service.FoodService;
import com.ordering_system.service.converter.Converter;
import com.ordering_system.service.mailsender.GetMail;
import com.ordering_system.service.validator.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodServiceImpl implements FoodService {

    private final FoodRepository foodRepository;
    private final Converter converter;
    private final RestaurantRepository restaurantRepository;
    private final Validator validator;
    private static final Logger LOGGER = LoggerFactory.getLogger(FoodServiceImpl.class);

    @Autowired
    public FoodServiceImpl(FoodRepository foodRepository, Converter converter,
                           RestaurantRepository restaurantRepository,
                           UserRepository userRepository,
                           Validator validator) {
        this.foodRepository = foodRepository;
        this.converter = converter;
        this.restaurantRepository = restaurantRepository;
        this.validator = validator;
    }

    @Override
    public Food getById(long id) {
        LOGGER.info("In method getById in FoodServiceImpl class");
        Validator.checkId(id);
        FoodEntity foodEntity = foodRepository.findFoodEntityById(id);
        Validator.checkEntity(foodEntity);
        LOGGER.info("GetById method passed in FoodServiceImpl class");
        return converter.entityToFood(foodEntity);
    }


    @Override
    public Food save(Food food) {
        LOGGER.info("In method save in FoodServiceImpl class");
        Validator.checkEntity(food);
        Validator.checkPrice(food.getPrice());
        Validator.checkName(food.getName());
        Validator.checkIngredientText(food.getIngredient());
        Validator.checkId(food.getRestaurantId());
        RestaurantEntity restaurantEntity = restaurantRepository.findRestaurantEntityById(food.getRestaurantId());
        Validator.checkEntity(restaurantEntity);
   
        validator.checkAccess(restaurantEntity);
        if(restaurantEntity.isActivated() == false) {
        	throw new ActivationException("Restaurant isn't activated");
        }
        if(foodRepository.findByRestaurantEntityAndName(restaurantEntity, food.getName()) != null) {
        	throw new EntityAlreadyExistsException("Food by entered name already exists");
        }
        foodRepository.save(converter.foodToEntity(food));
        LOGGER.info("Save method passed in FoodServiceImpl class");
        return food;
    }

    @Override
    public void update(long id, Food food) {
        LOGGER.info("In method update in FoodServiceImpl class");
        FoodEntity foodEntity = foodRepository.findFoodEntityById(id);
        RestaurantEntity restaurantEntity = restaurantRepository.findRestaurantEntityById(food.getRestaurantId());

        validator.checkAccess(restaurantEntity);
        Validator.checkEntity(food);
        if (food.getName() != null) {
            Validator.checkName(food.getName());
            foodEntity.setName(food.getName());
        }
        if (food.getIngredient() != null) {
            Validator.checkIngredientText(food.getIngredient());
            foodEntity.setIngredient(food.getIngredient());
        }
        if (food.getPrice() > 0) {
            foodEntity.setPrice(food.getPrice());
        }
        if (food.getPrice() < 0) {
            throw new RuntimeException("Price should be greater than 0");
        }
        foodRepository.save(foodEntity);
        LOGGER.info("Update method passed in FoodServiceImpl class");
    }

    @Override
    public void delete(long id) {
        LOGGER.info("In method delete in FoodServiceImpl class");
        FoodEntity foodEntity = foodRepository.findFoodEntityById(id);
        Validator.checkId(id);
        Validator.checkEntity(foodEntity);
        validator.checkAccess(foodEntity.getRestaurantEntity());
        foodRepository.deleteById(id);
        LOGGER.info("Delete method passed in FoodServiceImpl class");
    }

    @Override
    public List<Food> getAllByRestaurantId(long id) {
        LOGGER.info("In method getAllByRestaurantId in FoodServiceImpl class");
        List<Food> foodList = converter.entityListToFoodList(foodRepository.findFoodEntitiesByRestaurantEntityId(id));
        LOGGER.info("GetAllByRestaurantId method passed in FoodServiceImpl class");
        return foodList;
    }
}
