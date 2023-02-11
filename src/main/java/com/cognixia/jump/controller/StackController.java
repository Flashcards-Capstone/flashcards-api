package com.cognixia.jump.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.exception.ResourceNotFoundException;

import com.cognixia.jump.model.Stack;
import com.cognixia.jump.model.User;
import com.cognixia.jump.repository.StackRepository;
import com.cognixia.jump.repository.UserRepository;
import com.cognixia.jump.service.StackService;
import com.cognixia.jump.service.UserService;

@RestController
@RequestMapping("/api")
public class StackController {

	@Autowired
	StackService service;
	
	@Autowired
	StackRepository repo;
	
	@Autowired
	UserService us;
	
	@Autowired
	UserRepository urepo;

	//for teacher to get all stacks
	@GetMapping("/stack")
	public List<Stack> getStacks() {
		return repo.findAll();
	}
	
	@GetMapping("/stack/{id}")
	public ResponseEntity<?> getStacksByStackId(@PathVariable int id) throws ResourceNotFoundException {
		
		Optional<Stack> found = repo.findById(id);
		
		if(found.isEmpty()) {
			throw new ResourceNotFoundException("Stack", id);
		}
		
		return ResponseEntity.status(200).body(found.get());
	}
	
//	@GetMapping("/stack/{user_id}")
//	public List<Stack> getStacksByUserId(@PathVariable int user_id) throws ResourceNotFoundException {
//		
//		
//		List<Stack> stacksByUserId = repo.stacksWithSameUserId(user_id);
//		
//		if(stacksByUserId.isEmpty()) {
//			throw new ResourceNotFoundException("Stack", user_id);
//		}
//		
//		return stacksByUserId;
//	}
//	
	@GetMapping("/stack/subject")
	public List<Stack> stacksInSameCategory(@RequestParam String subject) {
		
		return repo.stacksInSameSubject(subject);
	}
	
	
	@PostMapping("/stack")
	public ResponseEntity<?> newStack(@RequestParam (value= "user_id", required = true) String user_id, @RequestBody Stack stack) throws ResourceNotFoundException {
		
		User user = new User();
		
		
		Optional<User> optionalUser = us.getUserById(Integer.parseInt(user_id));
		
		if(optionalUser.isPresent()) {
			user = optionalUser.get();
		}
		else {
			throw new ResourceNotFoundException("user", Integer.parseInt(user_id));
		}
		stack.setUser(user);

		Stack created = repo.save(stack);
		

		user.getStacks().add(created);
		
		urepo.save(user);
		
		return ResponseEntity.status(201).body(created);		
		
		
	}
	
	
	
	
	
	
//	@PostMapping("/stack")
////	public ResponseEntity<?> createStack(@Valid @RequestBody Stack stack) throws ResourceNotFoundException {
//	public ResponseEntity<?> createStack(@Valid @RequestBody Stack stack){
//		//stack.setId(null);
//
//
//		Stack created = repo.save(stack);
//		
//		
//		return ResponseEntity.status(201).body(created);
//	}
	
	@PatchMapping("/stack/{id}")
	//public ResponseEntity<?> updateStack(@PathParam(value = "id") int id, @PathParam(value = "publiclyVisible") Boolean publiclyVisible, @PathParam(value = "title") String title, @PathParam(value = "subject") String subject) {
	public ResponseEntity<?> updateStack(@RequestParam int id, @RequestParam Boolean publiclyVisible, @RequestParam String title, @RequestParam String subject) {

		if(publiclyVisible != null) {
			service.updateVisible(id, publiclyVisible);
			
		}
		if(title != null) {
			service.updateTitle(id, title);
		}
		if(subject != null) {
			service.updateSubject(id, subject);
		}
		
//		Optional<Stack> updatedVis = service.updateVisible(id, publiclyVisible);
//		Optional<Stack> updatedTitle = service.updateTitle(id, title);
//		Optional<Stack> updatedSubject = service.updateSubject(id, subject);
		Optional<Stack> updatedStack = service.getStackById(id);

		
//		if(updatedVis.isEmpty() || updatedTitle.isEmpty() || updatedSubject.isEmpty()) {
//			return ResponseEntity.status(404)
//								 .body("Cannot update, can't find stack with id = " + id);
//		}
		if(updatedStack.isEmpty()) {
			return ResponseEntity.status(404)
								 .body("Cannot update, can't find stack with id = " + id);
		}
		else {
			return ResponseEntity.status(200)
								 .body(updatedStack.get());
		}
		
	}
	
	@DeleteMapping("/stack/{id}")
	public ResponseEntity<?> deleteStack(@PathVariable int id) throws ResourceNotFoundException {
		
		boolean exists = repo.existsById(id);
		
		if(exists) {
			
			// will return the student we just deleted in the response
			Stack deleted = repo.findById(id).get();
					
			repo.deleteById(id);
			
			return ResponseEntity.status(200).body(deleted);
		}
		
		throw new ResourceNotFoundException("Stack", id);
	}
	
	
	
	
}
