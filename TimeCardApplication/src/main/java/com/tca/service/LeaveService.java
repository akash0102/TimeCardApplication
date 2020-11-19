package com.tca.service;

import java.time.LocalDate;
import java.util.List;

import com.tca.entity.Employee;
import com.tca.entity.Leave;

public interface LeaveService {

	public Leave addLeave(LocalDate fromDate, LocalDate toDate);

	public Leave findLeave(int leaveId);

	boolean removeLeave(int leaveId);

	List<Leave> findByEmpId(Employee emp);

}
