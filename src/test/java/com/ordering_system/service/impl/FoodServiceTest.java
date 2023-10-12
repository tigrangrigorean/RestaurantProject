package com.ordering_system.service.impl;

import com.ordering_system.model.domain.FoodEntity;
import com.ordering_system.model.domain.RestaurantEntity;
import com.ordering_system.model.dto.Food;
import com.ordering_system.repository.FoodRepository;
import com.ordering_system.repository.RestaurantRepository;
import com.ordering_system.service.converter.Converter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class FoodServiceTest {
    @Mock
    private FoodRepository foodRepository;

    @Mock
    private Converter converter;

    @Mock
    private RestaurantRepository restaurantRepository;

    @InjectMocks
    private FoodServiceImpl foodService;

    FoodServiceTest() {
    }

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testGetById_ValidId_ReturnsFood() {
        long id = 1;
        FoodEntity foodEntity = new FoodEntity();
        Food expectedFood = new Food();
        when(foodRepository.findFoodEntityById(id)).thenReturn(foodEntity);
        when(converter.entityToFood(foodEntity)).thenReturn(expectedFood);

        Food actualFood = foodService.getById(id);

        Assertions.assertEquals(expectedFood, actualFood);
        verify(foodRepository, times(1)).findFoodEntityById(id);
        verify(converter, times(1)).entityToFood(foodEntity);
    }

    @Test
    public void testGetAllByRestaurantId_ValidId_ReturnsFoodList() {
        long id = 1;
        List<FoodEntity> foodEntityList = new ArrayList<>();
        List<Food> expectedFoodList = new ArrayList<>();
        when(foodRepository.findFoodEntitiesByRestaurantEntityId(id)).thenReturn(foodEntityList);
        when(converter.entityListToFoodList(foodEntityList)).thenReturn(expectedFoodList);

        List<Food> actualFoodList = foodService.getAllByRestaurantId(id);

        Assertions.assertEquals(expectedFoodList, actualFoodList);
        verify(foodRepository, times(1)).findFoodEntitiesByRestaurantEntityId(id);
        verify(converter, times(1)).entityListToFoodList(foodEntityList);
    }

    @Test
    public void testSave_ValidFood_ReturnsSavedFood() {
        Food food = new Food();
        food.setName("Pizza");
        food.setIngredient("Cheese,Tomato,Pepperoni");
        food.setPrice(9.99);
        food.setRestaurantId(1);

        RestaurantEntity restaurantEntity = new RestaurantEntity();
        restaurantEntity.setActivated(true);
        when(restaurantRepository.findRestaurantEntityById(food.getRestaurantId())).thenReturn(restaurantEntity);
        when(converter.foodToEntity(food)).thenReturn(new FoodEntity());

        Food savedFood = foodService.save(food);

        Assertions.assertEquals(food, savedFood);
        verify(restaurantRepository, times(1)).findRestaurantEntityById(food.getRestaurantId());
        verify(converter, times(1)).foodToEntity(food);
        verify(foodRepository, times(1)).save(any(FoodEntity.class));
    }
    @Test
    public void testUpdate_ValidIdAndFood_UpdatesFood() {
        long id = 1;
        Food food = new Food();
        food.setName("Pizza");
        food.setIngredient("Cheese, Tomato, Pepperoni");
        food.setPrice(9.99);
        food.setRestaurantId(1);

        FoodEntity foodEntity = new FoodEntity();
        when(foodRepository.findFoodEntityById(id)).thenReturn(foodEntity);
        when(converter.foodToEntity(food)).thenReturn(foodEntity);

        foodService.update(id, food);

        verify(foodRepository, times(1)).findFoodEntityById(id);
        verify(foodRepository, times(1)).save(foodEntity);
    }


    @Test
    public void testDelete_ValidId_DeletesFood() {
        long id = 1;
        when(foodRepository.findFoodEntityById(id)).thenReturn(new FoodEntity());

        foodService.delete(id);

        verify(foodRepository, times(1)).findFoodEntityById(id);
        verify(foodRepository, times(1)).deleteById(id);
    }
}