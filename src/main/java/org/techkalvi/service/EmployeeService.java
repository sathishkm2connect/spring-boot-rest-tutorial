package org.techkalvi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.techkalvi.dao.EmployeeDAO;
import org.techkalvi.entity.Employee;


@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeDAO employeeDao;

    public List<Employee> getAllEmployees() {
        return employeeDao.getAllEmployees();
    }
    
    @Transactional
    public void processSalaryAndPayroll() {
    	
    }

	public void saveEmployee(Employee employee) {
		// TODO Auto-generated method stub
		employeeDao.saveEmployee(employee);
	}

	public Employee getEmployee(Long id) {
		// TODO Auto-generated method stub
		return employeeDao.getEmployee(id);
	}
}
