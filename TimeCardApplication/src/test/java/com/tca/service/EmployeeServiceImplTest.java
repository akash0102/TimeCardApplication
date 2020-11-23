package com.tca.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.tca.entity.Employee;
import com.tca.repository.EmployeeRepository;
@RunWith(SpringRunner.class)
@SpringBootTest
class EmployeeServiceImplTest {  
	
	
	@MockBean
    private EmployeeRepository employeeRepository;
	
	@Autowired
	private EmployeeService employeeService;
	  
	@Test
	void testCreateEmployee() throws Exception{
		Employee employee=new Employee();
	   	employee.setEmployeeId(1);
	   	employee.setEmployeeName("MARINA");
	   	employee.setEmployeeEmail("mainas@gmail.com");
	   	employee.setPhoneNumber("085126767");
	   	 Mockito.when(employeeRepository.save(employee)).thenReturn(employee);
	   	 assertThat(employeeService.createEmployee(employee)).isEqualTo(employee);               
	}
	
	
	@Test
	void testGetAllEmployee() throws Exception{
		Employee employee1=new Employee();
	   	employee1.setEmployeeId(1);
	   	employee1.setEmployeeName("MARINA");
	   	employee1.setEmployeeEmail("marinas@gmail.com");
	   	employee1.setPhoneNumber("0851235353");
	   	
	   	Employee employee2=new Employee();
	   	employee2.setEmployeeId(0);
	   	employee2.setEmployeeName("MARINA");
	   	employee2.setEmployeeEmail("marinas@gmail.com");
	   	employee2.setPhoneNumber("0851235353");
	    List<Employee> employeelist = new ArrayList<>();
	    employeelist.add(employee1);
	    employeelist.add(employee2);
	        
	    Mockito.when(employeeRepository.findAll()).thenReturn(employeelist);  
	    assertThat(employeeService.getAllEmployee()).isEqualTo(employeelist);
	    	
	}  
	@Test
	void testDeleteEmployee() throws Exception{
		Employee employee=new Employee();
	    employee.setEmployeeId(1);
	    employee.setEmployeeName("MARINA");
	    employee.setEmployeeEmail("marinas@gmail.com");
	    employee.setPhoneNumber("085123433");
	    employeeRepository.deleteById(employee.getEmployeeId());
	    System.out.println(employeeRepository.findById(1));
	    Assert.assertTrue(employeeRepository.findById(1).isEmpty());    
	} 
	@Test
	void testUpdateEmployee() throws Exception{
		Employee employee2=new Employee();
	    employee2.setEmployeeId(6);
	    employee2.setEmployeeName("MARINA");
	    employee2.setEmployeeEmail("marinas@gmail.com");
	    employee2.setPhoneNumber("0851123433");
	    
		System.out.println(employee2.getEmployeeEmail());
		employeeRepository.findById(employee2.getEmployeeId());
		Assert.assertTrue(employeeRepository.findById(1).isEmpty()); 
	}
  
}
