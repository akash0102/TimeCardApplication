package com.tca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tca.entity.Employee;
import com.tca.entity.Leave;

@Repository
public interface LeaveRepository extends JpaRepository<Leave, Integer>{
	@Query("select l from Leave l where l.employee=:emp")
    List<Leave> findByEmpId(Employee emp);
	@Query("select l from Leave l where l.employee=(select emp from Employee emp where emp.employeeId=:empId)")
	List<Leave> findByEmpId(int empId);
	@Transactional
	@Modifying
	@Query("delete from Leave lea where lea.leaveId=:id")
	void deleteId(int id);
}
