package com.tca.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.tca.exception.ResourceNotFoundException;
import com.tca.entity.Manager;
import com.tca.service.ManagerService;


@RestController
@RequestMapping("/api/v2")
public class ManagerController {
	
	@Autowired
	private ManagerService managerService;

	@PostMapping("/CreateManager")
	public Manager createCompanyManger( @RequestBody Manager manager) {
		return managerService.createManager(manager);
	} 
	
	@PutMapping("/Manager/{id}")
	public ResponseEntity<ResponseEntity<Manager>> updateManager(@PathVariable(value = "id") Integer managerId,
			 @RequestBody Manager managerDetails) throws ResourceNotFoundException {
		ResponseEntity<Manager> manager = managerService.updateManager(managerId, managerDetails);
		return  ResponseEntity.ok(manager);
	}


	@DeleteMapping("/Manager/{id}")	
	public ResponseEntity<Boolean> deleteManager(@PathVariable(value = "id") Integer managerId) throws ResourceNotFoundException	{
		
		boolean manager = managerService.deleteManager(managerId);
		return  ResponseEntity.ok(manager);
	}
	
	@GetMapping("/Manager")
	public List<Manager> getAllManager() {
		return managerService.getAllManager();
	} 
	
}
