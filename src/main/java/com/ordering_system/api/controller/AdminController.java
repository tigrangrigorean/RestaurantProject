package com.ordering_system.api.controller;

import java.util.List;

import com.ordering_system.model.dto.Restaurant;
import com.ordering_system.model.enumeration.Role;
import com.ordering_system.repository.RestaurantRepository;
import com.ordering_system.service.converter.Converter;
import com.ordering_system.service.impl.RestaurantServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ordering_system.model.dto.User;
import com.ordering_system.service.impl.UserServiceImpl;

@RestController
@RequestMapping("/admin")
@SecurityRequirement(name = "Bearer Authentication")
public class AdminController {

    private final UserServiceImpl adminServiceImpl;
    private  final RestaurantServiceImpl restaurantService;
    private final RestaurantRepository restaurantRepository;
    private final Converter converter;


    @Autowired
    public AdminController(UserServiceImpl adminServiceImpl, RestaurantServiceImpl restaurantService, RestaurantRepository restaurantRepository, Converter converter) {
        this.adminServiceImpl = adminServiceImpl;
        this.restaurantService = restaurantService;
        this.restaurantRepository = restaurantRepository;
        this.converter = converter;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<User> getAdminById(@PathVariable("id") long id) {
        return ResponseEntity.ok().body(adminServiceImpl.getById(id,Role.ADMIN));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getAllAdmin() {
        return ResponseEntity.ok().body(adminServiceImpl.getAll(Role.ADMIN));
    }

    @GetMapping("/getNotActivatedRestaurants")
    public List<Restaurant> getAllNotActivatedRestaurants() {
        return converter.entityListToRestaurantList(restaurantRepository.findRestaurantEntitiesByActivatedIsFalse());
    }

    @PostMapping("/save")
    public ResponseEntity<User> saveAdmin(@RequestBody User admin) {
        admin.setRole(Role.ADMIN);
        return ResponseEntity.status(201).body(adminServiceImpl.save(admin));
    }
    
    @PostMapping("/getadminroleusingkey")
    public ResponseEntity<String> getAdminRoleUsingKey(@RequestBody User admin,@RequestParam String key) {
    	adminServiceImpl.getAdminRoleUsingKey(admin, key);
    	return ResponseEntity.status(201).body("Created Admin by email " + admin.getEmail());
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateAdminById(@RequestParam String email,
                                                  @RequestBody User admin) {
        adminServiceImpl.update(email, admin);
        return ResponseEntity.ok().body("Admin by " + email + " updated successfully");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteAdmin(@RequestParam("id") long id) {
        adminServiceImpl.delete(id,Role.ADMIN);
        return ResponseEntity.ok().body("Admin by " + id + " deleted successfully");
    }
    @PutMapping("/verify/restaurant")
    public ResponseEntity<String> verifyRestaurant(@RequestParam("id") long id){

        restaurantService.verifyRestaurant(id);
        return ResponseEntity.ok().body("Restaurant by id "+id+" activated successfully");
    }

}
