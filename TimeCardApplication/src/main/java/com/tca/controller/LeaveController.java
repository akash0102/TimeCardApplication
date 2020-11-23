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
import com.tca.entity.Leave;
import com.tca.exception.ResourceNotFoundException;
import com.tca.service.EmployeeService;
import com.tca.service.LeaveService;




/**
 * @author pavan
 * controller class for leave service
 */
@RestController
@RequestMapping("/api/v2/leave")
public class LeaveController {
	@Autowired
	private LeaveService leaveservice;
	
	@Autowired
	private EmployeeService empSer;
	
	@PostMapping("/apply/{emp_id}")
	public Leave addLeave(@RequestBody Leave leave ,@PathVariable("emp_id") Integer empId ) {
		Employee emp=empSer.getEmpById(empId);
		if(emp!=null) {
			leave.setEmployee(emp); 
		}
		return leaveservice.addLeave(leave);
	}

	@GetMapping("/findLeaveById/{leaveId}")
	public Leave findLeave(@PathVariable Integer leaveId) throws ResourceNotFoundException{
		return leaveservice.findLeave(leaveId); 
	}
	 
	@DeleteMapping("/deleteLeaveById/leaveId/{leaveId}")
	public int removeLeave(@PathVariable Integer leaveId){
		return leaveservice.removeLeave(leaveId); 
	}
	
	@PutMapping("/updateLeave/{leaveId}")
	public int updateLeave(@RequestBody Leave leave ,@PathVariable("leaveId") Integer leaveId ) throws ResourceNotFoundException {
		return leaveservice.updateLeave(leaveId,leave);
	}
	
	@GetMapping("/getAllLeaves/{emp_id}")
	public ResponseEntity<List<Leave>> getAllLeaves(@PathVariable("emp_id") Integer empId) {
		return ResponseEntity.ok(leaveservice.findByEmpId(empId));
	}

}
