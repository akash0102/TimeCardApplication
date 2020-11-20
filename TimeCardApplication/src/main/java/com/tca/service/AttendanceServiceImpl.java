package com.tca.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.apache.log4j.Logger;

import com.tca.entity.Attendance;
import com.tca.entity.Employee;
import com.tca.repository.AttendanceRepository;

public class AttendanceServiceImpl implements AttendanceService {

	AttendanceRepository attDao=new AttendanceRepository();
	Logger log=Logger.getLogger(AttendanceServiceImpl.class); 
	
	@Override
	public boolean manualAttendance(Employee empl, LocalDate toDate, LocalDate fromDate, LocalTime iTime, LocalTime oTime) {
		return false;
	}
	
	@Override
	public List<Attendance> viewAllDetails(Employee empl) {
		return null;
	}

}
