package com.tca.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Optional;

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

import com.tca.entity.Employee;
import com.tca.entity.TimeCard;
import com.tca.exception.ResourceNotFoundException;
import com.tca.repository.TimeCardRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
class TimeCardServiceImplTest {

	@MockBean
	private TimeCardRepository tcardrepo;
	
	@Autowired
	private TimeCardService tcardservice;
	

	private TimeCard tca;
	private TimeCard tca2;
	
	
	
	@BeforeEach
	void setUp() throws Exception {
		Employee emp=new Employee(); 
		emp.setEmployeeId(3);
		tca=new TimeCard();
		tca2=new TimeCard();
		tca2.setEmployee(emp);
		tca.setDate(LocalDate.of(2020, 2, 13));
		tca.setTimeEntry(LocalTime.MIN);
		tca.setTimeExit(LocalTime.MAX);
		tca.setEmployee(emp);
		tca.setStatus("Pending");
	}
	 
	  
	
	@AfterEach
	void tearDown() throws Exception {
		tca=null;
		tca2=null;
	}
	 

	@Test
	void testNewSaveEntry() {
		
		Mockito.when(tcardrepo.save(tca)).thenReturn(tca);
		assertThat(tcardservice.saveTimeEntry(tca)).isEqualTo(tca);
		
	}
	
	@Test
	void testNewSaveEntryFalse() {
		
		Mockito.when(tcardrepo.save(tca)).thenReturn(tca);
		assertThat(tcardservice.saveTimeEntry(tca)).isNotEqualTo(tca2);

	}
	
	@Test
	void testDisplayEntries() throws Exception{
		ArrayList<TimeCard> checkList=new ArrayList<>();
		checkList.add(tca);
		checkList.add(tca2);
		Mockito.when(tcardservice.displayEntries(3)).thenReturn(checkList);
		assertThat(tcardservice.displayEntries(3)).isEqualTo(checkList);
	}
	
	@Test
	void testFailDisplayEntries() throws Exception{
		ArrayList<TimeCard> checkList=new ArrayList<>();
		ArrayList<TimeCard> newlist=new ArrayList<>();
		checkList.add(tca);
		checkList.add(tca2);
		Mockito.when(tcardservice.displayEntries(3)).thenReturn(checkList);
		assertThat(tcardservice.displayEntries(3)).isNotEqualTo(newlist);
	}
		
	
	
	
	@Test
	void testRemoveEntry() {
		Mockito.when(tcardrepo.findById(tca.getTimeCardId())).thenReturn(Optional.of(tca));
		try {
			assertThat(tcardservice.removeEntry(tca.getTimeCardId())).isFalse();
		} catch (ResourceNotFoundException e) {
			assertThat(e.getMessage()).isEqualTo("TimeCard not found for this id :: " + tca.getTimeCardId());
		}
	}
	  
	@Test
	void testFailRemoveEntry() {
		Mockito.when(tcardrepo.findById(tca.getTimeCardId())).thenReturn(Optional.of(tca));
		try {
			assertThat(tcardservice.removeEntry(3)).isTrue();
		} catch (ResourceNotFoundException e) {
			assertThat(e.getMessage()).isEqualTo("TimeCard not found for this id :: " + tca.getTimeCardId());
		}
	}
	
	@Test
	void testUpdate(){
		tca.setTimeCardId(3);
		Mockito.when(tcardrepo.findById(tca.getTimeCardId())).thenReturn(Optional.of(tca));
		try {
			assertThat(tcardservice.updateEntries(0, LocalDate.now(), LocalTime.MIN, LocalTime.MAX)).isEqualTo(3);
		} catch (ResourceNotFoundException e) {
			assertThat(e.getMessage()).isEqualTo("TimeCard not found for this id :: " + tca.getTimeCardId());
		}
	}
	
	@Test
	void testUpdateFail() {
		tca.setTimeCardId(4);
		Mockito.when(tcardrepo.findById(tca.getTimeCardId())).thenReturn(Optional.of(tca));
		try {
			assertThat(tcardservice.updateEntries(4, tca.getDate(),tca.getTimeEntry(),tca.getTimeExit())).isNotZero();
		} catch (ResourceNotFoundException e) {
			assertThat(e.getMessage()).isEqualTo("TimeCard not found for this id :: " + tca.getTimeCardId());
		}
	}
}
