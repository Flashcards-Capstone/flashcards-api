package com.cognixia.jump.controller;

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
import com.cognixia.jump.model.Card;
import com.cognixia.jump.model.Stack;
import com.cognixia.jump.repository.CardRepository;
import com.cognixia.jump.repository.StackRepository;
import com.cognixia.jump.service.CardService;
import com.cognixia.jump.service.StackService;

@RestController
@RequestMapping("/api")
public class CardController {
	@Autowired
	CardRepository repo;

	@Autowired
	CardService service;

	@Autowired
	StackService ss;

	@Autowired
	StackRepository srepo;

	@GetMapping("/card/{stack_id}")
	public ResponseEntity<?> getStacksByStackId(@PathVariable int stack_id) throws ResourceNotFoundException {

		Stack stack = srepo.findById(stack_id).get();

		List<Card> cards = stack.getCards();

		return ResponseEntity.status(200).body(cards);
	}

	@PostMapping("/card")
	public ResponseEntity<?> createCard(@RequestParam(value = "stack_id", required = true) String stack_id,
			@RequestBody Card card) throws ResourceNotFoundException {

		// card.setId(null);

		Stack stack = new Stack();

		Optional<Stack> optionalStack = ss.getStackById(Integer.parseInt(stack_id));

		if (optionalStack.isPresent()) {
			stack = optionalStack.get();
		} else {
			throw new ResourceNotFoundException("stack", Integer.parseInt(stack_id));

		}
		card.setStack(stack);

		Card created = repo.save(card);

		stack.getCards().add(created);

		srepo.save(stack);

		return ResponseEntity.status(201).body(created);
	}

	@PatchMapping("/card/{id}")
	public ResponseEntity<?> updateCard(@PathVariable Integer id, @RequestParam String question,
			@RequestParam String answer) throws ResourceNotFoundException {

		if (question != null) {
			service.updateQuestion(id, question);
		} else {
			throw new ResourceNotFoundException("card", id);

		}
		if (answer != null) {
			service.updateAnswer(id, answer);
		} else {
			throw new ResourceNotFoundException("card", id);

		}

		Card card = repo.findById(id).get();

		Card created = repo.save(card);

		card = created;

		return ResponseEntity.status(200).body(card);

	}

	@DeleteMapping("/card/{id}")
	public ResponseEntity<?> deleteCard(@PathVariable int id) throws ResourceNotFoundException {

		boolean exists = repo.existsById(id);

		if (exists) {

			// will return the student we just deleted in the response
			Card deleted = repo.findById(id).get();

			repo.deleteById(id);

			return ResponseEntity.status(200).body(deleted);
		}

		throw new ResourceNotFoundException("Card", id);
	}

}
