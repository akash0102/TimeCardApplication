package com.tca.service;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tca.repository.AttendanceRepository;
import com.tca.entity.Attendance;
import com.tca.exception.ResourceNotFoundException;

@Service
@Transactional
public class AttendanceServiceImpl implements AttendanceService {
	
	Logger log=Logger.getLogger(getClass());

	@Autowired
	private AttendanceRepository attdetails; 

	@Override
	public List<Attendance> getAllAttendance() {
		log.info("all attendance details");
		return attdetails.findAll();
	}

	@Override
	public List<Attendance> getAttendanceByEmpId(Integer employeeId) throws ResourceNotFoundException {
		log.info("fetched all attendance by an employee with id "+employeeId);
		return attdetails.findByEmpId(employeeId); 
	}

	@Override
	public boolean deleteAttendanceByEmpId(Integer attendanceId) throws ResourceNotFoundException {
		boolean del;
		Attendance att = attdetails.findById(attendanceId).orElseThrow(
				() -> new ResourceNotFoundException("Attendance not found for this id :: " + attendanceId));
		del=att!=null;
		attdetails.deleteId(attendanceId);
		log.info("attendance with id "+attendanceId+" successfully");
		return del;
	}

	@Override
	public Attendance updateAttendanceById(Integer attendanceId, Attendance atts) throws ResourceNotFoundException {
		Attendance att = attdetails.findById(attendanceId).orElseThrow(
				() -> new ResourceNotFoundException("Attendance not found for this id :: " + attendanceId));
		att.setAttendanceId(atts.getAttendanceId());
		att.setFromDate(atts.getFromDate());
		att.setToDate(atts.getToDate());
		att.setInTime(atts.getInTime());
		att.setOffTime(atts.getOffTime());
		att.setStatus(atts.getStatus());
		log.info("attendance updated successfully");
		return attdetails.save(att);
	}

	@Override
	public Attendance saveAttendanceDetails(Attendance att) {
		log.info("attendance saved succesfully");
		if(att.getToDate()==null) {
			att.setToDate(att.getFromDate());
		}
		return attdetails.save(att);
	}
	
	@Override
	public Attendance getAttendanceById(Integer attendanceId) {
		log.info("attendance for id "+attendanceId+" fetched");
		return attdetails.getOne(attendanceId);
	}

}
