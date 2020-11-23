package com.tca.service;

import java.time.LocalDate;
import java.util.List;

import com.tca.entity.Leave;

public interface LeaveService {

	public Leave addLeave(Leave leave);

	public Leave findLeave(int leaveId);

	int removeLeave(int leaveId);

	int updateLeave(int leaveId, LocalDate fromDate, LocalDate toDate);

	List<Leave> findByEmpId(int empId);

}
