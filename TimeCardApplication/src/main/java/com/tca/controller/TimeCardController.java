package com.tca.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tca.entity.Employee;
import com.tca.entity.TimeCard;
import com.tca.service.TimeCardService;

@RestController
@RequestMapping("/timecard")
public class TimeCardController {

	@Autowired
	private TimeCardService tcs;
	
	@GetMapping("/employees/{id}")
	public ResponseEntity<List<TimeCard>> getEmployeeById(@PathVariable(value = "id") int employeeId){
		List<TimeCard> timecard = tcs.displayEntries(employeeId);
		return ResponseEntity.ok().body(timecard);
	}
	
	@PostMapping("/employee")
	public ResponseEntity<TimeCard> createEmployee( @RequestBody Employee employee) {
		TimeCard tc=new TimeCard();
		tc.setEmployee(employee);
		tc.setStatus("Pending");
		tc.setTimeEntry(LocalTime.MIN);
		tc.setTimeExit(LocalTime.MAX);
		tc.setDate(LocalDate.now());
		System.out.println("Controller executing");
		return ResponseEntity.ok().body(tcs.saveTimeEntry(tc));
	}
	
	
	
	
}
