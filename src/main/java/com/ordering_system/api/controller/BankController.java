package com.ordering_system.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ordering_system.model.dto.Order;
import com.ordering_system.service.impl.OrderServiceImpl;

@RestController
@RequestMapping("/bank")
public class BankController {
	
	  private final OrderServiceImpl orderService;

	  
	  @Autowired
	   public BankController(OrderServiceImpl orderService) {
	        this.orderService = orderService;
	    }

	
	  @PostMapping("pay/order")
	public ResponseEntity<Boolean> doPayment(@RequestBody Order order) {
		return ResponseEntity.ok().body(orderService.doPayment(order));
	}

}
