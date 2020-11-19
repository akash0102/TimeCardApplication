package com.tca.repository;
//import java.time.LocalDateTime;
//import java.util.List;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tca.entity.Employee;
//import com.tca.entity.DateId;
//import com.tca.entity.Employee;
import com.tca.entity.Leave;
import com.tca.entity.TimeCard;
@Repository
public interface LeaveRepository extends JpaRepository<Leave, Integer>{
	@Query("select l from Leave l where l.employee=:emp")
    List<Leave> findByEmpId(Employee emp);
	@Query("select l from Leave l where l.employee=(select emp from Employee emp where emp.employeeId=:empId)")
	List<TimeCard> findByEmpId(int empId);
	//public boolean addLeave(Employee emp, DateId dateId);

	/*public boolean findLeave(int leaveId);

	public boolean removeLeave(int leaveId);

	List<Leave> viewAllLeaves(Employee emp);*/

}
