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
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="EMP_ID")
	private Employee empl;
	
	@OneToMany(mappedBy = "manager",
	           cascade = CascadeType.ALL,
	           orphanRemoval = true)
	@Column(name="EMPS")
	private Set<Employee> emps;
	
	public Set<Employee> getEmps() {
		return emps;
	}
	public void setEmps(Set<Employee> emps) {
		this.emps = emps;
	}
	public int getManagerId() {
		return managerId;
	}
	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}
	public Employee getEmpl() {
		return empl;
	}
	public void setEmpl(Employee empl) {
		this.empl = empl;
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