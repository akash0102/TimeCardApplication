package com.tca.service;

import com.tca.entity.Employee;
import com.tca.repository.EmployeeRepository;

public class EmployeeServiceImpl implements EmployeeService {
	
	EmployeeRepository empDao=new EmployeeRepository();
	
	@Override
	public boolean viewDetails(Employee employee) {
		return empDao.searchEmployee(employee);
	}
	
	@Override
	public boolean editProfile(Employee employee) {
		boolean check=false;
		if(!empDao.searchEmployee(employee)){
			check=empDao.editDetails(employee);	
		}
		return check;
		
	}
	

}
