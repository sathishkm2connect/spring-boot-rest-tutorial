package org.techkalvi.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.techkalvi.entity.Employee;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeRespositoryTest {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Test
    public void testSaveEmployee() {
        Employee employee = new Employee(10l, "Suresh", "Karthick", "Male", new Date(), new Date());
        employeeRepository.save(employee);
        Optional<Employee> savedEmployee = employeeRepository.findById(employee.getEmpNo());
        assertTrue(savedEmployee.isPresent());
        assertEquals("Suresh", savedEmployee.get().getFirstName());
    }

} 
