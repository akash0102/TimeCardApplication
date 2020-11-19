package com.tca.repository;

import org.apache.log4j.Logger;

import com.tca.entity.Employee;
import com.tca.util.JPaUtil;

public class EmployeeDao {
	
	Logger log=Logger.getLogger(getClass()); 
	boolean check;
	
	public boolean searchEmployee(Employee employee) {
		JPaUtil.createEM();
		Employee emp=null;
		try {
		emp=JPaUtil.entMan.find(Employee.class, employee.getEmployeeId());
		if(emp.equals(employee)) {
			check=true;
		}
		else {
			check=false;
		}
		}
		catch(NullPointerException e) {
			log.debug(e);
			try {
			emp=employee;
			emp.setEmployeeName("NotFound");
			}
			catch(NullPointerException em) {
				check=false;
			}
		}
		JPaUtil.closeEM();
		return check;
	}

	public boolean editDetails(Employee employee) {
		JPaUtil.createEM();
		try {
		JPaUtil.entMan.getTransaction().begin();
		JPaUtil.entMan.merge(employee);
		JPaUtil.entMan.getTransaction().commit();
		check=true;
		}
		catch(Exception e) {
			check=false;
			log.debug(e);
		}
		return check;
	}

}
