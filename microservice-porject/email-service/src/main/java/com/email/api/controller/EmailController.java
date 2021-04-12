package com.email.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.email.api.entity.Employee;
import com.email.api.service.EmailService;

@RestController
@RequestMapping("/email")
public class EmailController {

	@Autowired
	EmailService emailService;

	@PostMapping("/resign")
	public ResponseEntity<Boolean> resignEmployee(@RequestBody Employee e) {
		System.out.println("email project");
		return ResponseEntity.ok(emailService.resignEmployee(e));

	}

}
