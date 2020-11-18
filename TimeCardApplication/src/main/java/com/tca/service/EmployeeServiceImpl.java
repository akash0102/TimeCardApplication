package com.tca.service;

import com.tca.dao.EmployeeDao;
import com.tca.entity.Employee;

public class EmployeeServiceImpl implements EmployeeService {
	
	EmployeeDao empDao=new EmployeeDao();
	
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
