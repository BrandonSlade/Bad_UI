package com.revature.Bad_UI.service;

import java.security.NoSuchAlgorithmException;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import com.revature.Bad_UI.beans.Credentials;
import com.revature.Bad_UI.beans.User;
import com.revature.Bad_UI.repositories.UserRepository;

@Service
public class UserService {
	
	UserRepository userRepository;

	@Inject
	public UserService(UserRepository userRepository)
	{
		super();
		this.userRepository = userRepository;
	}
	
	public User getById(int id) {
		return this.userRepository.getById(id);
	}

	public User create(User user) throws NoSuchAlgorithmException {
		user.setHashedPassword(user.getPassword());
		return this.userRepository.create(user);
	}

	public User login(User user) throws NoSuchAlgorithmException {
		Credentials credentials = new Credentials();
		credentials.setUsername(user.getUsername());
		credentials.setPassword(user.getPassword());
		credentials.setHashedPassword(credentials.getPassword());
		return this.userRepository.login(credentials);
	}

}
