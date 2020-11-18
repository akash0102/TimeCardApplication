package com.tca.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tca.entity.Employee;
import com.tca.entity.TimeCard;

@Repository
public interface TimeCardDao extends JpaRepository<TimeCard, Integer>{
	
	@Query("select tc from TimeCard tc where tc.employee=:emp")
    List<TimeCard> findByEmp(Employee emp);
	
	@Query("select tc from TimeCard tc where tc.employee=(select emp from Employee emp where emp.employeeId=:empId)")
	List<TimeCard> findByEmpId(int empId);
}
