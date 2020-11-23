package com.tca.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AttendanceTest {
	
	Attendance att;
	@BeforeEach
	void setUp() throws Exception {
		att=new Attendance();
	}

	@AfterEach
	void tearDown() throws Exception {
		att=null;
	}
	
	@Test
	void test() {
		
		att.setAttendanceId(100);
		assertEquals(100,att.getAttendanceId());
	}
	
	@Test
	void testGetId() {
		att.setAttendanceId(150);
		assertNotEquals(100,att.getAttendanceId());
	}
	
	@Test
	void testGetEmployeeId() {
		Employee emp=new Employee();
		emp.setEmployeeId(100);
		att.setEmployee(emp);
		assertEquals(emp,att.getEmployee());
	}
	
	@Test 
	void testLocalInTime() {
		att.setInTime(LocalTime.now());
		assertEquals(LocalTime.now(),att.getInTime());
	}
	
	@Test 
	void testLocalInTimeFalse() {
		att.setInTime(LocalTime.now());
		assertNotEquals(LocalTime.NOON,att.getInTime());
	}
	
	@Test 
	void testOfftime() {
		att.setOffTime(LocalTime.now());
		assertEquals(LocalTime.now(),att.getOffTime());
	}
	
	@Test 
	void testOffTimeFalse() {
		att.setOffTime(LocalTime.now());
		assertNotEquals(LocalTime.NOON,att.getInTime());
	}
	
	@Test 
	void testDateId() {
		att.setFromDate(LocalDate.now());
		assertEquals(LocalDate.now(),att.getFromDate());
	}
	
	@Test 
	void testDateIdFalse() {
		att.setFromDate(LocalDate.now());
		att.setToDate(LocalDate.MIN);
		assertNotEquals(LocalDate.now(),att.getToDate());
	}
	
	
	@Test
	void testStatus() {
		att.setStatus("Pending");
		assertEquals("Pending",att.getStatus());
	}
	
	@Test
	void testFailStatus() {
		att.setStatus("Approved");
		assertNotEquals("Pending",att.getStatus());
	}

}
