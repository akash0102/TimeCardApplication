package com.tca.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tca.exception.ResourceNotFoundException;
import com.tca.entity.Employee;
import com.tca.entity.Manager;
import com.tca.repository.EmployeeRepository; 


@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService{
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private ManagerService manService;
	
	public Employee createEmployee(Employee employee) throws ResourceNotFoundException {
		Manager manager=manService.getManagerById(employee.getManager().getManagerId());
		manager.getEmpl().add(employee); 
		manService.updateManager(manager.getManagerId(),manager);
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
 
 	public boolean deleteEmployeeById( Integer employeeId)
			throws ResourceNotFoundException {
 		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

		employeeRepository.delete(employee);
		return true;
 	}

 	public List<Employee> getAllEmployee() {
		 return employeeRepository.findAll();
	} 

	@Override
	public Employee getEmpById(int empId) {
		return employeeRepository.findById(empId).get();
	}

	
}
