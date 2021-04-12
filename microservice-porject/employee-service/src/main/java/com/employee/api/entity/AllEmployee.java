package com.employee.api.entity;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class AllEmployee {
	
	private List<Employee> employees;
	
	public AllEmployee() {
		// TODO Auto-generated constructor stub
	}

	public AllEmployee(List<Employee> employees) {
		super();
		this.employees = employees;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	@Override
	public String toString() {
		return "AllEmployee [employees=" + employees + "]";
	}
	
	

}
