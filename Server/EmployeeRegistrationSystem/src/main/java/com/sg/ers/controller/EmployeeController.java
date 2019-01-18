package com.sg.ers.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sg.ers.bean.Employee;
import com.sg.ers.dao.EmployeeRepository;

@RestController
@RequestMapping(value = { "/employee" })
@CrossOrigin(origins = "http://localhost:8081")
public class EmployeeController {

	@Autowired
    private EmployeeRepository employeeRepository;

	/**
	 * This method is used to register employee.
	 * 
	 * @param emp the employee information is passed as an employee object.
	 * @return the ResponseEntity with http status code.
	 */
	@PostMapping(value = "/create")
	public ResponseEntity<String> createUser(@RequestBody Employee emp) {
		employeeRepository.save(emp );
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	/**
	 * This method is used to get all employees data.
	 * 
	 * @return the List of Employee.
	 */
	@GetMapping(value = "/getAllEmployees", headers = "Accept=application/json")
	public List<Employee> getAllEmployee() {
		
		return (List<Employee>) employeeRepository.findAll();
	}
}
