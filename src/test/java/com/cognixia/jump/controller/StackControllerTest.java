package com.cognixia.jump.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.cognixia.jump.model.Stack;
import com.cognixia.jump.model.User;
import com.cognixia.jump.model.User.Role;
import com.cognixia.jump.repository.StackRepository;
import com.cognixia.jump.service.StackService;



@WebMvcTest(StackController.class)
public class StackControllerTest {
	private static final String STARTING_URI = "http://localhost:8080/api";

	@Autowired
	private MockMvc mvc;

	@MockBean
	private StackService service;

	@MockBean
	private StackRepository repo;

	@InjectMocks
	private StackController controller;
	
	@Test
	void testGetStacks() throws Exception {

		Integer id = 1;
		String uri = STARTING_URI + "/stack";

		List<Stack> getStacks = new ArrayList<Stack>();
		Stack stack1 = new Stack();
		stack1.setPubliclyVisible(false);
		stack1.setTitle("title");
		stack1.setSubject("subject");

		getStacks.add(stack1);

		Stack stack2 = new Stack();
		stack2.setPubliclyVisible(false);
		stack2.setTitle("title");
		stack2.setSubject("subject");

		getStacks.add(stack2);

		when(repo.findAll()).thenReturn(getStacks);

		mvc.perform(get(uri)) // perform get request
				.andDo(print()) // print request sent/response given
				.andExpect(status().isOk()) // expect a 200 status code
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE)) // checks content type is json
				.andExpect(jsonPath("$.length()").value(getStacks.size())) // length of the list matches one above
				.andExpect(jsonPath("$[0].id").value(getStacks.get(0).getId())) // check each column value for the
				.andExpect(jsonPath("$[0].title").value(getStacks.get(0).getTitle()))
				.andExpect(jsonPath("$[0].subject").value(getStacks.get(0).getSubject()))
				.andExpect(jsonPath("$[0].publiclyVisible").value(getStacks.get(0).isPubliclyVisible()))
				
				.andExpect(jsonPath("$[1].id").value(getStacks.get(1).getId())) // check each column value for the
				.andExpect(jsonPath("$[1].title").value(getStacks.get(1).getTitle()))
				.andExpect(jsonPath("$[1].subject").value(getStacks.get(1).getSubject()))
				.andExpect(jsonPath("$[1].publiclyVisible").value(getStacks.get(1).isPubliclyVisible()));

		verify(service, times(1)).getStackById(id);
		verifyNoMoreInteractions(service); // after checking above, service is no longer called

	}
	
	@Test
	void testGetStackById() throws Exception{
		
	}
	@Test
	void testGetStackByIdNotFound() throws Exception{
		
	}
	
}
