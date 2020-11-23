package com.tca.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.tca.entity.Attendance;
import com.tca.entity.Employee;


@RunWith(SpringRunner.class)
@DataJpaTest
class AttendanceRepositoryTest {
	@Autowired
	private AttendanceRepository attr;
	
	@Autowired
	private TestEntityManager testenti;
	
	@Test
	void GetAllAttendances() { 
		List<Attendance> att=new ArrayList<>();
		Attendance att1 = new Attendance();
		att1.setEmployee(null);
		att1.setInTime(LocalTime.of(8, 15));
		att1.setOffTime(LocalTime.of(18, 15));
		att1.setFromDate(LocalDate.of(2020, 11, 20));
		att1.setToDate(LocalDate.of(2020, 11, 22));
		att1.setStatus("Pending");
		Attendance att2 = new Attendance();
		att2.setEmployee(null);
		att2.setInTime(LocalTime.of(8, 15));
		att2.setOffTime(LocalTime.of(18, 15));
		att2.setFromDate(LocalDate.of(2020, 11, 20));
		att2.setToDate(LocalDate.of(2020, 11, 22));
		att2.setStatus("Pending");
		
		testenti.persist(att1);
        assertNotNull(attr.findAll());
        att.add(att2);
        att.add(att1);
        assertThat(attr.findAll()).isEqualTo(att);
	}
	
	@Test
	void testNewAttendances() throws Exception {
		Attendance att1 = new Attendance();
		att1.setEmployee(null);
		att1.setInTime(LocalTime.of(8, 15));
		att1.setOffTime(LocalTime.of(18, 15));
		att1.setFromDate(LocalDate.of(2020, 11, 20));
		att1.setToDate(LocalDate.of(2020, 11, 22)); 
		att1.setStatus("Pending");
		Attendance att=attr.save(att1);
		assertNotNull(att1);
		assertEquals(att1,att);
		
	}
	
	
	@Test
	void testDeleteAttendance() throws Exception{
		Attendance att1 = new Attendance();
		att1.setEmployee(null);
		att1.setInTime(LocalTime.of(8, 15));
		att1.setOffTime(LocalTime.of(18, 15));
		att1.setFromDate(LocalDate.of(2020, 11, 20));
		att1.setToDate(LocalDate.of(2020, 11, 22));
		att1.setStatus("Pending");
		Attendance att=testenti.persist(att1);
		attr.delete(att);
		List<Attendance> emp=(List<Attendance>)attr.findAll();
		assertEquals(0,emp.size());
		
	}
	
	@Test
	void testGetEmployee() throws Exception{
		Employee emp=new Employee();
		Attendance att1 = new Attendance();
		att1.setEmployee(null);
		att1.setInTime(LocalTime.of(8, 15));
		att1.setOffTime(LocalTime.of(18, 15));
		att1.setFromDate(LocalDate.of(2020, 11, 20));
		att1.setToDate(LocalDate.of(2020, 11, 22));
		att1.setStatus("Pending");
		testenti.persist(att1);
		testenti.persist(emp);
		assertNotNull(attr.findByEmpId(emp.getEmployeeId()));
	}
	
}
