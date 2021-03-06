package com.tca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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

@CrossOrigin(origins = "http://localhost:3000")
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
	public List<TimeCard> getEmployeeById(@PathVariable(value = "id") Integer employeeId){
		return tcs.displayEntries(employeeId);
	}
	
	@ApiOperation(value = "get timecards by employee id", response = Iterable.class, tags = "TimeCardControllerClass")
	@GetMapping("/getTimeCard/{id}")
	public TimeCard getTimeCardById(@PathVariable(value = "id") Integer tcId){
		return tcs.getTimeCard(tcId);
	}
	
	@ApiOperation(value = "enter timecard entry", response = TimeCard.class, tags = "TimeCardControllerClass")
	@PostMapping("/timecardEntry/{emp_id}")
	public TimeCard createTimeCard( @RequestBody TimeCard tca, @PathVariable(value = "emp_id") Integer empId ) throws ResourceNotFoundException {
		Employee employee=empSer.getEmpById(empId);
		if(employee!=null)
			tca.setEmployee(employee);
		tca.setStatus("Pending");
		return tcs.saveTimeEntry(tca); 
	}
	
	@ApiOperation(value = "edit timecard entry", response = Integer.class, tags = "TimeCardControllerClass")
	@PutMapping("/timeCardEdit/{tc_id}")
	public Integer editTimeCard(@PathVariable("tc_id") Integer id,@RequestBody TimeCard tcard) throws ResourceNotFoundException{
		
		return tcs.updateEntries(id, tcard);
	}
	
	
	@ApiOperation(value = "delete timecard entry", response = Boolean.class, tags = "TimeCardControllerClass")
	@DeleteMapping("/timecardDelete/{id}")
	public Boolean deleteTimeCard(@PathVariable("id") Integer id ) throws ResourceNotFoundException{
		return tcs.removeEntry(id);
	}
	
	@ApiOperation(value = "get all timecards", response = Iterable.class, tags = "TimeCardControllerClass")
	@GetMapping("/timecards")
	public List<TimeCard> getAllEntries(){
		List<TimeCard> timecard = tcs.displayAll();
		return timecard;
	}
	
}
