package com.tca.service;

import java.util.List;

import com.tca.entity.Attendance;
import com.tca.exception.ResourceNotFoundException;

public interface AttendanceService {
	
	 public List<Attendance> getAllAttendance();
	 public List<Attendance> getAttendanceByEmpId(Integer employeeId) throws ResourceNotFoundException;
	 public boolean deleteAttendanceByEmpId(Integer attendanceId) throws ResourceNotFoundException;
	 public Attendance updateAttendanceById(Integer attendanceId, Attendance att) throws ResourceNotFoundException;
	 public Attendance saveAttendanceDetails(Attendance att);
	Attendance getAttendanceById(Integer attendanceId);
	 
}