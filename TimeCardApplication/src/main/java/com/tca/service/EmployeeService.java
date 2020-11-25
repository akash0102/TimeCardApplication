package com.tca.service;

import java.util.List;

import com.tca.exception.ResourceNotFoundException;
import com.tca.entity.Employee;

public interface EmployeeService {
	Employee createEmployee(Employee employee) throws ResourceNotFoundException;	
	Employee updateEmployee(Integer employeeId, Employee employeeDetails) throws ResourceNotFoundException;
	boolean deleteEmployeeById(Integer employeeId) throws ResourceNotFoundException;
	List<Employee> getAllEmployee();
	Employee getEmpById(int empId) throws ResourceNotFoundException;

}
