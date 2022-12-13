package com.example.employee.services;

import java.util.List;

import com.example.employee.entity.Employee;

public interface EmployeeService {

	public Employee addEmployee(Employee employee);

	public List<Employee> findAllEmployees();

	public Employee findEmployeeById(long employeeId);

	public Employee updateEmployee(long employeeId, Employee employee);
	
	public List<Employee> applyBonus();

}
