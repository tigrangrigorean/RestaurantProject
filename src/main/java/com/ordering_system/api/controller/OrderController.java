package com.ordering_system.api.controller;

import com.ordering_system.model.dto.Address;
import com.ordering_system.model.dto.FoodDto;
import com.ordering_system.model.dto.Order;
import com.ordering_system.service.impl.OrderServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@SecurityRequirement(name = "Bearer Authentication")
public class OrderController {
    private final OrderServiceImpl orderServiceImpl;

    @Autowired
    public OrderController(OrderServiceImpl orderServiceImpl) {
        this.orderServiceImpl = orderServiceImpl;
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable("id") long id) {
        return ResponseEntity.ok().body(orderServiceImpl.getById(id));
    }

    @GetMapping("")
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok().body(orderServiceImpl.getAll());
    }


    @PostMapping("/save")
    public ResponseEntity<Order> saveOrder(@RequestBody List<FoodDto> foodDtoList, Address address) {
        return ResponseEntity.status(201).body(orderServiceImpl.save(foodDtoList,address));
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateOrderById(@RequestParam long id, @RequestBody Order order) {
        orderServiceImpl.update(id, order);
        return ResponseEntity.ok().body("Order by " + id + " updated successfully");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteOrder(@RequestParam("id") long id) {
        orderServiceImpl.delete(id);
        return ResponseEntity.ok().body("Order by " + id + " deleted successfully");
    }
}
