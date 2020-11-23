package com.tca.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LeaveTest {
	Leave leaveObj;
	@BeforeEach
	void setUp() throws Exception {
		leaveObj=new Leave();
	}

	@AfterEach
	void tearDown() throws Exception {
		leaveObj=null;
	}

	@Test
	void testFromDate() {
		leaveObj.setFromDate(LocalDate.MIN);
		assertEquals(LocalDate.MIN,leaveObj.getFromDate());
	}
	
	@Test
	void testToDateFails() {
		leaveObj.setFromDate(LocalDate.MIN);
		assertNotEquals(null,leaveObj.getFromDate());
	}
	
	
	@Test
	void testGetLeaveId() {
		leaveObj.setLeaveId(100);
		assertEquals(100,leaveObj.getLeaveId());
	}
	
	@Test
	void testGetLeaveIdFalse() {
		leaveObj.setLeaveId(100); 
		assertNotEquals(400,leaveObj.getLeaveId());
	}
	
	@Test
	void testGetEmployee() {
		Employee emp=new Employee();
		emp.setEmployeeId(12533);
		leaveObj.setEmployee(emp);;
		assertEquals(emp,leaveObj.getEmployee());
	}
	
	@Test
	void testGetEmployeeFalse() {
		Employee emp=new Employee();
		emp.setEmployeeId(12533);
		leaveObj.setEmployee(emp);;
		assertNotEquals(null,leaveObj.getEmployee());
	}

}
