package org.techkalvi.service;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.techkalvi.dao.UserDAO;
import org.techkalvi.entity.User;
import org.techkalvi.exception.*;

@Service
public class UserService {
	
	@Autowired
	UserDAO userDao;
	
	public User getUser(String userName) {
		return userDao.getUser(userName);
	}
	
	public User validateUser(User inputUser) throws InvalidUserException {
		User user = userDao.getUser(inputUser.getUsername());
		if(user!=null) {
			BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();  
			boolean isPasswordMatches = bcrypt.matches(inputUser.getPassword(), user.getPassword());
			if(isPasswordMatches)
				return user;
			throw new InvalidUserException("username or password is Invalid");
		}
		throw new InvalidUserException("username or password is Invalid");
	}
	
	public void saveUser(String userName, String password, String role) {
		 BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();  
		 String encodedPassword = bcrypt.encode(password);
		 userDao.saveUser(new User(userName,encodedPassword,role));
	}

}