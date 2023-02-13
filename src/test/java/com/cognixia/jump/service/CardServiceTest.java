package com.cognixia.jump.service;

import static org.mockito.Mockito.when;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cognixia.jump.model.Card;
import com.cognixia.jump.repository.CardRepository;

@ExtendWith(MockitoExtension.class)
public class CardServiceTest {
	
	@Mock
	private CardRepository repo;
	
	@InjectMocks
	private CardService service;
	
	@Test
	void testGetCardById() throws Exception{
		
		int id = 1;
		Card card = new Card();
		card.setId(id);
		card.setQuestion("question");
		card.setAnswer("answer");
		
		when(repo.findById(id)).thenReturn(Optional.of(card));
		Card result = service.getCardById(id).get();
		
		assertEquals(card, result);
		
		
	}
	@Test
	void testUpdateQuestion() throws Exception{
		int id = 1;
		String question = "newQuestion";
		Card card = new Card();
		card.setId(id);
		card.setQuestion("Question");
		card.setAnswer("answer");
		
		when(repo.updateQuestion(id, question)).thenReturn(1);
		when(repo.findById(id)).thenReturn(Optional.of(card));
		
		Card updated = service.updateQuestion(id, question).get();
		
		assertEquals(card, updated);
	}
	@Test
	void testUpdateAnswer() throws Exception{
		int id = 1;
		String answer = "newAnswer";
		Card card = new Card();
		card.setId(id);
		card.setQuestion("Question");
		card.setAnswer("answer");
		
		when(repo.updateAnswer(id, answer)).thenReturn(1);
		when(repo.findById(id)).thenReturn(Optional.of(card));
		
		Card updated = service.updateAnswer(id, answer).get();
		
		assertEquals(card, updated);
	}

}
