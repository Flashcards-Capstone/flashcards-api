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
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.exception.ResourceNotFoundException;

import com.cognixia.jump.model.Stack;
import com.cognixia.jump.repository.StackRepository;
import com.cognixia.jump.service.StackService;

@RestController
@RequestMapping("/api")
public class StackController {

	@Autowired
	StackService service;
	
	@Autowired
	StackRepository repo;

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
	@GetMapping("/stack/{user_id}")
	public ResponseEntity<?> getStacksByUserId(@PathVariable int user_id) throws ResourceNotFoundException {
		
		Optional<Stack> found = repo.findById(user_id);
		
		if(found.isEmpty()) {
			throw new ResourceNotFoundException("Stack", user_id);
		}
		
		return ResponseEntity.status(200).body(found.get());
	}
	
	@GetMapping("/stack/subject")
	public List<Stack> stacksInSameCategory(@PathParam(value = "subject") String subject) {
		
		return repo.stacksInSameSubject(subject);
	}
	
	@PostMapping("/stack/{user_id}")
	public ResponseEntity<?> createStack(@Valid @RequestBody Stack stack, @PathVariable int user_id) throws ResourceNotFoundException {
		
		stack.setId(null);
		
		// make sure each student created has an address, if not checked, will end up with 500 error
		if(stack.getTitle() == null) {
			return ResponseEntity.status(400).body("Stack must have a name");
		}
		
		if(stack.getSubject() == null) {
			return ResponseEntity.status(400).body("Stack must have a subject");

		}
		
		

		Stack created = repo.save(stack);
		
		
		return ResponseEntity.status(201).body(created);
	}
	
	@PatchMapping("/stack/{id}")
	public ResponseEntity<?> updateStack(@PathParam(value = "id") int id, @PathParam(value = "publiclyVisible") Boolean publiclyVisible, @PathParam(value = "title") String title, @PathParam(value = "subject") String subject) {
		
		Optional<Stack> updatedVis = service.updateVisible(id, publiclyVisible);
		Optional<Stack> updatedTitle = service.updateTitle(id, title);
		Optional<Stack> updatedSubject = service.updateSubject(id, subject);
		Optional<Stack> updatedStack = service.getStackById(id);

		
		if(updatedVis.isEmpty() || updatedTitle.isEmpty() || updatedSubject.isEmpty()) {
			return ResponseEntity.status(404)
								 .body("Cannot update, can't find student with id = " + id);
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
