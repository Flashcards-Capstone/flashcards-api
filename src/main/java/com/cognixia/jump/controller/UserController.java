package com.cognixia.jump.controller;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.User;
import com.cognixia.jump.repository.UserRepository;
import com.cognixia.jump.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	UserRepository repo;
	@Autowired
	UserService service;

	@Autowired
	PasswordEncoder encoder;
	
	@GetMapping("/login/{username}")
<<<<<<< HEAD
	//public ResponseEntity<?> getUserByUsernameAndPassword(@PathVariable String username, String password) throws ResourceNotFoundException{
//		String username = test.get("username");
		//String encodedPassword = encoder.encode(password);
		public ResponseEntity<?> getUserByUsername(@PathVariable String username) throws ResourceNotFoundException{
=======
	public ResponseEntity<?> getUserByUsername(@PathVariable String username) throws ResourceNotFoundException{
		Optional<User> user = repo.findByUsername(username);
>>>>>>> 74276aa6634846f6c6c68c3b28ddd0892352c1e9

		
		//User user = service.getUserByUsernameAndPassword(username, password);
		User user = service.getUserByUsernameAndPassword(username);

		return ResponseEntity.status(200).body(user);
	}

	@GetMapping("/user")
	public List<User> getAllUsers() {
		return service.getUsers();
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<?> getUserById(@PathVariable int id) {

		Optional<User> user = repo.findById(id);

		if (user.isEmpty()) {
			return ResponseEntity.status(404).body("User not found");
		} else {
			return ResponseEntity.status(200).body(user.get());
		}
	}


	
	@PostMapping("/user")
	public ResponseEntity<?> createUser(@RequestBody User user) {

		user.setId(null);

		//user.setPassword(encoder.encode(user.getPassword()));
		
		user.setPassword(encoder.encode(user.getPassword()));

		//user.setNewStacks(); 

		User created = repo.save(user);

		return ResponseEntity.status(201).body(created);

	}
		
}
