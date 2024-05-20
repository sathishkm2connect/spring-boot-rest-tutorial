package org.techkalvi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.techkalvi.entity.Employee;
import org.techkalvi.service.EmployeeService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/employee")
public class EmployeeRestController{
	
	@Autowired
	EmployeeService employeeService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<Employee>> employee() {
		List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public ResponseEntity<ObjectNode> addEmployee(@Valid @RequestBody Employee employee, BindingResult bindingResult) {
		ObjectMapper objectMapper = new ObjectMapper();
		ObjectNode node = objectMapper.createObjectNode();
		if(bindingResult.hasErrors()) {
			node.put("status", "error");
			ObjectNode errorNode = objectMapper.createObjectNode();
			  bindingResult
              .getFieldErrors()
              .forEach(f -> errorNode.put(f.getField(), f.getDefaultMessage()));
			node.set("errors", errorNode);
			return ResponseEntity.badRequest().body(node);
		}
		employeeService.saveEmployee(employee);
		return ResponseEntity.ok(node.put("status", "success"));
	}
	
	@RequestMapping(value="/get/{id}", method = RequestMethod.GET)
	public ResponseEntity<Employee> newEmployee(@PathVariable("id") long id) {
		Employee employee = employeeService.getEmployee(id);
		return ResponseEntity.ok(employee);
	}
	
}
