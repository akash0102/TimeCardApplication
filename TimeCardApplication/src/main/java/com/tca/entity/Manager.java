package com.tca.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


   //additional column to differ parent and child
/*	
 * //@NamedQueries({ // @NamedQuery(name = "Manager.getList", query =
 * "SELECT t FROM Manager t where t.emp=:emp"), // @NamedQuery(name =
 * "Manager.", query =
 * "SELECT t FROM EmployeeAccount t where t.userId=:emp and t.password=:pwd")
 * //})
 */
@Entity
@Table(name="MANAGER")
public class Manager{
	
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="MAN_ID",insertable = false,updatable = false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int managerId;
	@OneToOne
	@JoinColumn(name="EMP_ID")
	private Employee empl;
	
	@OneToMany(mappedBy = "manager",
	           cascade = CascadeType.ALL,
	           orphanRemoval = true)
	private Set<Employee> emps;
	
	public Set<Employee> getEmps() {
		return emps;
	}
	public void setEmps(Set<Employee> emps) {
		this.emps = emps;
	}
	public int getmanagerId() {
		return managerId;
	}
	public void setmanagerId(int managerId) {
		this.managerId = managerId;
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
	@Override
	public String toString() {
		return "Manager [managerId=" + managerId + ", emps=" + emps + "]";
	}

	
	
}