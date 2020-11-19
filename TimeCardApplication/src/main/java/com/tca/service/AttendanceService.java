package com.tca.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.tca.entity.Attendance;
import com.tca.entity.Employee;

public interface AttendanceService {

	boolean manualAttendance(Employee empl, LocalDate fromDate, LocalDate toDate, LocalTime iTime, LocalTime oTime);

	List<Attendance> viewAllDetails(Employee empl);

}
