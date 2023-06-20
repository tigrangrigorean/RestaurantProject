package com.ordering_system.api.controller;

import java.util.List;

import com.ordering_system.model.dto.Restaurant;
import com.ordering_system.service.impl.RestaurantServiceImpl;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurant")
@SecurityRequirement(name = "Bearer Authentication")
public class RestaurantController {

    private final RestaurantServiceImpl restaurantServiceImpl;

    @Autowired
    public RestaurantController(RestaurantServiceImpl restaurantServiceImpl) {
        this.restaurantServiceImpl = restaurantServiceImpl;
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable("id") long id) {
        return ResponseEntity.ok().body(restaurantServiceImpl.getById(id));
    }

    @GetMapping("")
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        return ResponseEntity.ok().body(restaurantServiceImpl.getAll());
    }


    @PostMapping("/save")
    public ResponseEntity<Restaurant> saveRestaurant(@RequestBody Restaurant restaurant) {
        return ResponseEntity.status(201).body(restaurantServiceImpl.save(restaurant));
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateRestaurantById(@RequestParam long id, @RequestBody Restaurant restaurant) {
        restaurantServiceImpl.update(id, restaurant);
        return ResponseEntity.ok().body("Restaurant by " + id + " updated successfully");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteRestaurant(@RequestParam("id") long id) {
        restaurantServiceImpl.delete(id);
        return ResponseEntity.ok().body("Restaurant by " + id + " deleted successfully");
    }
}
