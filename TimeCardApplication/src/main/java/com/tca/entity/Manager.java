package com.tca.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="MANAGER")
public class Manager implements Serializable{
	
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="MAN_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int managerId;
	@OneToMany(mappedBy = "manager",cascade = CascadeType.ALL)
	@Column(name="EMP_ID")
	private Set<Employee> emps;
	@Column(name="MAN_NAME")
	private String managerName;
	@Column(name="MAN_PHNO")
	private String managerNumber;
	@Column(name="MAN_EMAIL")
	private String managerEmail;
	@Column(name="PASSWORD")
	private String pass;
	@Column(name="USER_ID")
	private String userId; 
	
	public int getManagerId() {
		return managerId;
	}
	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}
	public Set<Employee> getEmpl() {
		return emps;
	}
	public void setEmpl(Set<Employee> emps) {
		this.emps = emps;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + managerId;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj) || (getClass() != obj.getClass()))
			return false;
		
		Manager other = (Manager) obj;
		return (managerId != other.managerId);
	}
	
	
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getManagerNumber() {
		return managerNumber;
	}
	public void setManagerNumber(String managerNumber) {
		this.managerNumber = managerNumber;
	}
	public String getManagerEmail() {
		return managerEmail;
	}
	public void setManagerEmail(String managerEmail) {
		this.managerEmail = managerEmail;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "Manager [managerId=" + managerId + ", emps=" + emps + ", managerName=" + managerName
				+ ", managerNumber=" + managerNumber + ", managerEmail=" + managerEmail + "]";
	}
	
	
}