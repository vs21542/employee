package com.example.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.employee.entity.Employee;
import com.example.employee.services.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	/*
	 * Get All the employees list.
	 */
	@RequestMapping(value = "/employees", method = RequestMethod.GET)
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> employees = employeeService.findAllEmployees();
		return new ResponseEntity<>(employees, HttpStatus.OK);
	}

	/*
	 * Get employee by id.
	 */
	@RequestMapping(value = "/employees/{id}", method = RequestMethod.GET)
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(required = true, name = "id") Long id) {
		Employee employee = employeeService.findEmployeeById(id);
		return new ResponseEntity<>(employee, HttpStatus.OK);
	}

	/*
	 * Insert employees to employee table.
	 */
	@RequestMapping(value = "/employees", method = RequestMethod.POST)
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
		Employee newEmployee = employeeService.addEmployee(employee);
		return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
	}

	/*
	 * Update employees.
	 */
	@RequestMapping(value = "/employees/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Employee> updateEmployee(@PathVariable(required = true, name = "id") Long id,@RequestBody Employee employee) {
		Employee updateEmployee = employeeService.updateEmployee(id, employee);
		return new ResponseEntity<>(updateEmployee, HttpStatus.OK);
	}

	/*
	 * Apply bonus on salary based on region, salary and joining date.
	 */
	@RequestMapping(value = "/employees/bouns", method = RequestMethod.POST)
	public ResponseEntity<List<Employee>> applyBonus() {
		List<Employee> bounsEmpList = employeeService.applyBonus();
		return new ResponseEntity<>(bounsEmpList, HttpStatus.OK);
	}
}
