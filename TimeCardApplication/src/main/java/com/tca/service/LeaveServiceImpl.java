package com.tca.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.tca.dao.LeaveDao;
import com.tca.entity.DateId;
import com.tca.entity.Employee;
import com.tca.entity.Leave;


public class LeaveServiceImpl implements LeaveService {
	LeaveDao daoLeave= new LeaveDao();
	Logger log=Logger.getLogger(LeaveServiceImpl.class);

	@Override
	public boolean addLeave(Employee emp, DateId dateId) {
		return daoLeave.addLeaveEntry(emp,dateId);
	}

	@Override
	public boolean findLeave(int leaveId) { 
		
		return daoLeave.getLeaveInfo(leaveId);
		
	}

	@Override
	public boolean removeLeave(int leaveId) {
		return daoLeave.cancelLeave(leaveId);
	}

	@Override
	public List<Leave> viewAllLeaves(Employee emp) {
		List<Leave> listAllLeave=null;
		try {
			listAllLeave=daoLeave.getAllLeaves(emp);
		}
		catch(NullPointerException e) {
			log.debug(e);
		}
		return listAllLeave;
	}
	

}
