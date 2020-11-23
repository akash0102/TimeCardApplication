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
import com.tca.service.EmployeeService;




/**
 * @author akash
 * controller layer for attendance
 */
@RestController
@RequestMapping("/api/v2")
public class AttendanceController {
	@Autowired
	private AttendanceServiceImpl attendanceService;
	@Autowired
	private EmployeeService empSer;

	@GetMapping("/getAllAttendance")
	public List<Attendance> getAllAttendance() {
		return attendanceService.getAllAttendance(); 
	}

	@PostMapping("/saveAttendance/{emp_id}")
	public ResponseEntity<Attendance> saveAttendance(@PathVariable("emp_id") Integer empId,@RequestBody String date,
												@RequestBody String intime, @RequestBody String outtime) {
		Attendance atts=new Attendance();
		Employee emp=empSer.getEmpById(empId);
		atts.setEmployee(emp);
		atts.setFromDate(LocalDate.parse(date));
		atts.setToDate(LocalDate.parse(date));
		atts.setInTime(LocalTime.parse(intime));
		atts.setOffTime(LocalTime.parse(outtime));
		atts.setStatus("Pending");
		Attendance resultAttendance=attendanceService.saveAttendanceDetails(atts);
		if(resultAttendance==null) {
			atts.setStatus("EmployeeNotFound");
		}
		else {
			atts=resultAttendance;
		}
		return ResponseEntity.ok().body(atts);
	}

	@GetMapping("/getAttendance/{id}")
	public ResponseEntity<List<Attendance>> getAttendanceById(@PathVariable(value = "id") Integer employeeId)
					throws ResourceNotFoundException {
		List<Attendance> atts = attendanceService.getAttendanceByEmpId(employeeId);
		return ResponseEntity.ok().body(atts);
	}

	@PutMapping("/updateAttendance/{id}")
	public ResponseEntity<Attendance> updateAttendanceById(@PathVariable(value = "id") Integer attendanceId,
				@RequestBody String fromDate,@RequestBody String toDate,@RequestBody String inTime,
				@RequestBody String outTime) throws ResourceNotFoundException {
		Attendance atts=attendanceService.getAttendanceById(attendanceId);
		atts.setFromDate(LocalDate.parse(fromDate));
		atts.setToDate(LocalDate.parse(toDate));
		atts.setInTime(LocalTime.parse(inTime));
		atts.setOffTime(LocalTime.parse(outTime));
		return ResponseEntity.ok(attendanceService.updateAttendanceById(attendanceId, atts));
	}

	@DeleteMapping("/deleteAttendance/{id}")
	public ResponseEntity<Boolean> deleteUsers(@PathVariable(value = "id") Integer attendanceId)
					throws ResourceNotFoundException {
		Boolean att1 = attendanceService.deleteAttendanceByEmpId(attendanceId);
		return ResponseEntity.ok(att1);
	}

}