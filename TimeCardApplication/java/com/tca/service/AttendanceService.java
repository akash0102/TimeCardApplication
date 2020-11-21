package com.tca.service;

import java.time.LocalTime;
import java.util.List;

import com.tca.entity.Attendance;
import com.tca.entity.DateId;
import com.tca.entity.Employee;

public interface AttendanceService {

	boolean manualAttendance(Employee empl, DateId dateId, LocalTime iTime, LocalTime oTime);

	List<Attendance> viewAllDetails(Employee empl);

}
