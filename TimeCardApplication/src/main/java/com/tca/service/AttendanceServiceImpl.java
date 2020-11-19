package com.tca.service;

import java.time.LocalTime;
import java.util.List;

import org.apache.log4j.Logger;

import com.tca.entity.Attendance;
import com.tca.entity.DateId;
import com.tca.entity.Employee;
import com.tca.repository.AttendanceRepository;

public class AttendanceServiceImpl implements AttendanceService {

	AttendanceRepository attDao=new AttendanceRepository();
	Logger log=Logger.getLogger(AttendanceServiceImpl.class); 
	
	@Override
	public boolean manualAttendance(Employee empl, DateId dateId, LocalTime iTime, LocalTime oTime) {
		return attDao.editAttendance(empl, dateId, iTime, oTime);
	}
	
	@Override
	public List<Attendance> viewAllDetails(Employee empl) {
		List<Attendance> listAttendance=null;
		try {
		 listAttendance=attDao.getAlldetails(empl);
		} 
		catch(Exception e) {
			log.debug(e);
		}
		return listAttendance;
	}

}
