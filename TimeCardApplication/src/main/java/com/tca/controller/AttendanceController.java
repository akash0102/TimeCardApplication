package com.tca.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tca.entity.Attendance;
import com.tca.entity.Employee;
import com.tca.exception.ResourceNotFoundException;

import com.tca.service.AttendanceServiceImpl;



@RestController
@RequestMapping("/api/v2")
public class AttendanceController {
	@Autowired
	private AttendanceServiceImpl attendanceService;

	@GetMapping("/getAllAttendance")
	public List<Attendance> getAllAttendance() {
		return attendanceService.getAllAttendance();
	}

	@PostMapping("/saveAttendance")
	public ResponseEntity<Attendance> saveAttendance(@RequestBody Employee employee) {
		Attendance atts=new Attendance();
		atts.setEmployee(employee);
		atts.setFromDate(LocalDate.MIN);
		atts.setToDate(LocalDate.now());
		atts.setInTime(LocalTime.MIN);
		atts.setOffTime(LocalTime.MAX);
		atts.setStatus("Pending");
		return ResponseEntity.ok().body(attendanceService.saveAttendanceDetails(atts));
	}

	@GetMapping("/getAttendance/{id}")
	public ResponseEntity<List<Attendance>> getAttendanceById(@PathVariable(value = "id") Integer employeeId)
			throws ResourceNotFoundException {
		List<Attendance> atts = attendanceService.getAttendanceDetailsById(employeeId);
		return ResponseEntity.ok().body(atts);
	}

	@PutMapping("/updateAttendance/{id}")
	public ResponseEntity<Attendance> updateAttendanceById(@PathVariable(value = "id") Integer attendanceId,
			@RequestBody Attendance att) throws ResourceNotFoundException {
		Attendance atts = attendanceService.updateAttendanceById(attendanceId, att);
		return ResponseEntity.ok(atts);
	}

	@DeleteMapping("/deleteAttendance/{id}")
	public ResponseEntity<Boolean> deleteUsers(@PathVariable(value = "id") Integer attendanceId,
			@RequestBody Attendance att) throws ResourceNotFoundException {
		Boolean att1 = attendanceService.deleteAttendanceDetailsById(attendanceId);
		return ResponseEntity.ok(att1);
	}

}