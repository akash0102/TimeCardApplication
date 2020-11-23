package com.tca.controller;

import java.util.List;

import org.apache.log4j.Logger;
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
import com.tca.entity.Employee;
import com.tca.service.EmployeeService;

@RestController
@RequestMapping("/api/v2/Employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/all")
	public ResponseEntity<List<Employee>> getAllEmployee() {
		return ResponseEntity.ok(employeeService.getAllEmployee());
	} 
	
	@PostMapping("/CreateEmployee")
	public ResponseEntity<Employee> createEmployee( @RequestBody Employee employee) {
		return ResponseEntity.ok(employeeService.createEmployee(employee));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Integer employeeId,
			 @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
		Employee  employee = employeeService.updateEmployee(employeeId, employeeDetails);
		return  ResponseEntity.ok(employee);
	}

	@DeleteMapping("/deleteEmployee/{id}")	
	public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable(value = "id") Integer employeeId,
			 @RequestBody Employee employeeDetails) throws ResourceNotFoundException	{
		return ResponseEntity.ok(employeeService.deleteEmployeeById(employeeId));
	}  
}
