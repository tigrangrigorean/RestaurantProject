package com.ordering_system.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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

import com.ordering_system.model.dto.Delivery;
import com.ordering_system.model.dto.User;
import com.ordering_system.service.impl.DeliveryServiceImpl;
import com.ordering_system.service.impl.OrderServiceImpl;

@RestController
@RequestMapping("/delivery")
@SecurityRequirement(name = "Bearer Authentication")
public class DeliveryController {
	
	private final DeliveryServiceImpl deliverService;
	private final OrderServiceImpl orderService;
	
	public DeliveryController(DeliveryServiceImpl deliverService, OrderServiceImpl orderService) {
		this.deliverService = deliverService;
		this.orderService = orderService;
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Delivery> getById(@PathVariable long id) {
		return ResponseEntity.ok().body(deliverService.getById(id));
	}
	
	@PostMapping("/signup")
	public ResponseEntity<String> singUpDelivery(@RequestBody User user) {
		deliverService.singUpDelivery(user);
		return ResponseEntity.status(201).body("Added deliverer with email " + user.getEmail());
	}
	
	@PostMapping("/save")
	public ResponseEntity<Delivery> save(@RequestBody Delivery delivery) {
		return ResponseEntity.status(201).body(deliverService.save(delivery));
	}
	
	@PutMapping("/update")
	public ResponseEntity<String> update(@RequestParam long id, @RequestBody Delivery delivery) {
		deliverService.update(id, delivery);
        return ResponseEntity.ok().body("Deliver by " + id + " updated successfully");
	}

	@PutMapping("/updateToDelivered")
	public ResponseEntity<String> updateStatusToDelivered(@RequestBody Delivery delivery){
		deliverService.updateStatusToDelivered(delivery);
		return ResponseEntity.ok().body("Delivery status updated successfully");
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<String> delete(@RequestParam long id) {
		deliverService.delete(id);
		return ResponseEntity.ok().body("Deliver by " + id + " deleted successfully");
	}
	
}
