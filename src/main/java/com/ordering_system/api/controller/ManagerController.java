package com.ordering_system.api.controller;

import com.ordering_system.model.dto.Manager;
import com.ordering_system.service.impl.ManagerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manager")
public class ManagerController {
    private final ManagerServiceImpl managerServiceImpl;

    @Autowired
    public ManagerController(ManagerServiceImpl managerServiceImpl) {
        this.managerServiceImpl = managerServiceImpl;
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<Manager> getManagerById(@PathVariable("id") long id) {
        return ResponseEntity.ok().body(managerServiceImpl.getById(id));
    }

    @GetMapping("")
    public ResponseEntity<List<Manager>> getAllManagers() {
        return ResponseEntity.ok().body(managerServiceImpl.getAll());
    }


    @PostMapping("/save")
    public ResponseEntity<Manager> saveManager(@RequestBody Manager manager) {
        return ResponseEntity.ok().body(managerServiceImpl.save(manager));
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateManagerById(@RequestParam long id, @RequestBody Manager manager) {
        managerServiceImpl.update(id, manager);
        return ResponseEntity.ok().body("Manager by " + id + " updated successfully");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteManager(@RequestParam("id") long id) {
        managerServiceImpl.delete(id);
        return ResponseEntity.ok().body("Manager by " + id + " deleted successfully");
    }
}
