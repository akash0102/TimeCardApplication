package com.tca.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.tca.entity.DateId;
import com.tca.entity.Employee;
import com.tca.entity.Leave;

@RunWith(SpringRunner.class)
@DataJpaTest
class LeaveDaoTest {
	@Autowired
	private LeaveDao leadao;
	
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
       DateId date=new DateId();
       date.setDateNum(1);
       date.setfromDate(null);
       date.settoDate(null);
      
     
        Leave saveLeave=leadao.save(leave);
        assertNotNull(saveLeave);
    }
	}


