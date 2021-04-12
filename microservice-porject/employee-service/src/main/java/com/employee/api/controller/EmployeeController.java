package com.employee.api.controller;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.api.entity.AllEmployee;
import com.employee.api.entity.Employee;
import com.employee.api.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	// get all the employees
	@GetMapping("/all")
	public ResponseEntity<AllEmployee> getAllEmployees() {
		return ResponseEntity.ok(employeeService.getAllEmployees());
	}

	// get a employee
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Employee>> getEmployee(@PathVariable String id) {
		return ResponseEntity.of(Optional.of(employeeService.getEmployeeById(id)));
	}

	// add employee
	@PostMapping("/add")
	public void addEmployee(@RequestBody Employee employee) {
		employeeService.addEmployee(employee);

	}

	// delete employee
	@DeleteMapping("/del/{id}")
	public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable String id) {
		employeeService.deleteEmployeeById(id);
		return new ResponseEntity<>(HttpStatus.OK);

	}

	// resign employee
	@PutMapping("/resign/{id}")
	public ResponseEntity<?> resignEmployee(@PathVariable String id) {

		employeeService.resignEmployee(id);
		return ResponseEntity.ok("Done");

	}
}
