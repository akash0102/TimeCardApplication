package com.tca.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="EMPLOYEE")
@SequenceGenerator(name="seq", initialValue=100000, allocationSize=100)
public class Employee implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
	@Column(name="EMP_ID")
	private int employeeId;
	@Column(name="EMP_NAME")
	private String employeeName;
	@Column(name="EMP_ROLE")
	private String employeeRole;
	@Column(name="EMP_PHNO")
	private String phoneNumber;
	@Column(name="EMP_EMAIL")
	private String employeeEmail;
	@Column(name="PASSWORD")
	private String pass;
	@Column(name="USER_ID")
	private String userId;
	@ManyToOne
	@JoinColumn(name="MAN_ID")
	private Manager manager;
	
	
	public int getEmployeeId() {
		return this.employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return this.employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmployeeRole() {
		return this.employeeRole;
	}
	public void setEmployeeRole(String employeeRole) {
		this.employeeRole = employeeRole;
	}
		public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmployeeEmail() {
		return employeeEmail;
	}
	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}
	
	public Manager getManager() {
		return manager;
	}
	public void setManager(Manager manager) {
		this.manager = manager;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((employeeEmail == null) ? 0 : employeeEmail.hashCode());
		result = prime * result + employeeId;
		result = prime * result + ((employeeName == null) ? 0 : employeeName.hashCode());
		result = prime * result + ((employeeRole == null) ? 0 : employeeRole.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || (getClass() != obj.getClass()))
			return false;
		Employee other = (Employee) obj;
		return (this.employeeEmail.equals(other.employeeEmail) && 
			   (this.employeeId == other.employeeId) && 
			   (this.employeeName.equals(other.employeeName)) && 
			   (this.employeeRole.equals(other.employeeRole)) && 
			   (this.phoneNumber.equals(other.phoneNumber)));
	}
	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", employeeName=" + employeeName + ", employeeRole="
				+ employeeRole + ", phoneNumber=" + phoneNumber + ", employeeEmail=" + employeeEmail + "]";
	}
	public Employee(int employeeId, String employeeName, String phoneNumber, String employeeEmail, Manager manager) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.phoneNumber = phoneNumber;
		this.employeeEmail = employeeEmail;
		this.manager = manager;
	}
	public Employee() {
		super();
	}
	
	
	
}
