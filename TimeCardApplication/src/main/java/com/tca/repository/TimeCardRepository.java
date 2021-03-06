package com.tca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tca.entity.Employee;
import com.tca.entity.TimeCard;

@Repository
public interface TimeCardRepository extends JpaRepository<TimeCard, Integer>{
	
	@Query("select tc from TimeCard tc where tc.employee=:emp")
    List<TimeCard> findByEmp(Employee emp);
	
	@Query("select tc from TimeCard tc where tc.employee=(select emp from Employee emp where emp.employeeId=:empId)")
	List<TimeCard> findByEmpId(int empId);
	
	@Transactional
	@Modifying
	@Query("delete from TimeCard tt where tt.timeCardId=:id")
	void deleteId(int id);
}
