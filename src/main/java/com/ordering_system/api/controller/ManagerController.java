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
@RequestMapping("/manager")
@SecurityRequirement(name = "Bearer Authentication")
public class ManagerController {
    private final UserServiceImpl managerServiceImpl;

    @Autowired
    public ManagerController(UserServiceImpl managerServiceImpl) {
        this.managerServiceImpl = managerServiceImpl;
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<User> getManagerById(@PathVariable("id") long id) {
        return ResponseEntity.ok().body(managerServiceImpl.getById(id,Role.MANAGER));
    }

    @GetMapping("")
    public ResponseEntity<List<User>> getAllManagers() {
        return ResponseEntity.ok().body(managerServiceImpl.getAll(Role.MANAGER));
    }


    @PostMapping("/save")
    public ResponseEntity<User> saveManager(@RequestBody User manager) {
        manager.setRole(Role.MANAGER);
        return ResponseEntity.status(201).body(managerServiceImpl.save(manager));
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateManagerById(@RequestParam String email, @RequestBody User manager) {
        managerServiceImpl.update(email, manager);
        return ResponseEntity.ok().body("Manager by " + email + " updated successfully");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteManager(@RequestParam("id") long id) {
        managerServiceImpl.delete(id,Role.MANAGER);
        return ResponseEntity.ok().body("Manager by " + id + " deleted successfully");
    }
}
