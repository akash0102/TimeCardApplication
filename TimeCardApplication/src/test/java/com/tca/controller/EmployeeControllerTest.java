package com.tca.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tca.entity.Employee;
import com.tca.service.EmployeeService;
@RunWith(SpringRunner.class)
@WebMvcTest(value = EmployeeController.class)
class EmployeeControllerTest {
	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
    private EmployeeService employeeService;

	 @Test
	    void testNewEmployee() throws Exception{
		  String URI = "/api/v2/CreateEmployee";
		  Employee employee=new Employee();
		  employee.setEmployeeId(1);
		  employee.setEmployeeName("amrutha");
		  employee.setPhoneNumber("9701531212");
		  employee.setEmployeeEmail("ammu@gmail.com");
		  String jsonInput = this.converttoJson(employee);

		  Mockito.when(employeeService.createEmployee(Mockito.any(Employee.class))).thenReturn(employee);
		  MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
	                .andReturn();
		  MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
	        String jsonOutput = mockHttpServletResponse.getContentAsString();
	        assertThat(jsonInput).isEqualTo(jsonOutput);
	        Assert.assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
	 
	 }
	 private String converttoJson(Object manager) throws JsonProcessingException {
	        ObjectMapper objectMapper = new ObjectMapper();
	        return objectMapper.writeValueAsString(manager);
	    }
	 
	 @Test
	  void testDeleteEmployeeById() throws Exception{
		 String URI = "/api/v2/Employee/{id}";
		 Employee employee=new Employee();
	    	employee.setEmployeeId(3);
	    	employee.setEmployeeName("amrutha");
	    	employee.setEmployeeEmail("ammu@gmail.com");
	    	employee.setPhoneNumber("9550355319");
	    	employeeService.deleteEmployeeById(employee.getEmployeeId());
	    	 String jsonInput = this.converttoJson(employee);
	    	  Mockito.when(employeeService.deleteEmployeeById(3)).thenReturn(true);
		       MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI,3)
		    		   					.accept(MediaType.APPLICATION_JSON)).andReturn();
		       MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		       String jsonOutput = mockHttpServletResponse.getContentAsString();

		       assertThat(jsonInput).isEqualTo(jsonOutput);
	  }

	    @Test
	     void testGetAllEmployees() throws Exception{
	    	
	    	String URI= "/api/v2/Employee";
	    	Employee employee1=new Employee();
	    	employee1.setEmployeeId(3);
	    	employee1.setEmployeeName("amrutha");
	    	employee1.setEmployeeEmail("ammu@gmail.com");
	    	employee1.setPhoneNumber("9550355319");

	    	Employee employee2=new Employee();
	    	employee2.setEmployeeId(1);
	    	employee2.setEmployeeName("amrutha");
	    	employee2.setEmployeeEmail("ammu@gmail.com");
	    	employee2.setPhoneNumber("9550355319");

	       List<Employee> employeelist=new ArrayList<>();
	       employeelist.add(employee1);
	       employeelist.add(employee2);
	       String jsonInput = this.converttoJson(employeelist);
	       
	       Mockito.when(employeeService.getAllEmployee()).thenReturn(employeelist);
	       MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON)).andReturn();
	       MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
	       String jsonOutput = mockHttpServletResponse.getContentAsString();

	       assertThat(jsonInput).isEqualTo(jsonOutput);
	  
	    }
	    @Test
	     void testUpdateEmployee() throws Exception{
	    	
	     String URI= "/api/v2/Employee/{id}";
	     Employee employee=new Employee();
	    	employee.setEmployeeId(3);
	    	employee.setEmployeeName("amrutha");
	    	employee.setEmployeeEmail("ammu@gmail.com");
	    	employee.setPhoneNumber("9550355319");
	    	 String jsonInput = this.converttoJson(employee);
	    	 
	    	Mockito.when(employeeService.updateEmployee(Mockito.any(), Mockito.any())).thenReturn(ResponseEntity.ok(employee));
	    	 MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.put(URI, 3).accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
	                 .andReturn();
	         MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
	         String jsonOutput = mockHttpServletResponse.getContentAsString();

	         assertThat(jsonInput).isEqualTo(jsonOutput);

	    }
}
