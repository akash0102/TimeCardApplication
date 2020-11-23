package com.tca.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tca.repository.AttendanceRepository;
import com.tca.entity.Attendance;
import com.tca.exception.ResourceNotFoundException;

@Service
@Transactional
public class AttendanceServiceImpl implements AttendanceService {

	@Autowired
	private AttendanceRepository attdetails;

	@Override
	public List<Attendance> getAllAttendance() {
		return attdetails.findAll();
	}

	@Override
	public List<Attendance> getAttendanceByEmpId(Integer employeeId) throws ResourceNotFoundException {
		
		return attdetails.findByEmpId(employeeId); 
	}

	@Override
	public boolean deleteAttendanceByEmpId(Integer attendanceId) throws ResourceNotFoundException {
		Attendance att = attdetails.findById(attendanceId).orElseThrow(
				() -> new ResourceNotFoundException("Attendance not found for this id :: " + attendanceId));
		attdetails.delete(att);
		return att==null;
	}

	@Override
	public Attendance updateAttendanceById(Integer attendanceId, Attendance atts) throws ResourceNotFoundException {
		Attendance att = attdetails.findById(attendanceId).orElseThrow(
				() -> new ResourceNotFoundException("Attendance not found for this id :: " + attendanceId));
		att.setAttendanceId(atts.getAttendanceId());
		att.setInTime(atts.getInTime());
		att.setOffTime(atts.getOffTime());
		att.setStatus(atts.getStatus());

		return attdetails.save(att);
	}

	@Override
	public Attendance saveAttendanceDetails(Attendance att) {
		return attdetails.save(att); 
	}
	
	@Override
	public Attendance getAttendanceById(Integer attendanceId) {
		return attdetails.getOne(attendanceId);
	}

}
