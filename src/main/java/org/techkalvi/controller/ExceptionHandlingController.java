package org.techkalvi.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.techkalvi.exception.EmployeeIdInvalidException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@ControllerAdvice
class GlobalDefaultExceptionHandler {

  @ExceptionHandler(value = EmployeeIdInvalidException.class)
  public ResponseEntity<ObjectNode> handleError(Exception e) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode node = mapper.createObjectNode();
	    node.put("status", "error");
	    node.put("errorMessage", e.getMessage());
	    return ResponseEntity.internalServerError().body(node);
  }
}
