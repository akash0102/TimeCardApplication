package com.tca.controller;

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

import com.tca.entity.Leave;
import com.tca.service.LeaveService;




@RunWith(SpringRunner.class)
@WebMvcTest(value = LeaveController.class)
class LeaveControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LeaveService leaveservice;


   /* @Test
    public void testaddLeave(DateId date) throws Exception{
        String URI = "/leave/apply";
       Leave lea=new Leave();
		lea.setDateId(date);
		lea.setEmployee(date.getemployeeId());
		lea.setStatus("Pending");
		
        String jsonInput = this.converttoJson(lea);
        Mockito.when(leaveservice.addLeave(Mockito.any(Leave.class))).thenReturn(lea);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        String jsonOutput = mockHttpServletResponse.getContentAsString();
        assertThat(jsonInput).isEqualTo(jsonOutput);
        Assert.assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
    }*/
    @Test
    void testRemoveLeave() throws Exception{
        String URI = "/leave/deleteLeaveById/leaveId/{leaveId}";
        Leave lea=new Leave();
        lea.setLeaveId(2);

        Mockito.when(leaveservice.findLeave(Mockito.any())).thenReturn(null);
        Mockito.when(leaveservice.removeLeave(Mockito.any())).thenReturn(true);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.delete(URI, 105).accept(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();

        Assert.assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());

       
    }
}

