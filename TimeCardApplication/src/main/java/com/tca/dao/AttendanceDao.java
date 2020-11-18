package com.tca.dao;

import java.time.LocalTime;
import java.util.List;

import org.apache.log4j.Logger;

import com.tca.entity.Attendance;
import com.tca.entity.DateId;
import com.tca.entity.Employee;
import com.tca.util.JPaUtil;


public class AttendanceDao {
 
	Logger log=Logger.getLogger(getClass());
	
	public boolean editAttendance(Employee empl, DateId dateId, LocalTime iTime, LocalTime oTime) {
		JPaUtil.createEM();
		boolean checker=false;
		try {
			Attendance att=new Attendance();
			JPaUtil.entMan.getTransaction().begin();
			att.setDateId(dateId);
			att.setEmployee(empl);
			att.setInTime(iTime); 
			att.setOffTime(oTime);
			att.setEmployee(empl);
			JPaUtil.entMan.merge(att);
			JPaUtil.entMan.getTransaction().commit();
			checker=true;
		}
		catch(NullPointerException e) {
			log.debug(e);
		}
		JPaUtil.closeEM();
		return checker;
	}

	public List<Attendance> getAlldetails(Employee empl) {
		JPaUtil.createEM();
		List<Attendance> entries=null;
		entries = JPaUtil.entMan
				 .createNamedQuery("Attendance.getAllById", Attendance.class)
				 .setParameter("emp", empl)
				 .getResultList();
		JPaUtil.closeEM();
		return entries;
	}

}
