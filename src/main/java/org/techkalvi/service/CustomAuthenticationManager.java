package org.techkalvi.service;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.techkalvi.entity.User;
import org.techkalvi.exception.InvalidUserException;

@Component
public class CustomAuthenticationManager implements AuthenticationManager {

	@Autowired
	UserService userService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		String username = authentication.getName();
        String password = authentication.getCredentials().toString();  
        try {
			User user = userService.validateUser(new User(username,password));
			SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
	        return new UsernamePasswordAuthenticationToken(
	        		username, password, Arrays.asList(authority));
		} catch (InvalidUserException e) {
			// TODO Auto-generated catch block
			 throw new BadCredentialsException(e.getMessage());
		}

	}
}