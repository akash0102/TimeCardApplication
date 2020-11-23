package com.tca.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
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
import com.tca.entity.Leave;
import com.tca.service.LeaveService;




@RunWith(SpringRunner.class)
@WebMvcTest(value = LeaveController.class)
class LeaveControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LeaveService leaveservice;

	  @Test
	  public void testaddLeave() throws Exception{
	  String URI = "/leave/apply";
	  Leave lea=new Leave(); 
	  lea.setFromDate(LocalDate.MIN);
	  lea.setToDate(LocalDate.MAX);
	  lea.setEmployee(new Employee());
	  lea.setStatus("Pending");
	  
		
		  String jsonInput = this.convertToJSON(lea);
		  Mockito.when(leaveservice.addLeave(lea)).thenReturn(
		  lea);
		  MvcResult mvcResult =
		  this.mockMvc.perform(MockMvcRequestBuilders.post(URI).accept(MediaType.
		  APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
		  .andReturn();
		  MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		  String jsonOutput = mockHttpServletResponse.getContentAsString();
		  assertThat(jsonOutput).isEqualTo(jsonInput);
		  Assert.assertEquals(HttpStatus.OK.value(),
		  mockHttpServletResponse.getStatus());
		 
	  }
	 
    @Test
    void testRemoveLeave() throws Exception{
        String URI = "/leave/deleteLeaveById/leaveId/{leaveId}";
        Leave lea=new Leave();
        lea.setLeaveId(2);

        Mockito.when(leaveservice.findLeave(2)).thenReturn(lea);
        Mockito.when(leaveservice.removeLeave(2)).thenReturn(-1);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.delete(URI, 2)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();

        Assert.assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());

       
    }
    
    
	private String convertToJSON(Object att) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(att);
	}
}

