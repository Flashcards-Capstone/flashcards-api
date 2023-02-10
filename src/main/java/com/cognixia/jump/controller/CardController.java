package com.cognixia.jump.controller;

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
import com.cognixia.jump.model.Card;
import com.cognixia.jump.repository.CardRepository;
import com.cognixia.jump.service.CardService;

@RestController
@RequestMapping("/api")
public class CardController {
	@Autowired
	CardRepository repo;
	
	@Autowired
	CardService service;
	
	
	
	@GetMapping("/card/{stack_id}")
	public ResponseEntity<?> getStacksByStackId(@PathVariable int stack_id) throws ResourceNotFoundException {
		
		Optional<Card> found = repo.findById(stack_id);
		
		if(found.isEmpty()) {
			throw new ResourceNotFoundException("Card", stack_id);
		}
		
		return ResponseEntity.status(200).body(found.get());
	}
	
	@PostMapping("/card")
	public ResponseEntity<?> createCard(@Valid @RequestBody Card card) {
		
		card.setId(null);
		
		// make sure each student created has an address, if not checked, will end up with 500 error
		if(card.getStackId() == null) {
			return ResponseEntity.status(400).body("Card must belong to a stack");
		}
		
		if(card.getQuestions() == null) {
			return ResponseEntity.status(400).body("Card must have a question");

		}
		if(card.getAnswer() == null) {
			return ResponseEntity.status(400).body("Card must have an answer");

		}
		


		Card created = repo.save(card);
		
		
		return ResponseEntity.status(201).body(created);
	}
	
	
	@PatchMapping("/card/{id}")
	public ResponseEntity<?> updateCard(@PathParam(value = "id") int id, @PathParam(value = "question") String question, @PathParam(value = "answer") String answer) {
		
		Optional<Card> updatedQuestion = service.updateQuestion(id, question);
		Optional<Card> updatedAnswer = service.updateAnswer(id, answer);
		Optional<Card> updatedCard = service.getCardById(id);
		
		if(updatedQuestion.isEmpty() || updatedAnswer.isEmpty()) {
			return ResponseEntity.status(404)
								 .body("Cannot update, can't find student with id = " + id);
		}
		else {
			return ResponseEntity.status(200)
								 .body(updatedCard.get());
		}
		
	}
	
	
	
	@DeleteMapping("/card/{id}")
	public ResponseEntity<?> deleteCard(@PathVariable int id) throws ResourceNotFoundException {
		
		boolean exists = repo.existsById(id);
		
		if(exists) {
			
			// will return the student we just deleted in the response
			Card deleted = repo.findById(id).get();
					
			repo.deleteById(id);
			
			return ResponseEntity.status(200).body(deleted);
		}
		
		throw new ResourceNotFoundException("Card", id);
	}
	
	
	
	
	
	
}
