package com.tca.dao;

import com.tca.entity.Employee;
import com.tca.entity.EmployeeAccount;
import com.tca.util.JPaUtil;

public class EmployeeAccountDao {
	
	public Employee login(EmployeeAccount empacct) {
		JPaUtil.createEM();
		empacct=JPaUtil.entMan
				 .createNamedQuery("EmployeeAccount.getEmpByCreds", EmployeeAccount.class)
				 .setParameter("emp", empacct.getUserId()).setParameter("pwd",empacct.getPassword()).getSingleResult();
		return JPaUtil.entMan.find(Employee.class, empacct.getEmployee().getEmployeeId());
	}

}
