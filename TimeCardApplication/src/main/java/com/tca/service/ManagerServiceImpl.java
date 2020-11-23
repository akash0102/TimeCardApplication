package com.tca.service;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.tca.exception.ResourceNotFoundException;
import com.tca.entity.Employee;
import com.tca.entity.Manager;
import com.tca.repository.ManagerRepository; 




@Service
@Transactional
public class ManagerServiceImpl  implements ManagerService {
	
	@Autowired
	private ManagerRepository managerRepository;
	
	
	public Manager createManager( @RequestBody Manager manager) {
		return  managerRepository.save(manager); 
	}
	 
	public Manager updateManager(@PathVariable(value = "id") Integer managerId,
		 @RequestBody Manager managerDetails) throws ResourceNotFoundException {
		Manager manager = managerRepository.findById(managerId)
			.orElseThrow(() -> new ResourceNotFoundException("Company Manager not found for this id :: " + managerId));
		manager.setManagerId(managerDetails.getManagerId());
		manager.setEmpl(managerDetails.getEmpl());
		final Manager updatedManager = managerRepository.save(manager);
		return updatedManager; 
		
	}
	 
	 public boolean deleteManager(@PathVariable(value = "id") Integer managerId)
			throws ResourceNotFoundException {
		 Manager manager = managerRepository.findById(managerId)
				.orElseThrow(() -> new ResourceNotFoundException(" Manager not found for this id :: " + managerId));
	
		managerRepository.delete(manager);
		return true;
	}
	 
	public List<Manager> getAllManager() { 
		return managerRepository.findAll();
	} 
	
	public Set<Employee> getEmployees(int manId){
		Manager another=managerRepository.getOne(manId);
		return another.getEmpl();
	}

	@Override
	public Manager getManagerById(Integer managerId) {
		
		return managerRepository.getOne(managerId);
	}
}
