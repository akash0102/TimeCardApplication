package com.tca.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.tca.exception.ResourceNotFoundException;
import com.tca.entity.Employee;
import com.tca.repository.EmployeeRepository;
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService{
	@Autowired
	private EmployeeRepository employeeRepository;
	public Employee createEmployee( @RequestBody Employee employee) {
		return  employeeRepository.save(employee);
	}	

 public Employee updateEmployee( Integer employeeId, Employee employeeDetails) throws ResourceNotFoundException {
	Employee employee = employeeRepository.findById(employeeId)
			.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
	employee.setEmployeeId(employeeDetails.getEmployeeId()); 
	employee.setEmployeeName(employeeDetails.getEmployeeName());
	employee.setEmployeeEmail(employeeDetails.getEmployeeEmail());
	employee.setPhoneNumber(employeeDetails.getPhoneNumber());
	final Employee updatedEmployee = employeeRepository.save(employee);
	return updatedEmployee; 
} 
 
 public boolean deleteEmployeeById(@PathVariable(value = "id") Integer employeeId)
			throws ResourceNotFoundException {
	 Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

		employeeRepository.delete(employee);
		return true;
}

 public List<Employee> getAllEmployee() {
		 return employeeRepository.findAll();
		}

	
}
