package com.tca.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.tca.entity.Employee;
import com.tca.entity.TimeCard;


@RunWith(SpringRunner.class)
@DataJpaTest
class TimeCardRepositoryTest {
	
	@Autowired
	private TimeCardRepository tcardRepo;
	
	@Autowired
	private TestEntityManager tenman;
	
	private TimeCard tcard1,tcard2;
	
	
	@BeforeEach
	void setUp() throws Exception {
		tcard1=new TimeCard(new Employee(),LocalDate.now(),LocalTime.parse("10:02:03.000"),LocalTime.parse("22:02:45.000"),"Pending");
    	tcard2=new TimeCard(new Employee(),LocalDate.now(),LocalTime.parse("08:32:51.000"),LocalTime.parse("19:04:37.000"),"Pending");
        tenman.persist(tcard2);
	}
	
	@AfterEach
	void tearDown() throws Exception{
		tcard1=null;
		tcard2=null;
		
	}
	
	@Test
    void testCreateTimeCard() throws Exception{
        TimeCard savedTimeCard=tcardRepo.save(tcard1);
        assertNotNull(savedTimeCard);
        assertEquals(savedTimeCard,tcard1);
    }
	
	@Test
    void testDeleteTimeCard() throws Exception{
		TimeCard tcard = tenman.persist(tcard1);

        //delete one ticket DB
        tcardRepo.delete(tcard);

        List<TimeCard> employees = (List<TimeCard>)  tcardRepo.findAll();
        assertEquals(1,employees.size());
    }
	
	@Test
	void testFindAll() throws Exception{
		List<TimeCard> listOfEmps=new ArrayList<>();
        tenman.persist(tcard1);
        assertNotNull(tcardRepo.findAll());
        listOfEmps.add(tcard1);
        listOfEmps.add(tcard2);
        assertThat(tcardRepo.findAll()).isEqualTo(listOfEmps);
	}
	
	@Test
	void testFindEmp() throws Exception{
		
		tenman.persist(tcard2);
		
		assertNotNull(tcardRepo.findByEmp(new Employee()));
		
		
		
		
	}
}
