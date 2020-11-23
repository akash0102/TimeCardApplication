package com.tca.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.tca.entity.Attendance;
import com.tca.entity.Employee;
import com.tca.repository.AttendanceRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
class AttendanceServiceImplTest {

	@MockBean
    private AttendanceRepository attd;
	
	@Autowired
    private AttendanceService atts;
	@Mock
	private Attendance att1;
	@Mock
	private Attendance att2;
	
	@BeforeEach
	void setUp() throws Exception {
		Employee emp=new Employee();
		emp.setEmployeeId(1);
		att1 = new Attendance();
		att1.setEmployee(emp);
		att1.setAttendanceId(4);
		att1.setInTime(LocalTime.of(8, 15));
		att1.setOffTime(LocalTime.of(18, 15));
		att1.setFromDate(LocalDate.of(2020, 11, 20));
		att1.setToDate(LocalDate.of(2020, 11, 22));
		att1.setStatus("Pending");
		att2 = new Attendance();
		att2.setEmployee(emp);
		att2.setAttendanceId(3);
		att2.setInTime(LocalTime.of(8, 15));
		att2.setOffTime(LocalTime.of(18, 15));
		att2.setFromDate(LocalDate.of(2020, 11, 20));
		att2.setToDate(LocalDate.of(2020, 11, 22));
		att2.setStatus("Pending");
		
	}

	@AfterEach
	void tearDown() throws Exception {
		att1=null;
		att2=null;
	}

	@Test
	void testGetAllAttendance() throws Exception{
		att1=new Attendance();
		attd.save(att1);
		Mockito.when(attd.save(att1)).thenReturn(att1);
		assertThat(atts.saveAttendanceDetails(att1)).isEqualTo(att1);
	}

	@Test
	void testGetAttendanceDetailsById() throws Exception{
		ArrayList<Attendance> attlist=new ArrayList<>();
		attlist.add(att1);
		attlist.add(att2);
		Mockito.when(atts.getAttendanceDetailsById(1)).thenReturn(attlist);
		assertThat(atts.getAttendanceDetailsById(1)).isEqualTo(attlist);
	}

	@Test
	void testDeleteAttendanceDetailsById() throws Exception {
		Employee emp=new Employee();
		emp.setEmployeeId(1);
		att1 = new Attendance();
		att1.setEmployee(emp);
		att1.setAttendanceId(4);
		att1.setInTime(LocalTime.of(8, 15));
		att1.setOffTime(LocalTime.of(18, 15));
		att1.setFromDate(LocalDate.of(2020, 11, 20));
		att1.setToDate(LocalDate.of(2020, 11, 22));
		att1.setStatus("Pending");
		attd.save(att1);
		
		Assert.assertEquals(atts.deleteAttendanceDetailsById(4),true);
	}

	@Test
	void testUpdateAttendanceById() throws Exception{
		Mockito.when(attd.save(att1)).thenReturn(att1);
		assertThat(atts.updateAttendanceById(1, att1)).isEqualTo(att2);
	}

	@Test
	void testSaveAttendanceDetails() throws Exception{
		att1=new Attendance();
		attd.save(att1);
		Mockito.when(attd.save(att1)).thenReturn(att1);
		assertThat(atts.saveAttendanceDetails(att1)).isEqualTo(att1);
	}

}
