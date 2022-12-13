package com.example.employee;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.example.employee.controller.EmployeeController;
import com.example.employee.entity.Employee;
import com.example.employee.repo.EmployeeRepo;
import com.example.employee.services.EmployeeService;

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {

	@InjectMocks
	EmployeeController employeeController;

	@Mock
	EmployeeService service;

	@Mock
	EmployeeRepo repo;

	@Test
	public void testAddEmployee() {
		Employee employee = new Employee(1, "Vikas", 10000, LocalDate.parse("2022-12-12"), "UK");
		ResponseEntity<Employee> responseEntity = employeeController.addEmployee(employee);

		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
	}

	@Test
	public void testUpdateUser() {
		Employee emp = new Employee(1, "Sumit", 10000, LocalDate.parse("2022-12-12"), "APAC");

		ResponseEntity<Employee> responseEntity = employeeController.updateEmployee(1L,emp);

		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
	}

	@Test
	public void testFetchAllEmployees() {
		Employee emp = new Employee();
		emp.setName("John");
		emp.setRegion("UK");
		emp.setSal(100000);
		emp.setJoin_date(LocalDate.parse("2022-12-12"));

		List<Employee> empList = new ArrayList<Employee>();
		empList.add(emp);

		when(repo.findAll()).thenReturn(empList);

		List<Employee> fetchedEmployees= repo.findAll();
		assertThat(fetchedEmployees.size()).isGreaterThan(0);
	}
}
