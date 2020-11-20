package com.tca.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.tca.entity.Employee;
import com.tca.entity.Leave;

@RunWith(SpringRunner.class)
@DataJpaTest
class LeaveRepositoryTest {
	@Autowired
	private LeaveRepository leadao;
	
	@Autowired
	private TestEntityManager tenman;
	
	/*@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}*/


	@Test
    public void testaddLeave() throws Exception{
       Leave leave=new Leave();
       leave.setLeaveId(2);
       leave.setStatus("Pending");
       leave.setEmployee(new Employee());
       leave.setFromDate(LocalDate.now());
       leave.setToDate(LocalDate.MAX);
     
        Leave saveLeave=leadao.save(leave);
        assertNotNull(saveLeave);
    }
	}


