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
import com.tca.entity.Leave;
import com.tca.entity.Manager;
import com.tca.service.ManagerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;




/**
 * @author usha
 * manager service controller
 */
@Api(value = "ManagerControllerClass",description = "This is class for employee controller")
@RestController
@RequestMapping("/api/v2/Manager")
public class ManagerController {
	
	@Autowired
	private ManagerService managerService;

	@ApiOperation(value = "create manager", response = Manager.class, tags = "ManagerControllerClass")
	@PostMapping("/CreateManager")
	public Manager createCompanyManger( @RequestBody Manager manager) {
		return managerService.createManager(manager);  
	}
	
	
	@ApiOperation(value = "edit manager", response = Manager.class, tags = "ManagerControllerClass")
	@PutMapping("/editManager/{id}")
	public ResponseEntity<Manager> updateManager(@PathVariable(value = "id") Integer managerId,
			 @RequestBody Manager managerDetails) throws ResourceNotFoundException {
		Manager manager = managerService.updateManager(managerId, managerDetails);
		return  ResponseEntity.ok(manager); 
	}

	
	@ApiOperation(value = "delete manager", response = Boolean.class, tags = "ManagerControllerClass")
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteManager(@PathVariable(value = "id") Integer managerId) throws ResourceNotFoundException	{
		
		boolean manager = managerService.deleteManager(managerId);
		return  ResponseEntity.ok(manager);
	}
	
	
	@ApiOperation(value = "get all manager", response = Iterable.class, tags = "ManagerControllerClass")
	@GetMapping("/all")
	public List<Manager> getAllManager() {
		return managerService.getAllManager();
	} 
	
}
