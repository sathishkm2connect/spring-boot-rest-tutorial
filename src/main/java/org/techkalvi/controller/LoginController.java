package org.techkalvi.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.techkalvi.dto.AuthRequestDTO;
import org.techkalvi.dto.JwtResponseDTO;
import org.techkalvi.service.CustomAuthenticationManager;
import org.techkalvi.service.JwtService;

@RestController
public class LoginController {
	
	@Autowired
	CustomAuthenticationManager authenticationManager;
	
	@Autowired
	JwtService jwtService;
	
	@PostMapping("/login")
	public ResponseEntity<JwtResponseDTO> AuthenticateAndGetToken(@RequestBody AuthRequestDTO authRequestDTO){
	    Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword()));
	    if(authentication.isAuthenticated()){
	       return ResponseEntity.ok(new JwtResponseDTO(jwtService.GenerateToken(authRequestDTO.getUsername())));
	    } else {
	        throw new RuntimeException("invalid user request..!!");
	    }
	}
}
