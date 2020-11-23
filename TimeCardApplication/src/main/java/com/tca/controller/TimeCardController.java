package com.tca.controller;

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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;



/**
 * @author akash
 * controller class for timecard
 */

@Api(value = "TimeCardControllerClass",description = "This is class for employee controller")
@RestController 
@RequestMapping("/api/v2/timecard")
public class TimeCardController {

	@Autowired
	private TimeCardService tcs;
	
	@Autowired
	private EmployeeService empSer;
	
	@ApiOperation(value = "get timecards by employee id", response = Iterable.class, tags = "TimeCardControllerClass")
	@GetMapping("/employee/{id}")
	public ResponseEntity<List<TimeCard>> getEmployeeById(@PathVariable(value = "id") Integer employeeId){
		List<TimeCard> timecard = tcs.displayEntries(employeeId);
		return ResponseEntity.ok().body(timecard);
	}
	
	@ApiOperation(value = "enter timecard entry", response = TimeCard.class, tags = "TimeCardControllerClass")
	@PostMapping("/timecardEntry/")
	public ResponseEntity<TimeCard> createTimeCard(
					@RequestBody TimeCard tca ) {
		Employee employee=empSer.getEmpById(tca.getEmployee().getEmployeeId());
		if(employee!=null)
			tca.setEmployee(employee);
		tca.setStatus("Pending"); 
		return ResponseEntity.ok().body(tcs.saveTimeEntry(tca)); 
	}
	
	@ApiOperation(value = "edit timecard entry", response = Integer.class, tags = "TimeCardControllerClass")
	@PutMapping("/timeCardEdit/{tc_id}")
	public ResponseEntity<Integer> editTimeCard(@PathVariable("tc_id") Integer id,@RequestBody TimeCard tcard) throws ResourceNotFoundException{
		
		return ResponseEntity.ok(tcs.updateEntries(id, tcard));
	}
	
	
	@ApiOperation(value = "delete timecard entry", response = Boolean.class, tags = "TimeCardControllerClass")
	@DeleteMapping("/timecardDelete/{id}")
	public ResponseEntity<Boolean> deleteTimeCard(@PathVariable("id") Integer id ) throws ResourceNotFoundException{
		return ResponseEntity.ok(tcs.removeEntry(id));
	}
	
}
