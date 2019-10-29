package com.revature.Bad_UI.controllers;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import javax.inject.Inject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import com.revature.Bad_UI.beans.User;
import com.revature.Bad_UI.service.UserService;

@RestController
@RequestMapping("Users")
public class UserController {
	
	private UserService userService;
	
	@Inject
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@GetMapping("/{id}")
	public User getById(@PathVariable int id) {
		return Optional.ofNullable(this.userService.getById(id))
			.orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
	}

	@PostMapping("signup")
	@ResponseStatus(HttpStatus.CREATED)
	public User createUser(@RequestBody User user) throws NoSuchAlgorithmException {
		return this.userService.create(user);
	}
	
	@PostMapping("login")
	public ResponseEntity<User> login(@RequestBody User user) throws NoSuchAlgorithmException {
		user = this.userService.login(user);
		
		if( user == null){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}else {
			return ResponseEntity.ok(user);
		}
		
	}
}




































