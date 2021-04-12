package com.employee.api.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.employee.api.dao.EmployeeDao;
import com.employee.api.entity.AllEmployee;
import com.employee.api.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	RestTemplate restTemplate;
	@Autowired
	EmployeeDao dao;

	@Override
	public AllEmployee getAllEmployees() {
		// TODO Auto-generated method stub
		List<Employee> list = dao.getAll();
		if (list.size() <= 0) {
			throw new NullPointerException();
		}
		AllEmployee emp = new AllEmployee();
		emp.setEmployees(list);
		return emp;
	}

	@Override
	public Optional<Employee> getEmployeeById(String id) {
		// TODO Auto-generated method stub
		Optional<Employee> emp = dao.getEmployeeById(id);
		if (emp == null) {
			throw new NullPointerException();
		}
		return emp;
	}

	@Override
	public void addEmployee(Employee employee) {
		// TODO Auto-generated method stub
		LocalDateTime date = LocalDateTime.now();
		employee.setEmpDOJ(Timestamp.valueOf(date));
		employee.setEmpDOR(null);
		dao.save(employee);
	}

	@Override
	public void deleteEmployeeById(String id) {
		// TODO Auto-generated method stub
		dao.deleteEmployeeById(id);
	}

	@Override
	public void resignEmployee(String id) {
		// TODO Auto-generated method stub

		Optional<Employee> e = dao.getEmployeeById(id);
		Employee employee;
		if (!e.isEmpty())
			employee = e.get();
		else
			throw new NullPointerException();
		LocalDateTime lt = LocalDateTime.now().plusMonths(2);
		Timestamp t = Timestamp.valueOf(lt);
		employee.setEmpDOR(t);
		dao.resignEmployee(id, t);
		ResponseEntity<Boolean> result = restTemplate.postForEntity("http://email-service/email/resign",employee,Boolean.class );

		
		  if (!result.getBody())
			  throw new RuntimeException("mail not sent !!!");
		 
	}

}
