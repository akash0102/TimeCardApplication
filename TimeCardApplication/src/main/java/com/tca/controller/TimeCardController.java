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
import com.tca.service.EmployeeService;
import com.tca.service.TimeCardService;

@RestController
@RequestMapping("/api/v2/timecard")
public class TimeCardController {

	@Autowired
	private TimeCardService tcs;
	
	@Autowired
	private EmployeeService empSer;
	
	@GetMapping("/employee/{id}")
	public ResponseEntity<List<TimeCard>> getEmployeeById(@PathVariable(value = "id") Integer employeeId){
		List<TimeCard> timecard = tcs.displayEntries(employeeId);
		return ResponseEntity.ok().body(timecard);
	}
	
	
	@PostMapping("/timecardEntry/")
	public ResponseEntity<TimeCard> createTimeCard( @RequestBody Integer employeeId,String date,String fromTime,String toTime) {
		TimeCard tc=new TimeCard();
		Employee employee=empSer.getEmpById(employeeId);
		if(employee!=null)
			tc.setEmployee(employee);
		tc.setStatus("Pending");
		tc.setTimeEntry(LocalTime.parse(fromTime));
		tc.setTimeExit(LocalTime.parse(toTime)); 
		tc.setDate(LocalDate.parse(date));
		return ResponseEntity.ok().body(tcs.saveTimeEntry(tc));
	}
	
	
	@PutMapping("/timeCardEdit/{id}")
	public ResponseEntity<Integer> editTimeCard(@PathVariable("tc_id") Integer id,@RequestBody String date,
									@RequestBody String inTime,@RequestBody String outTime) throws ResourceNotFoundException{
		
		return ResponseEntity.ok(tcs.updateEntries(id, LocalDate.parse(date), LocalTime.parse(inTime), LocalTime.parse(outTime)));
	}
	
	@DeleteMapping("/timecardDelete/{id}")
	public ResponseEntity<Boolean> deleteTimeCard(@PathVariable("tc_id") Integer id ) throws ResourceNotFoundException{
		return ResponseEntity.ok(tcs.removeEntry(id));
	}
	
}
