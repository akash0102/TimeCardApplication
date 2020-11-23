package com.tca.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

//import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tca.entity.Leave;
import com.tca.exception.ResourceNotFoundException;
import com.tca.repository.LeaveRepository;


@Service

public class LeaveServiceImpl implements LeaveService{
	@Autowired
	LeaveRepository leaveRep;
	
	Leave lea;
	
	@Override
	public Leave addLeave(Leave leave) {
		return leaveRep.save(leave);
		
	}

	@Override
	public int removeLeave(int leaveId) {
		Optional<Leave> toDelete= leaveRep.findById(leaveId);
		if(toDelete.isPresent()) {
			leaveRep.delete(toDelete.get());	
		}
		return leaveRep.findById(leaveId).isEmpty()?-1:leaveId;
	}
	
	
	@Override
	public int updateLeave(int leaveId,LocalDate fromDate, LocalDate toDate) throws ResourceNotFoundException {
		
		Leave leave=leaveRep.findById(leaveId).orElseThrow(() -> new ResourceNotFoundException("Leave not found for this id :: " + leaveId));

		leave.setFromDate(fromDate);
		leave.setToDate(toDate);
		
		leaveRep.save(leave);
		return leave.getLeaveId();
	}
	
	@Override
	public List<Leave> findByEmpId(int empId) {
		
		return leaveRep.findByEmpId(empId);
		
	}


	@Override
	public Leave findLeave(int leaveId) throws ResourceNotFoundException {
		
		return leaveRep.findById(leaveId).orElseThrow(() -> new ResourceNotFoundException("Leave not found for this id :: " + leaveId));
	}

	

}