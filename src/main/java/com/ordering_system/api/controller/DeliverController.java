package com.ordering_system.api.controller;

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

import com.ordering_system.model.dto.Deliver;
import com.ordering_system.service.impl.DeliverServiceImpl;
import com.ordering_system.service.impl.OrderServiceImpl;

@RestController
@RequestMapping("/deliver")
public class DeliverController {
	
	private final DeliverServiceImpl deliverService;
	private final OrderServiceImpl orderService;
	
	public DeliverController(DeliverServiceImpl deliverService,OrderServiceImpl orderService) {
		this.deliverService = deliverService;
		this.orderService = orderService;
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Deliver> getById(@PathVariable long id) {
		return ResponseEntity.ok().body(deliverService.getById(id));
	}
	
	@PostMapping("/save")
	public ResponseEntity<Deliver> save(@RequestBody Deliver deliver) {
		return ResponseEntity.ok().body(deliverService.save(deliver));
	}
	
	@PutMapping("/update")
	public ResponseEntity<String> update(@RequestParam long id, @RequestBody Deliver deliver) {
		deliverService.update(id, deliver);
        return ResponseEntity.ok().body("Deliver by " + id + " updated successfully");
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<String> delete(@RequestParam long id) {
		deliverService.delete(id);
		return ResponseEntity.ok().body("Deliver by " + id + " deleted successfully");
	}
	
}
