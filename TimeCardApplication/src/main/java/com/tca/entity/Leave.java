package com.tca.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="LEAVE")
public class Leave implements Serializable{
    
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id 
	@Column(name="LEAVE_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int leaveId;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="EMP_ID")
	private Employee employee;
	@Column(name="STATUS")
	private String status;
	@Column(name="FROM_DATE")
	private LocalDate fromDate;	
	@Column(name="TO_DATE")
	private LocalDate toDate;
	
	
	public Leave() {
		super();
	} 
	public Leave(LocalDate fromDate, LocalDate toDate) {
		this.fromDate=fromDate;
		this.toDate=toDate;
		this.status="Pending";
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
	public LocalDate getFromDate() {
		return this.fromDate;
	}
	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}
	public LocalDate getToDate() {
		return toDate; 
	}
	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}
	@Override
	public String toString() {
		return "Leave [leaveId=" + leaveId + ", employee=" + employee + ", status=" + status + ", fromDate=" + fromDate
				+ ", toDate=" + toDate + "]";
	}
	

	
}
 