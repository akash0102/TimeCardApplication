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
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="DATEID")
public class DateId implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="DATE_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int dateNum;
	@Column(name="FROM_DATE")
	private LocalDate fromDate;
	@Column(name="TO_DATE")
	private LocalDate toDate;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="EMP_ID")
	private Employee employee;
	
	public int getDateNum() {
		return dateNum;
	}
	public void setDateNum(int dateNum) {
		this.dateNum = dateNum;
	}
	public LocalDate getfromDate() {
		return this.fromDate;
	}
	public void setfromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}
	public LocalDate gettoDate() {
		return toDate;
	}
	public void settoDate(LocalDate toDate) {
		this.toDate = toDate;
	}
	public Employee getemployeeId() {
		return this.employee;
	}
	public void setemployeeId(Employee employeeId) {
		this.employee = employeeId;
	}
	@Override
	public String toString() {
		return "DateId [dateNum=" + dateNum + ", fromDate=" + fromDate + ", toDate=" + toDate + ", employee=" + employee
				+ "]";
	}
	
}