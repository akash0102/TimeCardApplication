package com.tca.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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

import com.tca.entity.DateId;
import com.tca.entity.Employee;
import com.tca.entity.Leave;
import com.tca.service.LeaveService;





@RunWith(SpringRunner.class)
@WebMvcTest(value = LeaveController.class)
public class LeaveControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LeaveService leaveService;


   @Test
    public void testaddLeave() throws Exception{
        String URI = "/leave/apply";
      Employee emp=new Employee();
        Leave leave=new Leave();
        leave.setLeaveId(2);
        leave.setStatus("Pending");
        leave.setEmployee(emp);
       
        DateId date=new DateId();
        date.setDateNum(1);
        date.setfromDate(null);
        date.settoDate(null);
      leave.setDateId(date);
      date.setemployeeId(emp);
       
		//lea.setDateId(date);
		//lea.setEmployee(date.getemployeeId());
		
		
      String jsonInput = this.converttoJson(leave);

      Mockito.when(leaveService.addLeave(Mockito.any(Leave.class))).thenReturn(leave);
      MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
              .andReturn();
      MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
      String jsonOutput = mockHttpServletResponse.getContentAsString();
      assertThat(jsonInput).isEqualTo(jsonOutput);
      Assert.assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
   
    
}
   @Test
   public void testFindByEmpId() throws Exception{
	   String URI = "/leave/viewAllLeaves/{empId}";
	   Employee emp=new Employee();
       Leave leave=new Leave();
       leave.setLeaveId(2);
       leave.setStatus("Pending");
       leave.setEmployee(emp);
      
       DateId date=new DateId();
       date.setDateNum(1);
       date.setfromDate(null);
       date.settoDate(null);
     leave.setDateId(date);
     //date.setemployeeId(emp);
     ArrayList<Leave> checkList=new ArrayList<>();
		checkList.add(leave);
     String jsonInput = this.converttoJson(leave);

     Mockito.when(leaveService.findByEmpId(5).thenReturn(checkList);
     MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI, 5).accept(MediaType.APPLICATION_JSON)).andReturn();
     MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
     String jsonOutput = mockHttpServletResponse.getContentAsString();

     assertThat(jsonInput).isEqualTo(jsonOutput);
 }
      
   

   private String converttoJson(Object leave) throws JsonProcessingException {
       ObjectMapper objectMapper = new ObjectMapper();
       return objectMapper.writeValueAsString(leave);
}
}

