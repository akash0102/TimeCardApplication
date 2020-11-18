package com.tca.entity;


import java.io.Serializable;
import java.time.LocalTime;

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
@Table(name="ATTENDANCE")
@NamedQuery(name = "Attendance.getAllById", query = "SELECT t FROM Attendance t where t.employee=:emp")
public class Attendance implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ATT_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private int attendanceId;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="EMP_ID")
	private Employee employee; 
	
	@Column(name="IN_TIME")
	private LocalTime inTime;
	
	@Column(name="OFF_TIME")
	private LocalTime offTime;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="DATE_ID")
	private DateId dateId;
	
	@Column(name="STATUS")
	private String status;
	
	public int getAttendanceId() {
		return this.attendanceId;
	}
	public void setAttendanceId(int attendanceId) {
		this.attendanceId = attendanceId;
	}
	public LocalTime getInTime() {
		return this.inTime;
	}
	public void setInTime(LocalTime inTime) {
		this.inTime = inTime;
	}
	public LocalTime getOffTime() {
		return this.offTime;
	}
	public void setOffTime(LocalTime offTime) {
		this.offTime = offTime;
	}
	public DateId getDateId() {
		return this.dateId;
	}
	public void setDateId(DateId dateId) {
		this.dateId = dateId;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	} //testtt
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Attendance [attendanceId=" + attendanceId + ", employee=" + employee + ", inTime=" + inTime
				+ ", offTime=" + offTime + ", dateId=" + dateId + ", status=" + status + "]";
	}
	
	
}