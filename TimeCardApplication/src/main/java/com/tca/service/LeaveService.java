package com.tca.service;

import java.util.List;

import com.tca.entity.DateId;
import com.tca.entity.Employee;
import com.tca.entity.Leave;

public interface LeaveService {

	public Leave addLeave(DateId date);

	public Leave findLeave(int leaveId);

	boolean removeLeave(int leaveId);

	List<Leave> findByEmpId(Employee emp);

}
