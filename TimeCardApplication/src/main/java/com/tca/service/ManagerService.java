package com.tca.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.tca.exception.ResourceNotFoundException;
import com.tca.entity.Manager;



public interface ManagerService {
	Manager createManager(Manager manager);
	Manager updateManager(Integer managerId, Manager managerDetails) throws ResourceNotFoundException;
	boolean deleteManager(Integer managerId) throws ResourceNotFoundException;
	List<Manager> getAllManager();

	
//	Employee createEmployee(Employee employee);
//	ResponseEntity<Employee> updateEmployee(Integer employeeId, Employee employeeDetails) throws ResourceNotFoundException;
//	boolean deleteEmployeeById(Integer employeeId) throws ResourceNotFoundException;
//	List<Employee> getAllEmployee();
	//Employee findEmployeeById(Integer employee);
	
	
	 
}
