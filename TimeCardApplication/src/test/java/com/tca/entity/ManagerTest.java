package com.tca.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ManagerTest {
	
	static Manager manager;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		manager=new Manager();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		manager=null;
	}

	@Test
	void testGetManagerId() {
		manager.setManagerId(25);
		assertEquals(25,manager.getManagerId());
	}

	@Test
	void testFailGetManagerId() {
		manager.setManagerId(25);
		assertNotEquals(0,manager.getManagerId());
	}

	@Test
	void testGetEmpl() {
		Employee emp=new Employee();
		emp.setEmployeeId(20);
		HashSet<Employee> empl=new HashSet<>();
		empl.add(new Employee());
		empl.add(emp);
		manager.setEmpl(empl);
		
		assertEquals(2,manager.getEmpl().size());
	}

	@Test
	void testFailGetEmpl() {
		Employee emp=new Employee();
		emp.setEmployeeId(20);
		HashSet<Employee> empl=new HashSet<>();
		empl.add(new Employee());
		empl.add(emp);
		manager.setEmpl(empl);
		
		assertNotEquals(0,manager.getEmpl().size());
	}

	@Test
	void testEqualsObject() {
		Manager man=new Manager();
		man.setManagerId(100);
		man.setManagerName("abcd");
		man.setManagerNumber("0987654321");
		man.setManagerEmail("manager@mail.com");
		Employee emp=new Employee();
		emp.setEmployeeId(20);
		HashSet<Employee> empl=new HashSet<>();
		empl.add(new Employee());
		empl.add(emp);
		man.setEmpl(empl);
		man.setPass("abcede");
		man.setUserId("user12");
		manager.setManagerId(man.getManagerId());
		manager.setEmpl(man.getEmpl());
		manager.setManagerName(man.getManagerName());
		manager.setManagerNumber(man.getManagerNumber());
		manager.setManagerId(man.getManagerId());
		manager.setPass(man.getPass());
		manager.setUserId(man.getUserId());
		assertTrue(manager.equals(man));
	}
	
	@Test
	void testFailEqualsObject() {
		Manager man=new Manager();
		man.setManagerId(100);
		man.setManagerName("abcd");
		man.setManagerNumber("0987654321");
		man.setManagerEmail("manager@mail.com");
		Employee emp=new Employee();
		emp.setEmployeeId(20);
		HashSet<Employee> empl=new HashSet<>();
		empl.add(new Employee());
		empl.add(emp);
		man.setEmpl(empl);
		man.setPass("abcede");
		man.setUserId("user12");
		
		assertFalse(man.equals(manager));
	}
	
	


}
