package com.tca.service;

import java.util.List;

import com.tca.entity.DateId;
import com.tca.entity.Employee;
import com.tca.entity.Leave;

public interface LeaveService {

	boolean addLeave(Employee emp, DateId dateId);

	boolean findLeave(int leaveId);

	boolean removeLeave(int leaveId);

	List<Leave> viewAllLeaves(Employee emp);

}
