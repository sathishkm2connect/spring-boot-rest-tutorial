package org.techkalvi.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.techkalvi.entity.Employee;
import org.techkalvi.exception.EmployeeIdInvalidException;
import org.techkalvi.service.EmployeeService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Date;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeRestController.class)
public class EmployeeRestControllerTest {

	
	@Autowired
	private MockMvc mockMvc;
	  
	@MockBean
	private EmployeeService employeeService;
	  
	    @Test
	    public void testGetEmployeeApi() throws Exception {
	        Employee employee = new Employee(10l, "Suresh", "Karthick", "Male", new Date(), new Date());
			Mockito.when(employeeService.getEmployee(10l)).thenReturn(employee);
			Mockito.when(employeeService.getEmployee(9l)).thenThrow(new EmployeeIdInvalidException("Invalid Employee"));
	        mockMvc.perform(get("/employee/get/10"))
	        		.andDo(print())
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.firstName").value("Suresh"));
	    }
   
}
