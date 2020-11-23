package com.tca.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tca.entity.Leave;
import com.tca.exception.ResourceNotFoundException;
import com.tca.service.LeaveService;




@RestController
@RequestMapping("/api/v2/leave")
public class LeaveController {
	@Autowired
	private LeaveService leaveservice;
	@PostMapping("/apply")
	public Leave addLeave(@RequestBody Leave leave ) {
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
	public int updateLeave(@PathVariable int leaveId, @RequestBody LocalDate fromDate,@RequestBody LocalDate toDate) throws ResourceNotFoundException {
		return leaveservice.updateLeave(leaveId, fromDate, toDate);
	}

}
