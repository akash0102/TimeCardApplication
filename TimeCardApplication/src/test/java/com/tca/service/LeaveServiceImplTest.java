package com.tca.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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


import com.tca.entity.DateId;
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
			DateId dat=new DateId();
			dat.setfromDate(LocalDate.now());
			dat.settoDate(LocalDate.now());
			emp.setEmployeeId(4);
			lea=new Leave();
			lea2=new Leave();
			lea2.setLeaveId(3);
			lea.setLeaveId(3);
			lea2.setEmployee(emp);
			lea.setDateId(dat);
			lea.setStatus("Pending");
			
	    }
	@Test
	void testAddLeave() {
		DateId dat=new DateId();
		dat.setfromDate(LocalDate.now());
		dat.settoDate(LocalDate.now());
		Leave lev=new Leave();
		lev.setDateId(dat);
		leaveRep.save(lev);
		 Mockito.when(leaveRep.save(lev)).thenReturn(lev);
	        assertThat(leaveService.addLeave(lev)).isEqualTo(lev);
	    }
	
    @Test
        public void testRemoveLeave() throws Exception{
       
            Assert.assertEquals(leaveService.removeLeave(4), -1);

}
    @Test
    public void testUpdateLeave() throws Exception{
    	
    	Mockito.when(leaveRep.save(lea)).thenReturn(lea);
		assertThat(leaveService.updateLeave(3, lea)).isEqualTo(lea2.getLeaveId());
    }
	@Test
	public void testFindByEmpId() throws Exception{
		ArrayList<Leave> checkList=new ArrayList<>();
		checkList.add(lea);
		checkList.add(lea2);
		Mockito.when(leaveService.findByEmpId(3)).thenReturn(checkList);
		assertThat(leaveService.findByEmpId(3)).isEqualTo(checkList);
	}
}