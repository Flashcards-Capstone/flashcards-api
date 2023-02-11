package com.cognixia.jump.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.model.User;
import com.cognixia.jump.repository.UserRepository;

@RequestMapping("/api")
@RestController
public class UserController {

	@Autowired
	UserRepository repo;
	

	@Autowired
	PasswordEncoder encoder;

	@GetMapping("/user")
	public List<User> getAllStudents() {
		return repo.findAll();
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

		user.setPassword(encoder.encode(user.getPassword()));
		
		//user.setNewStacks(); 

		User created = repo.save(user);

		return ResponseEntity.status(201).body(created);

	}
		
}
