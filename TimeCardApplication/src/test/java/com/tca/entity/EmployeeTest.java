package com.tca.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EmployeeTest {
	
	Employee emp;
	
	@BeforeEach
	void setUp() throws Exception {
		emp=new Employee();
	}

	@AfterEach
	void tearDown() throws Exception {
		emp=null;
	}
	
	@Test
	void testEmpId() {
		emp.setEmployeeId(100);
		assertEquals(100,emp.getEmployeeId());
	}
	
	@Test
	void testEmpIdFalse() {
		emp.setEmployeeId(100);
		assertNotEquals(110,emp.getEmployeeId());
	}
	
	@Test
	void testEmpName() {
		emp.setEmployeeName("abcde");
		assertEquals("abcde",emp.getEmployeeName());
	}
	
	@Test
	void testEmpNameFalse() {
		emp.setEmployeeName("abcde");
		assertNotEquals("abe",emp.getEmployeeName());
	}
	
	@Test
	void testEmpRole() {
		emp.setEmployeeRole("developer");
		assertEquals("developer",emp.getEmployeeRole());
	}

	@Test
	void testEmpRoleFalse() {
		emp.setEmployeeRole("developer");
		assertNotEquals("Analyst",emp.getEmployeeRole());
	}
	
	@Test
	void testEmpPhNo() {
		emp.setPhoneNumber("1234567890");
		assertEquals("1234567890",emp.getPhoneNumber());
	}
	
	
	@Test
	void testEmpPhNoFalse() {
		emp.setPhoneNumber("1234567890");
		assertNotEquals("12347890",emp.getPhoneNumber());
	}
	
	@Test
	void testEmail() {
		emp.setEmployeeEmail("abcd@google.com");
		assertEquals("abcd@google.com",emp.getEmployeeEmail());
	}
	
	@Test
	void testEmailFalse() {
		emp.setPhoneNumber("abcd@google.com");
		assertNotEquals("1234567890",emp.getPhoneNumber());
	}
	
}
