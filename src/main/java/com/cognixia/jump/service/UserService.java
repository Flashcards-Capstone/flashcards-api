package com.cognixia.jump.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.User;
import com.cognixia.jump.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository repo;
	

	
	public List<User> getUsers(){
		return repo.findAll();
	}
	
//	public Optional<User> getUserById(Integer id) {
//		
//		return repo.findById(id);
//		
//		
//	}

	public User getUserById(int id) throws ResourceNotFoundException {
		
		Optional<User> found = repo.findById(id);
		
		if(found.isEmpty()) {
			throw new ResourceNotFoundException("User", id);
		}
		
		return found.get();
	}
	
	//public User getUserByUsernameAndPassword(String username, String password) {
		public User getUserByUsernameAndPassword(String username) {

		//Integer id = repo.loggedInUser(username, password);
		Integer id = repo.loggedInUser(username);

		if(id == null) {
			System.out.println("ID is NULL");;
		}
		
		Optional<User> user = repo.findById(id);
		
		return user.get();
		
		
	}
	

}
