package com.cognixia.jump.controller;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import com.cognixia.jump.model.Card;
import com.cognixia.jump.model.Stack;
import com.cognixia.jump.repository.CardRepository;
import com.cognixia.jump.service.CardService;
import com.cognixia.jump.service.MyUserDetailsService;
import com.cognixia.jump.util.JwtUtil;

@WebMvcTest(CardController.class)
public class CardControllerTest {
	private static final String STARTING_URI = "http://localhost:8080/api";

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private CardService service;

	@MockBean
	private CardRepository repo;
	
	@MockBean
	PasswordEncoder encoder;
	
	@MockBean
	MyUserDetailsService dService;
	
	@MockBean
	JwtUtil util;
	
	@InjectMocks
	private CardController controller;
//	@Test
//	void testGetCardByStackId() throws Exception{
//		
//		String uri = STARTING_URI + "/card/{stack_id}";
//		Integer stack_id = 1;
//		
//		Stack stack = new Stack();
//		stack.setId(1);
//		stack.setPubliclyVisible(true);
//		stack.setTitle("title");
//		stack.setSubject("subject");
//		
//		Card card = new Card();
//		card.setId(1);
//		card.setQuestion("question");
//		card.setAnswer("answer");
//		card.setStack(stack);
//		
//		when(service.getCardById(1).get()).return(card);
//		
//		
//		
//	}

}
