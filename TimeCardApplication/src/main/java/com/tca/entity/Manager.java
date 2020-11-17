package com.tca.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="MANAGER")   //additional column to differ parent and child
/*	
 * //@NamedQueries({ // @NamedQuery(name = "Manager.getList", query =
 * "SELECT t FROM Manager t where t.emp=:emp"), // @NamedQuery(name =
 * "Manager.", query =
 * "SELECT t FROM EmployeeAccount t where t.userId=:emp and t.password=:pwd")
 * //})
 */public class Manager extends Employee{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id	
	@Column(name="MAN_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int managerID;
	
	@OneToMany(mappedBy = "MANAGER",
	           cascade = CascadeType.ALL,
	           orphanRemoval = true)
	private List<Employee> emps = new ArrayList<>();
	
	public int getManagerID() {
		return managerID;
	}
	public void setManagerID(int managerID) {
		this.managerID = managerID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + managerID;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj) || (getClass() != obj.getClass()))
			return false;
		
		Manager other = (Manager) obj;
		return (managerID != other.managerID);
	}
	@Override
	public String toString() {
		return "Manager [managerID=" + managerID + ", emps=" + emps + "]";
	}

	
	
}