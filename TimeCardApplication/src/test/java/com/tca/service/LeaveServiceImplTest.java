package com.tca.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.tca.entity.Employee;
import com.tca.entity.Leave;
import com.tca.repository.LeaveRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
class LeaveServiceImplTest {
	
	@MockBean
	private LeaveRepository leaveRep;

	@Autowired
	private LeaveService leaveService;
	private Leave lea;
	@Mock
	private Leave lea2;
	@BeforeEach
	void setUp() throws Exception {
		Employee emp=new Employee();
		int id=3;
		emp.setEmployeeId(4);
		lea=new Leave();
		lea2=new Leave();
		lea2.setLeaveId(id);
		lea.setLeaveId(id);
		lea.setFromDate(LocalDate.now());
		lea.setToDate(LocalDate.now());
		lea2.setEmployee(emp);
		lea.setStatus("Pending");
	}
	    
	@Test
	void testAddLeave() {
		Mockito.when(leaveRep.save(lea)).thenReturn(lea);
	    assertThat(leaveService.addLeave(lea)).isEqualTo(lea);
	}
	
    @Test
    void testRemoveLeave() throws Exception{
    	Assert.assertEquals(leaveService.removeLeave(4), -1);
    }
    
    
    @Test
    void testUpdateLeave() throws Exception{
    	
    	Mockito.when(leaveRep.save(lea)).thenReturn(lea); 
		assertThat(leaveService.updateLeave(3, LocalDate.MIN,LocalDate.MAX)).isEqualTo(3);
    }
    
    
	@Test
	void testFindByEmpId() throws Exception{
		ArrayList<Leave> checkList=new ArrayList<>();
		checkList.add(lea);
		checkList.add(lea2);
		Mockito.when(leaveService.findByEmpId(3)).thenReturn(checkList);
		assertThat(leaveService.findByEmpId(3)).isEqualTo(checkList);
	}
}