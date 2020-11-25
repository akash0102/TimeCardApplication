package com.tca.service;

import java.util.List;

import com.tca.entity.Leave;
import com.tca.exception.ResourceNotFoundException;

public interface LeaveService {

	public Leave addLeave(Leave leave);

	public Leave findLeave(int leaveId) throws ResourceNotFoundException;

	int removeLeave(int leaveId) throws ResourceNotFoundException;

	List<Leave> findByEmpId(int empId);

	public int updateLeave(Integer leaveId, Leave leave) throws ResourceNotFoundException;

}
