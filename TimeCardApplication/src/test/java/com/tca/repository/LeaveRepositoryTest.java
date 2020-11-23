package com.tca.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.Assert;
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
	
	


	@Test
    void testaddLeave() throws Exception{
		Employee emp=new Employee();
       Leave leave=new Leave();
       leave.setLeaveId(2);
       leave.setStatus("Pending");
       leave.setEmployee(emp);
     tenman.persist(emp);
    // tenman.persist(date);
        Leave saveLeave=leadao.save(leave);
        assertNotNull(saveLeave);
    }
	@Test
    void testRemoveLeave() throws Exception{
		Employee emp=new Employee();
		emp.setEmployeeId(4);
	 Leave lea=new Leave();
	Leave lea2=new Leave();
		lea2.setEmployee(emp);
		lea.setStatus("Pending");
       //  tenman.persist(emp);
        Leave leave=tenman.persist(lea);
        
      //tenman.persist(lea2);

       
        tenman.remove(leave);

        List<Leave> employees = (List<Leave>)  leadao.findAll();
        Assert.assertEquals(0,employees.size());

	}
	@Test
    void testViewAllLeaves() throws Exception{
		Employee emp=new Employee();
		emp.setEmployeeId(4);
	 Leave lea=new Leave();
	Leave lea2=new Leave();
		lea2.setEmployee(emp);
		lea.setStatus("Pending");
        // tenman.persist(lea);
        tenman.persist(lea);
        
      // tenman.persist(lea2);
       List<Leave> leaveList = (List<Leave>) leadao.findAll();

       Assert.assertEquals(1, leaveList.size());


	}
	@Test
    void testUpdateLeave() throws Exception{
		Employee emp=new Employee();
	       Leave leave=new Leave();
	       leave.setLeaveId(2);
	       leave.setStatus("Pending");
	       leave.setEmployee(emp);
	       
	   
	      assertNotNull(leadao.findById(2));
}

}
