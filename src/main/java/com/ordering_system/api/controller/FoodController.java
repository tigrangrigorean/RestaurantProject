package com.ordering_system.api.controller;


import com.ordering_system.model.dto.Food;
import com.ordering_system.service.impl.FoodServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/food")
@SecurityRequirement(name = "Bearer Authentication")
public class FoodController {
    private final FoodServiceImpl foodServiceImpl;

    @Autowired
    public FoodController(FoodServiceImpl foodServiceImpl) {
        this.foodServiceImpl = foodServiceImpl;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Food> getFoodById(@PathVariable("id") long id) {
        return ResponseEntity.ok().body(foodServiceImpl.getById(id));
    }

    @GetMapping("/getAllByMenuId/{id}")
    public ResponseEntity<List<Food>> getAllFood(@PathVariable("id") long id) {
        return ResponseEntity.ok().body(foodServiceImpl.getAllByRestaurantId(id));
    }

    @PostMapping("/save")
    public ResponseEntity<Food> saveFood(@RequestBody Food food) {
        return ResponseEntity.status(201).body(foodServiceImpl.save(food));
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateFoodById(@RequestParam long id,
                                                 @RequestBody Food food) {
        foodServiceImpl.update(id, food);
        return ResponseEntity.ok().body("Food by " + id + " updated successfully");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteFood(@RequestParam("id") long id) {
        foodServiceImpl.delete(id);
        return ResponseEntity.ok().body("Food by " + id + " deleted successfully");
    }

}
