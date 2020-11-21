package com.tca.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.tca.entity.DateId;
import com.tca.entity.Employee;
import com.tca.entity.Leave;
import com.tca.entity.TimeCard;
import com.tca.repository.LeaveRepository;



@RunWith(SpringRunner.class)
@DataJpaTest
class LeaveDaoTest {
	@Autowired
	private LeaveRepository leadao;
	
	@Autowired
	private TestEntityManager tenman;
	
	


	@Test
    public void testaddLeave() throws Exception{
		Employee emp=new Employee();
       Leave leave=new Leave();
       leave.setLeaveId(2);
       leave.setStatus("Pending");
       leave.setEmployee(emp);
       DateId date=new DateId();
       date.setDateNum(1);
       date.setfromDate(null);
       date.settoDate(null);
     leave.setDateId(date);
     date.setemployeeId(emp);
     tenman.persist(emp);
    // tenman.persist(date);
        Leave saveLeave=leadao.save(leave);
        assertNotNull(saveLeave);
    }
	@Test
    public void testRemoveLeave() throws Exception{
		Employee emp=new Employee();
		DateId dat=new DateId();
		dat.setfromDate(LocalDate.now());
		dat.settoDate(LocalDate.now());
		emp.setEmployeeId(4);
	 Leave lea=new Leave();
	Leave lea2=new Leave();
		lea2.setEmployee(emp);
		lea.setDateId(dat);
		lea.setStatus("Pending");
       //  tenman.persist(emp);
        Leave leave=tenman.persist(lea);
        
      //tenman.persist(lea2);

       
        tenman.remove(leave);

        List<Leave> employees = (List<Leave>)  leadao.findAll();
        Assert.assertEquals(0,employees.size());

	}
	@Test
    public void testViewAllLeaves() throws Exception{
		Employee emp=new Employee();
		DateId dat=new DateId();
		dat.setfromDate(LocalDate.now());
		dat.settoDate(LocalDate.now());
		emp.setEmployeeId(4);
	 Leave lea=new Leave();
	Leave lea2=new Leave();
		lea2.setEmployee(emp);
		lea.setDateId(dat);
		lea.setStatus("Pending");
        // tenman.persist(lea);
        tenman.persist(lea);
        
      // tenman.persist(lea2);
       List<Leave> leaveList = (List<Leave>) leadao.findAll();

       Assert.assertEquals(1, leaveList.size());


	}
	@Test
    public void testUpdateLeave() throws Exception{
		Employee emp=new Employee();
	       Leave leave=new Leave();
	       leave.setLeaveId(2);
	       leave.setStatus("Pending");
	       leave.setEmployee(emp);
	       leave.setDateId(new DateId());
	       
	   
	      assertNotNull(leadao.findById(2));
}

}
