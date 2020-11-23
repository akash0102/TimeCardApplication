package com.tca.repository;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.tca.entity.Employee;
import com.tca.entity.Manager;



@RunWith(SpringRunner.class)
@DataJpaTest
public class ManagerRepositoryTest {
	
	@Autowired
	private ManagerRepository ManagerRepository;

	@Autowired
	private TestEntityManager testEntityManager;
	    

	@Test
	public void testCreateManager() throws Exception{
	    Manager manager=new Manager();
	    manager.setManagerId(45);
	    Employee emp=new Employee(5,"JIO","9701531212","abcd@gmail.com", manager);
	    manager.setEmpl(emp);
	    Manager savedManager=ManagerRepository.save(manager);
	    assertNotNull(savedManager);
	}
	    
	    
	@Test
	public void testDeleteManager() throws Exception{
		Manager manager1=new Manager();
	   	manager1.setManagerId(2550);
	   	Employee emp=new Employee(5,"JIO","9701531212","abcd@gmail.com", manager1);
		manager1.setEmpl(emp);
	    Manager manager2=new Manager();
	    manager2.setManagerId(22);
	    Employee emp2=new Employee(5,"JIO","9701531212","abcd@gmail.com", manager2);
		manager2.setEmpl(emp2);
	    	 
	    testEntityManager.persist(manager1);
	    testEntityManager.persist(manager2);

	    testEntityManager.remove(manager1);

	    List< Manager > managers= (List< Manager >)  ManagerRepository.findAll();
	  	  
	    Assert.assertEquals(1,managers.size());
	}
    @Test
    public void testGetAllManagers() throws Exception{
    	Manager manager1=new Manager();
    	manager1.setManagerId(1);
    	Employee emp=new Employee(10,"MARINA","08512","marinas@gmail.com",manager1);
    	manager1.setEmpl(emp);
    	
    	Manager manager2=new Manager();
    	manager2.setManagerId(0);
    	Employee emp3=new Employee(12,"MARINA","08512","marinas@gmail.com",manager2);
    	manager1.setEmpl(emp3);
    	
    	testEntityManager.persist(manager1);
    	testEntityManager.persist(manager2);
    	
    	List<Manager> managerlist = (List<Manager>)ManagerRepository.findAll();
	    Assert.assertEquals(2, managerlist.size());
    }
    @Test
    public void testUpdateManager(){

    	Manager manager2=new Manager();
    	manager2.setManagerId(0);
    	Employee emp=new Employee(10,"MARINA","08512","marinas@gmail.com",manager2);
    	manager2.setEmpl(emp);
    	
    	testEntityManager.persist(manager2);
    	Manager  getFromDb =ManagerRepository.findById(0).get();
    	getFromDb.setManagerId(1);
    	testEntityManager.persist(getFromDb);
    	 
    	assertThat(getFromDb.getManagerId()).isEqualTo(1);
    }

}
