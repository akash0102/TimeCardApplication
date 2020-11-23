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

import com.tca.entity.Employee;
import com.tca.entity.TimeCard;
import com.tca.exception.ResourceNotFoundException;
import com.tca.service.TimeCardService;

@RestController
@RequestMapping("/api/v2/timecard")
public class TimeCardController {

	@Autowired
	private TimeCardService tcs;
	
	@GetMapping("/employee/{id}")
	public ResponseEntity<List<TimeCard>> getEmployeeById(@PathVariable(value = "id") int employeeId){
		List<TimeCard> timecard = tcs.displayEntries(employeeId);
		return ResponseEntity.ok().body(timecard);
	}
	
	
	@PostMapping("/addEmployee/")
	public ResponseEntity<TimeCard> createTimeCard( @RequestBody Employee employee,LocalDate date,LocalTime fromTime,LocalTime toTime) {
		TimeCard tc=new TimeCard();
		tc.setEmployee(employee);
		tc.setStatus("Pending");
		tc.setTimeEntry(fromTime);
		tc.setTimeExit(toTime);
		tc.setDate(date);
		return ResponseEntity.ok().body(tcs.saveTimeEntry(tc));
	}
	
	
	@PutMapping("/timeCardEdit/{id}")
	public ResponseEntity<Integer> editTimeCard(@PathVariable("tc_id") Integer id,@RequestBody TimeCard tc) throws ResourceNotFoundException{
		return ResponseEntity.ok(tcs.updateEntries(id, tc));		
	}
	
	@DeleteMapping("/timecardDelete/{id}")
	public ResponseEntity<Boolean> deleteTimeCard(@PathVariable("tc_id") Integer id ) throws ResourceNotFoundException{
		return ResponseEntity.ok(tcs.removeEntry(id));
	}
	
}
