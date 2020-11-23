package com.tca.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalTime;
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
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.tca.entity.Attendance;
import com.tca.entity.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tca.service.AttendanceServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(value = AttendanceController.class)
public class AttendanceControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private AttendanceServiceImpl attService;

	@Test
	public void testsaveAttendanceDetails() throws Exception {
		String URI = "/api/v1/saveAttendance";
		Employee emp = new Employee();
		emp.setEmployeeId(1);
		Attendance att = new Attendance();
		att.setAttendanceId(3); 
		att.setEmployee(att.getEmployee());
		att.setInTime(LocalTime.of(8, 30));
		att.setOffTime(LocalTime.of(18, 00));
		
		att.setStatus("Pending");

		String jsonInput = this.converttoJson(att);

		Mockito.when(attService.saveAttendanceDetails(Mockito.any(Attendance.class))).thenReturn(att);
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON)
				.content(jsonInput).contentType(MediaType.APPLICATION_JSON)).andReturn();
		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		String jsonOutput = mockHttpServletResponse.getContentAsString();
		assertThat(jsonInput).isEqualTo(jsonOutput);
		Assert.assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
	}

	@Test
	public void testgetAttendanceDetailsById() throws Exception {
		String URI = "/api/v1/getAttendance/{id}";
		Attendance att = new Attendance();
		att.setAttendanceId(3);
		att.setInTime(LocalTime.of(8, 30));
		att.setOffTime(LocalTime.of(18, 00));
		att.setStatus("Pending");

		String jsonInput = this.converttoJson(att);

		//Mockito.when(attService.getAttendanceDetailsById(Mockito.any()).thenReturn(att));
		//Assert.assertTrue(attService.getAttendanceDetailsById(employeeId));
		MvcResult mvcResult = this.mockMvc
				.perform(MockMvcRequestBuilders.get(URI, 102).accept(MediaType.APPLICATION_JSON)).andReturn();
		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		String jsonOutput = mockHttpServletResponse.getContentAsString();

		assertThat(jsonInput).isEqualTo(jsonOutput);
	}

	@Test
	public void testupdateAttendance() throws Exception {
		String URI = "/api/v1/updateAttendance/{id}";
		Attendance att = new Attendance();
		att.setAttendanceId(3);
		att.setInTime(LocalTime.of(8, 30));
		att.setOffTime(LocalTime.of(18, 00));
		att.setStatus("Pending");

		String jsonInput = this.converttoJson(att);
		Mockito.when(attService.updateAttendanceById(Mockito.any(), Mockito.any())).thenReturn(att);
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.put(URI, 105)
				.accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
				.andReturn();
		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		String jsonOutput = mockHttpServletResponse.getContentAsString();

		assertThat(jsonInput).isEqualTo(jsonOutput);
	}

	@Test
	public void testdeleteAttendanceDetailsById() throws Exception {
		String URI = "/api/v1/deleteAttendance/{id}";
		Attendance att = new Attendance();
		att.setAttendanceId(3);
		att.setInTime(LocalTime.of(8, 30));
		att.setOffTime(LocalTime.of(18, 00));
		att.setStatus("Pending");

		//Mockito.when(attService.getAttendanceDetailsById(Mockito.any())).thenReturn(att);
		Mockito.when(attService.deleteAttendanceDetailsById(Mockito.any())).thenReturn(true);
		MvcResult mvcResult = this.mockMvc
				.perform(MockMvcRequestBuilders.delete(URI, 105).accept(MediaType.APPLICATION_JSON)).andReturn();
		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();

		Assert.assertNotEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
	}

	@Test
	public void testgetAllAttendance() throws Exception {
		String URI = "/api/v1/getAllAttendance";
		Attendance att = new Attendance();
		att.setAttendanceId(3);
		att.setInTime(LocalTime.of(8, 30));
		att.setOffTime(LocalTime.of(18, 00));
		att.setStatus("Pending");
		Attendance att1 = new Attendance();
		att1.setAttendanceId(3);
		att1.setInTime(LocalTime.of(8, 30));
		att1.setOffTime(LocalTime.of(18, 00));
		att1.setStatus("Pending");
		List<Attendance> attList = new ArrayList<>();
		attList.add(att);
		attList.add(att1);

		String jsonInput = this.converttoJson(attList);

		Mockito.when(attService.getAllAttendance()).thenReturn(attList);
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON))
				.andReturn();
		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		String jsonOutput = mockHttpServletResponse.getContentAsString();

		assertThat(jsonInput).isEqualTo(jsonOutput);
	}

	private String converttoJson(Object att) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(att);
	}

}

