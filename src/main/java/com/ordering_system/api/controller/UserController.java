package com.ordering_system.api.controller;
import com.ordering_system.model.dto.User;
import com.ordering_system.model.enumeration.Role;
import com.ordering_system.service.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/user")
@SecurityRequirement(name = "Bearer Authentication")
public class UserController {
    private final UserServiceImpl userServiceImpl;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
        return ResponseEntity.ok().body(userServiceImpl.getById(id,Role.USER));
    }

    @GetMapping("")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok().body(userServiceImpl.getAll(Role.USER));
    }


    @PostMapping("/save")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        user.setRole(Role.USER);
        return ResponseEntity.status(201).body(userServiceImpl.save(user));
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateUserById(@RequestParam String email, @RequestBody User user) {
        userServiceImpl.update(email, user);
        return ResponseEntity.ok().body("User by " + email + " updated successfully");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(@RequestParam("id") long id) {
        userServiceImpl.delete(id,Role.USER);
        return ResponseEntity.ok().body("User by  id " + id + " deleted successfully");
    }
}
