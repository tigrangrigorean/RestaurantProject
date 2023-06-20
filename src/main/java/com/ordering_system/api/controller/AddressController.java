package com.ordering_system.api.controller;

import com.ordering_system.model.dto.Address;
import com.ordering_system.service.impl.AddressServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
@SecurityRequirement(name = "Bearer Authentication")
public class AddressController {

    private final AddressServiceImpl addressServiceImpl;

    @Autowired
    public AddressController(AddressServiceImpl addressServiceImpl) {
        this.addressServiceImpl = addressServiceImpl;
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable("id") long id) {
        return ResponseEntity.ok().body(addressServiceImpl.getById(id));
    }

    @GetMapping("")
    public ResponseEntity<List<Address>> getAllAddresses() {
        return ResponseEntity.ok().body(addressServiceImpl.getAll());
    }

    @PostMapping("/save")
    public ResponseEntity<Address> saveAddress(@RequestBody Address address) {
        return ResponseEntity.status(201).body(addressServiceImpl.save(address));
    }
    @PutMapping("/update")
    public ResponseEntity<String> updateAddressById(@RequestParam long id, @RequestBody Address address) {
        addressServiceImpl.update(id, address);
        return ResponseEntity.ok().body("Address by " + id + " updated successfully");
    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteAddress(@RequestParam("id") long id) {
        addressServiceImpl.delete(id);
        return ResponseEntity.ok().body("Address by " + id + " deleted successfully");
    }

}
