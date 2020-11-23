package com.tca.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.tca.entity.Employee;
import com.tca.entity.Manager;
import com.tca.repository.ManagerRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ManagerServiceImplTest {
	
	@MockBean
    private ManagerRepository managerRepository;
	
	@Autowired
	private ManagerService managerService;
	
	@Test
	public  void testCreateManager() throws Exception{
		Manager manager=new Manager();
		manager.setManagerId(1);
		Employee emp=new Employee(10,"085126767","085126767","mainas@gmail.com",manager);
		manager.setEmpl(emp);
		Mockito.when(managerRepository.save(manager)).thenReturn(manager);  
		assertThat(managerService.createManager(manager)).isEqualTo(manager);               
	}
	  
	@Test
	public void testGetAllManager() throws Exception{
		Manager manager1=new Manager();
		manager1.setManagerId(1);
		Employee emp=new Employee(10,"085126767","085126767","mainas@gmail.com",manager1);
		manager1.setEmpl(emp);
		 
		Manager manager2=new Manager();
		manager2.setManagerId(1);
		Employee emp2=new Employee(10,"085126767","085126767","mainas@gmail.com",manager2);
		manager2.setEmpl(emp2); 
		List<Manager> managerlist = new ArrayList<>();
		managerlist.add(manager1);
		managerlist.add(manager2);
		        
		Mockito.when(managerRepository.findAll()).thenReturn(managerlist);  
		assertThat(managerService.getAllManager()).isEqualTo(managerlist);
		    	
	}  
	@Test
	public void testDeleteManager() throws Exception{
		Manager manager=new Manager();
		manager.setManagerId(1);
		Employee emp=new Employee(10,"085126767","085126767","mainas@gmail.com",manager);
		manager.setEmpl(emp);
		managerRepository.deleteById(manager.getManagerId());
		System.out.println(managerRepository.findById(1));
		Assert.assertTrue(managerRepository.findById(1).isEmpty());    
	}  
	@Test
	public void testUpdateManager() throws Exception{
		Manager manager2=new Manager();
		manager2.setManagerId(1);
		Employee emp=new Employee(10,"085126767","085126767","mainas@gmail.com",manager2);
		manager2.setEmpl(emp);
		managerRepository.deleteById(manager2.getManagerId());
		System.out.println(managerRepository.findById(1));
		Assert.assertTrue(managerRepository.findById(1).isEmpty());    
		Assert.assertTrue(managerRepository.findById(0).isEmpty());
	 
	}
  
	 
}
