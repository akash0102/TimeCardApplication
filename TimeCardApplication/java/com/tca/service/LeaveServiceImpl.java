package com.tca.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

//import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.tca.entity.DateId;
import com.tca.entity.Employee;
import com.tca.entity.Leave;
import com.tca.entity.TimeCard;
import com.tca.exception.LeaveNotFoundException;
//import com.tca.repository.DateRepository;
import com.tca.repository.LeaveRepository;
import com.tca.repository.TimeCardRepository;


@Service

public class LeaveServiceImpl implements LeaveService{
	//@Autowired
	//LeaveRepository leave;
@Autowired
LeaveRepository leaveRep;
	//Leave lea;
	//Employee emp;
	//DateId date;

	public Leave addLeave(Leave lea) {
		//Employee emp=new Employee();
		//lea=new Leave();
		//lea.setDateId(date);
		//lea.setEmployee(date.getemployeeId());
		lea.setStatus("Pending");
		return leaveRep.save(lea);
		
	}

	@Override
	public int removeLeave(int leaveId) {
		Optional<Leave> toDelete= leaveRep.findById(leaveId);
		//Leave lea=null;
		if(toDelete.isPresent()) {
		//	lea=toDelete.get();
		
			leaveRep.delete(toDelete.get());	
			
		}
		return leaveRep.findById(leaveId).isEmpty()?-1:leaveId;
	//	return true;
	}
	
	
	@Override
	public int updateLeave(int leaveId,Leave lea) {
		
		Optional<Leave> leave=leaveRep.findById(leaveId);
		Leave toEdit=(!leave.isEmpty())?leave.get():null;
		
		//if(toEdit==null) {
			toEdit=new Leave();
			toEdit.setLeaveId(lea.getLeaveId());
			toEdit.setDateId(lea.getDateId());
		
		toEdit.setEmployee(lea.getEmployee());
		DateId date=new DateId();
        date.setfromDate(LocalDate.now());
        date.settoDate(LocalDate.now());
		toEdit.setDateId(lea.getDateId());
	
	
		toEdit.setDateId(lea.getDateId());
		
		leaveRep.save(toEdit);
		return toEdit.getLeaveId();
	}
	@Override
	public List<Leave> findByEmpId(int empId) {
		
		return leaveRep.findByEmpId(empId);
		
	}

}
