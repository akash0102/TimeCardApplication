package com.tca.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.tca.entity.Attendance;
import com.tca.entity.DateId;
import com.tca.entity.Employee;
import com.tca.entity.Leave;
import com.tca.entity.Manager;
import com.tca.entity.TimeCard;
import com.tca.service.ManagerServiceImpl;

public class Trial {

	public static void main(String[] args) {
		Employee emp=new Employee();
		Leave l=new Leave();
		TimeCard tc=new TimeCard();
		Attendance att=new Attendance();
		ManagerServiceImpl msi=new ManagerServiceImpl();
		emp.setEmployeeId(100);
		emp.setEmployeeEmail("test@abc.com");
		emp.setEmployeeName("abcde");
		emp.setEmployeeRole("dev");
		DateId did=new DateId();
		did.setfromDate(LocalDate.MIN);
		did.settoDate(LocalDate.MAX);
		l.setDateId(did);
		tc.setDate(LocalDate.now());
		tc.setEmployee(emp);
		tc.setTimeEntry(LocalTime.MIN);
		tc.setTimeExit(LocalTime.NOON);
		att.setDateId(did);
		att.setEmployee(emp);
		att.setInTime(LocalTime.MIN);
		att.setOffTime(LocalTime.MAX);
		
		msi.resolveRequest();
		
		msi.resolveRequest();
		
		List<Object> requests=msi.viewRequests();
		for(int i=0;i<requests.size();i++) {
			System.out.println(requests.get(i) instanceof Leave);
			System.out.println(requests.get(i) instanceof TimeCard);
			System.out.println(requests.get(i) instanceof Attendance);
			System.out.println(requests.get(i) instanceof Manager);
			
		}
		

	}

}
