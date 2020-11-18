package com.tca.dao;

import java.util.List;

import javax.persistence.EntityTransaction;

import org.apache.log4j.Logger;

import com.tca.entity.DateId;
import com.tca.entity.Employee;
import com.tca.entity.Leave;
import com.tca.util.JPaUtil;

public class LeaveDao {
	Logger log=Logger.getLogger(getClass());
	public boolean addLeaveEntry(Employee emp, DateId dateId) {
		
		boolean check=false;
		JPaUtil.createEM();
		try {
			EntityTransaction tras=JPaUtil.entMan.getTransaction();
			tras.begin();
			Leave leave=new Leave();
			leave.setDateId(dateId);
			leave.setEmployee(emp);
			JPaUtil.entMan.merge(leave);
			tras.commit();
			check=true;
		}
		catch(Exception e) {
			log.debug(e);
		}
		JPaUtil.closeEM();
		return check;
	}

	public boolean getLeaveInfo(int leaveId) {
		JPaUtil.createEM();
		boolean chk=false;
		Leave leave=null;
		try {
			leave = JPaUtil.entMan.find(Leave.class, leaveId); //find a record based on the id
			
			if(leave != null){
				chk=true;
			}
			else {
				log.debug(leave);
			}
		} 
		catch (Exception e) {
			log.warn(e);
		}
		JPaUtil.closeEM();
		return chk;
	}

	public boolean cancelLeave(int leaveId) {
		boolean remove=false;
		JPaUtil.createEM();
		Leave leave = JPaUtil.entMan.find(Leave.class, leaveId);
		
		try {
			JPaUtil.entMan.getTransaction().begin();
			JPaUtil.entMan.remove(leave);   //delete 
			JPaUtil.entMan.getTransaction().commit();
			remove=true;
		} 
		catch (Exception e) {
			JPaUtil.entMan.getTransaction().rollback();
			log.debug(e);
		}
		
		JPaUtil.closeEM();
		return remove;
		
	}

	public List<Leave> getAllLeaves(Employee emp) {
		JPaUtil.createEM();		                              
		List<Leave> leaves = JPaUtil.entMan.createQuery("select c from Leave c where c.employee=?1", Leave.class)
									.setParameter(1, emp)
									.getResultList();  		
		log.info(leaves);
		JPaUtil.closeEM();
		return leaves;
	}

}
