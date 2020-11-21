package com.tca.service;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.PathVariable;

import com.tca.entity.DateId;
import com.tca.entity.Employee;
import com.tca.entity.Leave;
import com.tca.entity.TimeCard;

public interface LeaveService {

	public Leave addLeave(Leave lea);

	int updateLeave(int leaveId,Leave lea);
	int removeLeave(int leaveId);

	List<Leave> findByEmpId(int empId);

}
