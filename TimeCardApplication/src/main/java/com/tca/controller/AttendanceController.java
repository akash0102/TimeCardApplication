package com.tca.controller;

import java.util.List;

import org.apache.log4j.Logger;
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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;




/**
 * @author dinesh
 * controller layer for attendance
 */
@RestController
@RequestMapping("/api/v2")
@Api(value = "AttendanceControllerClass",description = "This is class for attendance controller")
public class AttendanceController {
	
	Logger log=Logger.getLogger(getClass());
	@Autowired
	private AttendanceServiceImpl attendanceService;
	@Autowired
	private EmployeeService empSer;
	
	@ApiOperation(value = "Get list of attendance in the System ", response = Iterable.class, tags = "AttendanceControllerClass")
	@GetMapping("/getAllAttendance")
	public List<Attendance> getAllAttendance() {
		log.info("returned List");
		return attendanceService.getAllAttendance(); 
	}
	
	@ApiOperation(value = "saves attendance to the database", response = Attendance.class, tags = "AttendanceControllerClass")
	@PostMapping("/saveAttendance/{emp_id}")
	public ResponseEntity<Attendance> saveAttendance(@PathVariable("emp_id") Integer empId,@RequestBody Attendance att) throws ResourceNotFoundException {
		
		Employee emp=empSer.getEmpById(empId);
		att.setEmployee(emp);
		att.setStatus("Pending");
		Attendance resultAttendance=attendanceService.saveAttendanceDetails(att);
		if(resultAttendance==null) {
			att.setStatus("EmployeeNotFound");
			log.warn(resultAttendance+" null");
		}
		else {
			att=resultAttendance;
		}
		log.info("return attendance");
		return ResponseEntity.ok().body(att);
	}

	@ApiOperation(value = " gets attendance by employee id ", response = Iterable.class, tags = "AttendanceControllerClass")
	@GetMapping("/getAttendance/{id}")
	public ResponseEntity<List<Attendance>> getAttendanceById(@PathVariable(value = "id") Integer employeeId)
					throws ResourceNotFoundException {
		List<Attendance> atts = attendanceService.getAttendanceByEmpId(employeeId);
		log.info("leave based on employee id fetched");
		return ResponseEntity.ok().body(atts);
	}

	@ApiOperation(value = "update attendance ", response = Attendance.class, tags = "AttendanceControllerClass")
	@PutMapping("/updateAttendance/{id}") 
	public ResponseEntity<Attendance> updateAttendanceById(@PathVariable(value = "id") Integer attendanceId,@RequestBody Attendance att) throws ResourceNotFoundException {
		log.info("attendance updated");
		return ResponseEntity.ok(attendanceService.updateAttendanceById(attendanceId, att));
	}

	@ApiOperation(value = "deleting attendance ", response = Boolean.class, tags = "AttendanceControllerClass")
	@DeleteMapping("/deleteAttendance/{id}")
	public ResponseEntity<Boolean> deleteAttendance(@PathVariable(value = "id") Integer attendanceId)
					throws ResourceNotFoundException {
		Boolean att1 = attendanceService.deleteAttendanceByEmpId(attendanceId);
		log.info("attendance deleted");
		return ResponseEntity.ok(att1);
	}

}