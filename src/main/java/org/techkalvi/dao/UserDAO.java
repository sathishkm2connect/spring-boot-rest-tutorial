package org.techkalvi.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.techkalvi.entity.User;

@Component
public class UserDAO {
	
	@Autowired
	UserRepository userRepository;
	
	public User getUser(String username) {
		Optional<User> user = userRepository.findById(username);
		return user.orElse(null);
	}
	
	public void saveUser(User user) {
		userRepository.save(user);
	}

}
