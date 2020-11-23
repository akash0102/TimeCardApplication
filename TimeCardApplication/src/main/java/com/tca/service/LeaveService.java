package com.tca.service;

import java.time.LocalDate;
import java.util.List;

import com.tca.entity.Leave;
import com.tca.exception.ResourceNotFoundException;

public interface LeaveService {

	public Leave addLeave(Leave leave);

	public Leave findLeave(int leaveId) throws ResourceNotFoundException;

	int removeLeave(int leaveId);

	int updateLeave(int leaveId, LocalDate fromDate, LocalDate toDate) throws ResourceNotFoundException;

	List<Leave> findByEmpId(int empId);

}
