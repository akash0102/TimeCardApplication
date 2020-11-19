package com.tca.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="LEAVE")
@NamedQuery(name = "Leave.getAllById", query = "SELECT l FROM Leave l where l.employee=:emp")
public class Leave implements Serializable{
    
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id 
	 @Column(name="LEAVE_ID")
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	 private int leaveId;
	 @Column(name="EMP_ID")
	 private Employee employee;
	 @OneToOne(cascade=CascadeType.ALL)
	 @JoinColumn(name="DATE_ID")
	 private DateId dateId;
	 @Column(name="STATUS")
	 private String status;
	    
	public DateId getDateId() { 
		return dateId;
	}
	public void setDateId(DateId dateId) {
		this.dateId = dateId;
	}
	public int getLeaveId() {
		return leaveId;
	}
	public void setLeaveId(int leaveId) {
		this.leaveId = leaveId;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Leave [leaveId=" + leaveId + ", employee=" + employee + ", dateId=" + dateId + ", status=" + status
				+ "]";
	}


	
}
 