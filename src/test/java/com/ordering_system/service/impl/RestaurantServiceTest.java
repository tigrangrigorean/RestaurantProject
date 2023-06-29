package com.ordering_system.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.ordering_system.model.domain.RestaurantEntity;
import com.ordering_system.model.dto.Restaurant;
import com.ordering_system.model.exception.EntityNotFoundException;
import com.ordering_system.repository.RestaurantRepository;
import com.ordering_system.service.RestaurantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;


@ExtendWith(MockitoExtension.class)

class RestaurantServiceTest {


    @Mock
    private RestaurantRepository restaurantRepository;

    @InjectMocks
    private RestaurantServiceImpl restaurantService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetById() {
        long restaurantId = 1L;
        RestaurantEntity restaurantEntity = new RestaurantEntity();
        restaurantEntity.setId(restaurantId);
        when(restaurantRepository.findRestaurantEntityById(restaurantId)).thenReturn(restaurantEntity);

        RestaurantEntity restaurant = restaurantRepository.findRestaurantEntityById(restaurantId);

        assertEquals(restaurantId, restaurant.getId());
    }



        @Test
        public void testGetAll() {
            List<Restaurant> restaurants = new ArrayList<>();


            RestaurantService restaurantService = Mockito.mock(RestaurantService.class);

            when(restaurantService.getAll()).thenReturn(restaurants);

            List<Restaurant> result = restaurantService.getAll();

            assertEquals(restaurants, result);

            verify(restaurantService, times(1)).getAll();
        }

    @Test
    void update() {
        long id = 1;
        Restaurant restaurant = new Restaurant();

        assertThrows(NullPointerException.class, () -> restaurantService.update(id, restaurant));
    }

    @Test
    void delete() {

            long id = 1;

            assertThrows(EntityNotFoundException.class, () -> restaurantService.delete(id));
    }
}