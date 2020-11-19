package com.tca.entity;

import java.io.Serializable;
import java.time.LocalDate;
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
@Table(name="TIMECARD")
@NamedQuery(name = "TimeCard.getAllById", query = "SELECT t FROM TimeCard t where t.employee=:emp")
public class TimeCard implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="TIME_CARDID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int timeCardId;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="EMP_ID")
	private Employee employee;
	@Column(name="ENTRY_DATE")
	private LocalDate date;
	@Column(name="ENTRY_TIME")
	private LocalTime timeEntry;
	@Column(name="EXIT_TIME")
	private LocalTime timeExit;
	@Column(name="STATUS")
	private String status;
	
	
	public TimeCard() {
		super();
	}
	public TimeCard(Employee employee, LocalDate date, LocalTime timeEntry, LocalTime timeExit, String status) {
		super();
		this.employee = employee;
		this.date = date;
		this.timeEntry = timeEntry;
		this.timeExit = timeExit;
		this.status = status;
	}
	public int getTimeCardId() {
		return timeCardId;
	}
	public void setTimeCardId(int timeCardId) {
		this.timeCardId = timeCardId;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public LocalTime getTimeEntry() {
		return timeEntry;
	}
	public void setTimeEntry(LocalTime timeEntry) {
		this.timeEntry = timeEntry;
	}
	public LocalTime getTimeExit() {
		return timeExit;
	}
	public void setTimeExit(LocalTime timeExit) {
		this.timeExit = timeExit;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "TimeCard [timeCardId=" + timeCardId + ", employee=" + employee + ", date=" + date + ", timeEntry="
				+ timeEntry + ", timeExit=" + timeExit + ", status=" + status + "]";
	}

	
}
