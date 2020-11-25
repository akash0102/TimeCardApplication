package com.tca.service;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
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
	
	Logger log=Logger.getLogger(getClass());
	
	public Manager createManager( @RequestBody Manager manager) {
		log.info("manager details saved");
		return  managerRepository.save(manager); 
	}
	 
	public Manager updateManager(@PathVariable(value = "id") Integer managerId,
		 @RequestBody Manager managerDetails) throws ResourceNotFoundException {
		Manager manager = managerRepository.findById(managerId)
			.orElseThrow(() -> new ResourceNotFoundException("Company Manager not found for this id :: " + managerId));
		manager.setManagerId(managerDetails.getManagerId());
		manager.setEmpl(managerDetails.getEmpl());
		final Manager updatedManager = managerRepository.save(manager);
		log.info("manager details updated");
		return updatedManager; 
		
	}
	 
	 public boolean deleteManager(@PathVariable(value = "id") Integer managerId)
			throws ResourceNotFoundException {
		 Manager manager = managerRepository.findById(managerId)
				.orElseThrow(() -> new ResourceNotFoundException(" Manager not found for this id :: " + managerId));
		managerRepository.delete(manager);
		log.info("manager details deleted successfully");
		return true;
	}
	 
	public List<Manager> getAllManager() {
		log.info("all manager details retrieved");
		return managerRepository.findAll();
	} 
	
	public Set<Employee> getEmployees(int manId){
		Manager another=managerRepository.getOne(manId);
		log.info("employee details fetched");
		return another.getEmpl();
	}

	@Override
	public Manager getManagerById(Integer managerId) {
		Manager man=managerRepository.getOne(managerId);
		log.info("manager with id " + managerId + " retrieved");
		return man;
	}
}
