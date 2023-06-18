package com.ordering_system.api.controller;

import java.util.List;

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
public class AdminController {

	private final UserServiceImpl adminServiceImpl;

	@Autowired
	public AdminController(UserServiceImpl adminServiceImpl) {
		this.adminServiceImpl = adminServiceImpl;
	}

	 @GetMapping("/get/{id}")
	    public ResponseEntity<User> getAdminById(@PathVariable("id") long id) {
	        return ResponseEntity.ok().body(adminServiceImpl.getById(id));
	    }

	    @GetMapping("/getAll")
	    public ResponseEntity<List<User>> getAllAdmin() {
	        return ResponseEntity.ok().body(adminServiceImpl.getAll());
	    }

	    @PostMapping("/save")
	    public ResponseEntity<User> saveAdmin(@RequestBody User admin) {
	        return ResponseEntity.status(201).body(adminServiceImpl.save(admin));
	    }

	    @PutMapping("/update")
	    public ResponseEntity<String> updateAdminById(@RequestParam String email,
	                                                 @RequestBody User admin) {
	        adminServiceImpl.update(email, admin);
	        return ResponseEntity.ok().body("Admin by " + email + " updated successfully");
	    }

	    @DeleteMapping("/delete")
	    public ResponseEntity<String> deleteAdmin(@RequestParam("id") long id) {
	        adminServiceImpl.delete(id);
	        return ResponseEntity.ok().body("Admin by " + id + " deleted successfully");
	    }

}
