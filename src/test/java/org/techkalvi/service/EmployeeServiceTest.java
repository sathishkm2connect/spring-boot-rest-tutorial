package org.techkalvi.service;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.techkalvi.dao.EmployeeDAO;
import org.techkalvi.entity.Employee;
import org.techkalvi.exception.EmployeeIdInvalidException;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {EmployeeService.class})
public class EmployeeServiceTest {
	
	@Autowired
	EmployeeService employeeService;
	
	@MockBean
	EmployeeDAO employeeDAO;
	
	@Test
	public void testGetEmployee() throws EmployeeIdInvalidException {
        Employee employee = new Employee(10l, "Suresh", "Karthick", "Male", new Date(), new Date());
		Mockito.when(employeeDAO.getEmployee(10l)).thenReturn(employee);
		Employee result = employeeService.getEmployee(10l);
		assertNotNull(result);
		assertEquals("Suresh",result.getFirstName());
	}

}
