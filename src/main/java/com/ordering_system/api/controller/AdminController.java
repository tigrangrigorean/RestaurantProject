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

import com.ordering_system.model.dto.Admin;
import com.ordering_system.service.impl.AdminServiceImpl;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	private final AdminServiceImpl adminServiceImpl;
	
	@Autowired
	public AdminController(AdminServiceImpl adminServiceImpl) {
		this.adminServiceImpl = adminServiceImpl;
	}
	
	 @GetMapping("/get/{id}")
	    public ResponseEntity<Admin> getAdminById(@PathVariable("id") long id) {
	        return ResponseEntity.ok().body(adminServiceImpl.getById(id));
	    }

	    @GetMapping("/getAll")
	    public ResponseEntity<List<Admin>> getAllAdmin() {
	        return ResponseEntity.ok().body(adminServiceImpl.getAll());
	    }

	    @PostMapping("/save")
	    public ResponseEntity<Admin> saveAdmin(@RequestBody Admin admin) {
	        return ResponseEntity.ok().body(adminServiceImpl.save(admin));
	    }

	    @PutMapping("/update")
	    public ResponseEntity<String> updateAdminById(@RequestParam long id,
	                                                 @RequestBody Admin admin) {
	        adminServiceImpl.update(id, admin);
	        return ResponseEntity.ok().body("Food by " + id + " updated successfully");
	    }

	    @DeleteMapping("/delete")
	    public ResponseEntity<String> deleteAdmin(@RequestParam("id") long id) {
	        adminServiceImpl.delete(id);
	        return ResponseEntity.ok().body("Food by " + id + " deleted successfully");
	    }

}
