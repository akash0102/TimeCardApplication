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

import com.tca.entity.DateId;
import com.tca.entity.Employee;
import com.tca.entity.Leave;
import com.tca.entity.Manager;
import com.tca.entity.TimeCard;
import com.tca.service.LeaveService;




@RestController
@RequestMapping("/leave")
public class LeaveController {
	@Autowired
	 //@org.springframework.beans.factory.annotation.Autowired(required=true)
	private LeaveService leaveservice;
	@PostMapping("/apply")
	public Leave addLeave(@RequestBody Leave lea) {
		
		//date.setDateNum(2);
		//date.setfromDate(null);
		//date.settoDate(null);
		return leaveservice.addLeave(lea);
	}
	@PutMapping("/leave/leaveEdit/{leaveId}")
	public ResponseEntity<Integer> editLeave(@PathVariable("leaveId") Integer leaveId,@RequestBody Leave lea){
		return ResponseEntity.ok(leaveservice.updateLeave(leaveId, lea));		
	}
	 @DeleteMapping("/deleteLeaveById/leaveId/{leaveId}")
	    public void removeLeave(@PathVariable Integer leaveId){
	        leaveservice.removeLeave(leaveId);
	    }
	  @GetMapping("/viewAllLeaves/{empId}")
	    public List<Leave> findByEmpId(int empId){
	        return (List<Leave>) leaveservice.findByEmpId(empId);
	    }

}
