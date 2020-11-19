package com.tca.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

//import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.tca.dao.DateRepository;
import com.tca.dao.LeaveDao;
import com.tca.dao.TimeCardDao;
import com.tca.entity.DateId;
import com.tca.entity.Employee;
import com.tca.entity.Leave;
import com.tca.entity.TimeCard;
import com.tca.exception.LeaveNotFoundException;


@Service

public class LeaveServiceImpl implements LeaveService{
	@Autowired
	LeaveDao leave;
@Autowired
DateRepository daterep;
	Leave lea;
	//Employee emp;
	//DateId date;

	public Leave addLeave(DateId date) {
		//Employee emp=new Employee();
		lea=new Leave();
		lea.setDateId(date);
		lea.setEmployee(date.getemployeeId());
		lea.setStatus("Pending");
		daterep.save(date);
		return leave.save(lea);
		//return true;

		//lea.setEmployee(dateId.getemployeeId());
		//lea.setDateId(dateId);
		//lea.setStatus("Pending");
		//leave.save(lea);
		//return true;
	
		/*emp.setEmployeeId(1);
		emp.setEmployeeName("pavan");
		emp.setEmployeeRole("analyst");
		emp.setEmployeeEmail("dfdffs");
		leave.save(emp);
		return true;*/
	}

	@Override
	public boolean removeLeave(int leaveId) {
		Optional<Leave> toDelete= leave.findById(leaveId);
		if(toDelete.isPresent()) {
			leave.delete(toDelete.get());	
			
		}
		return true;
	}
	
	
	@Override
	public Leave findLeave(int leaveId) { 
		
		
		if (!leave.existsById(leaveId)) {
			throw new LeaveNotFoundException("Leave with id " + leaveId + "not found");
		}
		return leave.getOne(leaveId);
		
	}
	@Override
	public List<Leave> findByEmpId(Employee emp) {
		
		return leave.findByEmpId(emp);
		
	}

}
