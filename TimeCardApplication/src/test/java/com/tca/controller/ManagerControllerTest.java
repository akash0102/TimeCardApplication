package com.tca.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tca.entity.Employee;
import com.tca.entity.Manager;
import com.tca.service.ManagerService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ManagerController.class)
public class ManagerControllerTest {
	
	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
    private ManagerService managerService;
	
	 @Test
	   public void testNewManager() throws Exception{
		  String URI = "/api/v2/CreateManager";
		  Manager manager=new Manager();
		  Employee emp=new Employee();
		  emp.setEmployeeName("RAJU");
		  emp.setEmployeeEmail("chiku@gmail.com");
		  emp.setPhoneNumber("08512518301");
		  manager.setManagerId(2);
		  manager.setEmpl(emp);
		  String jsonInput = this.converttoJson(manager);

		  Mockito.when(managerService.createManager(manager)).thenReturn(manager);
		  MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders
				    		.post(URI).accept(MediaType.APPLICATION_JSON).content(jsonInput).
				    		contentType(MediaType.APPLICATION_JSON)).andReturn();
		  MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
	      String jsonOutput = mockHttpServletResponse.getContentAsString();
	      assertThat(jsonInput).isEqualTo(jsonOutput);
	      Assert.assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
	 }
	 
	 @Test
	 public void testDeleteManagerById() throws Exception{
		 String URI = "/api/v2/Manager/{id}";
		  Manager manager=new Manager();
	    	manager.setManagerId(1);
	    	Employee emp=new Employee();
			emp.setEmployeeName("MARINA");
			emp.setEmployeeEmail("s@gmail.com");
			emp.setPhoneNumber("08512");
			manager.setEmpl(emp);
			String jsonInput = this.converttoJson(manager);
	    	managerService.deleteManager(manager.getManagerId());
	    	Mockito.when(managerService.deleteManager(Mockito.any())).thenReturn(true);
			MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.put(URI, 3)
				 			.accept(MediaType.APPLICATION_JSON)
				 			.content(jsonInput).contentType(MediaType.APPLICATION_JSON))
				 			.andReturn();
			MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
			String jsonOutput = mockHttpServletResponse.getContentAsString();
		
		 	assertThat(jsonInput).isEqualTo(jsonOutput);  	 
	 }
	 @Test
	 public void testUpdateManagerById() throws Exception{
		 String URI= "/api/v2/Manager/{id}";
		 Manager manager=new Manager();
		 manager.setManagerId(1);
		 Employee emp=new Employee();
		 emp.setEmployeeName("MARINA");
		 emp.setEmployeeEmail("marinas@gmail.com");
		 emp.setPhoneNumber("08512");
		 manager.setEmpl(emp);
		 String jsonInput = this.converttoJson(manager);
		 Mockito.when(managerService.updateManager(Mockito.any(), Mockito.any())).thenReturn(manager);
		 MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.put(URI, 3)
				 			.accept(MediaType.APPLICATION_JSON)
				 			.content(jsonInput).contentType(MediaType.APPLICATION_JSON))
				 			.andReturn();
		 MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		 String jsonOutput = mockHttpServletResponse.getContentAsString();
	
	 	 assertThat(jsonInput).isEqualTo(jsonOutput);  	 
	 }
	    
	    @Test
	    public void testGetAllManagers() throws Exception{
	    	
	       String URI= "/api/v2/Manager";
	    	Manager manager1=new Manager();
	    	manager1.setManagerId(4);
	    	Employee emp=new Employee();
			emp.setEmployeeName("MARINA");
			emp.setEmployeeEmail("marinas@gmail.com");
			emp.setPhoneNumber("08512");
			manager1.setEmpl(emp);
	    	
	    	Manager manager2=new Manager();
	    	manager2.setManagerId(0);
	    	Employee emp2=new Employee();
			emp2.setEmployeeName("MARINA");
			emp2.setEmployeeEmail("marinas@gmail.com");
			emp2.setPhoneNumber("08512");
			manager2.setEmpl(emp2);
	    	
	    	List<Manager> managerlist=new ArrayList<>();
	    	managerlist.add(manager1);
	    	managerlist.add(manager2);
	    	
	    	String jsonInput = this.converttoJson(managerlist);
	    	Mockito.when(managerService.getAllManager()).thenReturn(managerlist);
	    	MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders
	    			.get(URI).accept(MediaType.APPLICATION_JSON)).andReturn();
	        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
	        String jsonOutput = mockHttpServletResponse.getContentAsString();

	        assertThat(jsonInput).isEqualTo(jsonOutput);
	  
	    }
	 

	  /**
     * Convert Object into Json String by using Jackson ObjectMapper
     * @param ticket
     * @return
     * @throws JsonProcessingException
     */
    private String converttoJson(Object manager) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(manager);
    }

}
