package com.tca.service;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
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
	
	Logger log=Logger.getLogger(getClass());
	
	@Override
	public Leave addLeave(Leave leave) {
		log.info("leave saved");
		return leaveRep.save(leave); 
	}

	@Override
	public int removeLeave(int leaveId) {
		Optional<Leave> toDelete= leaveRep.findById(leaveId);
		if(toDelete.isPresent()) {
			leaveRep.delete(toDelete.get());	
		}
		log.info("removed leave");
		return leaveRep.findById(leaveId).isEmpty()?-1:leaveId;
	}
	
	
	@Override
	public int updateLeave(Integer leaveId,Leave lea) throws ResourceNotFoundException { 
		
		Leave leave=leaveRep.findById(leaveId).orElseThrow(() -> new ResourceNotFoundException("Leave not found for this id :: " + leaveId));

		leave.setFromDate(lea.getFromDate());
		leave.setToDate(lea.getToDate());
		
		leaveRep.save(leave);
		log.info("updated Leave");
		return leave.getLeaveId();
	}
	
	@Override
	public List<Leave> findByEmpId(int empId) {
		log.info("Found Leaves taken by an employee");
		return leaveRep.findByEmpId(empId);
		
	}


	@Override
	public Leave findLeave(int leaveId) throws ResourceNotFoundException {
		log.info("all leaves");
		return leaveRep.findById(leaveId).orElseThrow(() -> new ResourceNotFoundException("Leave not found for this id :: " + leaveId));
	}

	

}