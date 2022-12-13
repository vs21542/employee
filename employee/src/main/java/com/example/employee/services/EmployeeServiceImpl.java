package com.example.employee.services;

import java.time.Year;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.employee.entity.Employee;
import com.example.employee.exception.UserNotFoundException;
import com.example.employee.repo.EmployeeRepo;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepo repo;

	@Override
	public Employee addEmployee(Employee employee) {
		return repo.save(employee);
	}

	@Override
	public List<Employee> findAllEmployees() {
		return repo.findAll();
	}

	@Override
	public Employee findEmployeeById(long employeeId) {
		return repo.findEmployeeById(employeeId)
				.orElseThrow(() -> new UserNotFoundException("User by id " + employeeId + " was not found"));
	}

	@Override
	public Employee updateEmployee(long employeeId, Employee employee) {
		Employee empDb = repo.findById(employeeId).get();

		if (Objects.nonNull(employee.getName()) && !"".equalsIgnoreCase(employee.getName())) {
			empDb.setName(employee.getName());
		}
		if (Objects.nonNull(employee.getRegion()) && !"".equalsIgnoreCase(employee.getRegion())) {
			empDb.setRegion(employee.getRegion());
		}
		if (Objects.nonNull(employee.getSal())) {
			empDb.setSal(employee.getSal());
		}
		if (Objects.nonNull(employee.getJoin_date())) {
			empDb.setJoin_date(employee.getJoin_date());
		}
		return repo.save(empDb);
	}

	@Override
	public List<Employee> applyBonus() {
		List<Employee> allEmployeesList = repo.findAll();

		List<Employee> filteredList = allEmployeesList.stream().filter(empl -> "UK".equalsIgnoreCase(empl.getRegion())
				&& empl.getSal() >= 50000 && (empl.getJoin_date().getYear() == Year.now().getValue())).map(empl -> {
					empl.setSal(empl.getSal() + ((empl.getSal() * 5) / 100));
					return empl;
				}).collect(Collectors.toList());

		for (Employee emp : filteredList) {
			Employee empl = new Employee(emp.getId(), emp.getName(), emp.getSal(), emp.getJoin_date(), emp.getRegion());
			repo.save(empl);
		}

		return filteredList;
	}
}
