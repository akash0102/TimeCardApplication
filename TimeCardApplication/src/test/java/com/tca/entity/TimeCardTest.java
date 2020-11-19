package com.tca.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TimeCardTest {

	TimeCard tca;
	
	@BeforeEach
	void setUp() throws Exception {
		tca=new TimeCard();
	}

	@AfterEach
	void tearDown() throws Exception {
		tca=null;
	}
	
	@Test
	void testGetId() {
		tca.setTimeCardId(100);
		assertEquals(100,tca.getTimeCardId());
	}
	
	@Test
	void testGetIdFalse() {
		tca.setTimeCardId(100);
		assertNotEquals(120,tca.getTimeCardId());
	}
	
	@Test
	void testGetEmployee() {
		Employee emp=new Employee();
		emp.setEmployeeName("abcd");
		emp.setEmployeeId(100);
		emp.setEmployeeEmail("test@sk.com");
		emp.setEmployeeRole("developer");
		emp.setPhoneNumber("3263749232");
		
		tca.setEmployee(emp);
		assertEquals(emp,tca.getEmployee());
	}
	
	@Test
	void testGetEmployeeFalse() {
		Employee emp=new Employee();
		emp.setEmployeeName("abcd");
		emp.setEmployeeId(100);
		emp.setEmployeeEmail("test@sk.com");
		emp.setEmployeeRole("developer");
		emp.setPhoneNumber("3263749232");
		
		tca.setEmployee(null);
		assertNotEquals(emp,tca.getEmployee());
	}
	
	@Test
	void testGetLocalDate() {
		
		tca.setDate(LocalDate.now());
		assertEquals(LocalDate.now(),tca.getDate());
	}

	
	@Test
	void testGetLocalDateFalse() {
		
		tca.setDate(LocalDate.now());
		assertNotEquals(LocalDate.MIN,tca.getDate());
	}
	
	@Test
	void testGetTimeEntry() {
		tca.setTimeEntry(LocalTime.parse("07:30"));
		assertEquals(LocalTime.parse("07:30"),tca.getTimeEntry());
	}
	
	@Test
	void testGetTimeEntryFail() {
		tca.setTimeEntry(LocalTime.parse("07:30"));
		assertNotEquals(LocalTime.parse("14:30"),tca.getTimeEntry());
	}
	
	@Test
	void testGetTimeExit() {
		tca.setTimeExit(LocalTime.parse("19:30"));
		assertEquals(LocalTime.parse("19:30"),tca.getTimeExit());
	}
	
	@Test
	void testGetTimeExitFalse() {
		tca.setTimeExit(LocalTime.MAX);
		assertNotEquals(LocalTime.parse("19:30"),tca.getTimeExit());
	}
	
}
