package com.tca.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.junit.Assert;
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
	private TimeCardRepository tcarddao;
	
	@Autowired
	private TestEntityManager tenman;
	
	@Test
    void testCreateTimeCard() throws Exception{
        TimeCard tcard=new TimeCard();
        tcard.setDate(LocalDate.now());
        tcard.setEmployee(new Employee());
        tcard.setStatus("Pending");
        tcard.setTimeCardId(01222);
        tcard.setTimeEntry(LocalTime.NOON);
        tcard.setTimeExit(LocalTime.MIDNIGHT);
        TimeCard savedTimeCard=tcarddao.save(tcard);
        assertNotNull(savedTimeCard);
    }
	
	@Test
    void testDeleteTimeCard() throws Exception{
		
    	TimeCard tcard1=new TimeCard(new Employee(),LocalDate.now(),LocalTime.parse("10:02:03.000"),LocalTime.parse("22:02:45.000"),"Pending");
    	TimeCard tcard2=new TimeCard(new Employee(),LocalDate.now(),LocalTime.parse("08:32:51.000"),LocalTime.parse("19:04:37.000"),"Pending");
        TimeCard tcard = tenman.persist(tcard1);
        tenman.persist(tcard2);

        //delete one ticket DB
        tenman.remove(tcard);

        List<TimeCard> employees = (List<TimeCard>)  tcarddao.findAll();
        Assert.assertEquals(1,employees.size());

    }
	
	
}
