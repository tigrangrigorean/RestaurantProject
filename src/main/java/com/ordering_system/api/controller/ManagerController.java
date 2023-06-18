package com.ordering_system.api.controller;

import com.ordering_system.model.dto.User;
import com.ordering_system.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manager")
public class ManagerController {
    private final UserServiceImpl managerServiceImpl;

    @Autowired
    public ManagerController(UserServiceImpl managerServiceImpl) {
        this.managerServiceImpl = managerServiceImpl;
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<User> getManagerById(@PathVariable("id") long id) {
        return ResponseEntity.ok().body(managerServiceImpl.getById(id));
    }

    @GetMapping("")
    public ResponseEntity<List<User>> getAllManagers() {
        return ResponseEntity.ok().body(managerServiceImpl.getAll());
    }


    @PostMapping("/save")
    public ResponseEntity<User> saveManager(@RequestBody User manager) {
        return ResponseEntity.status(201).body(managerServiceImpl.save(manager));
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateManagerById(@RequestParam String email, @RequestBody User manager) {
        managerServiceImpl.update(email, manager);
        return ResponseEntity.ok().body("Manager by " + email + " updated successfully");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteManager(@RequestParam("id") long id) {
        managerServiceImpl.delete(id);
        return ResponseEntity.ok().body("Manager by " + id + " deleted successfully");
    }
}
