package org.techkalvi.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.techkalvi.entity.Employee;

@Component
public class EmployeeDAO {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	public List<Employee> getAllEmployees() {
		 List<Employee> employees = employeeRepository.findAll();
     	 return employees;
	}
	
	public void saveEmployee(Employee employee) {
		 employeeRepository.save(employee);
	}

	public Employee getEmployee(Long id) {
		// TODO Auto-generated method stub
		return employeeRepository.findById(id).orElse(null);
	}
}
