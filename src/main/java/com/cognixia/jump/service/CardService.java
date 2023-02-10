package com.cognixia.jump.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.jump.model.Card;
import com.cognixia.jump.repository.CardRepository;

@Service
public class CardService {
	@Autowired
	CardRepository repo;
	
	public Optional<Card> getCardById(int id){
		
		return repo.findById(id);
		
	}

	public Optional<Card> updateQuestion(int id, String question){
		int count = repo.updateQuestion(id, question);
		if(count > 0) {
			return getCardById(id);
		}
		else {
			return Optional.empty();
		}
	}
	
	public Optional<Card> updateAnswer(int id, String answer){
		int count = repo.updateAnswer(id, answer);
		if(count > 0) {
			return getCardById(id);
		}
		else {
			return Optional.empty();
		}
		
	}
}
