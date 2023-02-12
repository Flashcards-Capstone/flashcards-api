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

import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.User;
import com.cognixia.jump.model.User.Role;
import com.cognixia.jump.repository.UserRepository;
import com.cognixia.jump.service.UserService;

@WebMvcTest(UserController.class)
public class UserControllerTest {

	private static final String STARTING_URI = "http://localhost:8080/api";

	@Autowired
	private MockMvc mvc;

	@MockBean
	private UserService service;

	@MockBean
	private UserRepository repo;

	@InjectMocks
	private UserController controller;

	@Test
	void testGetUsers() throws Exception {

		Integer id = 1;
		String uri = STARTING_URI + "/user";

		List<User> getUsers = new ArrayList<User>();
		User user1 = new User();
		user1.setUsername("pep");
		user1.setPassword("pass");
		user1.setEmail("p@p.p");
		user1.setRole(Role.ROLE_STUDENT);

		getUsers.add(user1);

		User user2 = new User();
		user2.setUsername("user2");
		user2.setPassword("pass2");
		user2.setEmail("2@p.2");
		user2.setRole(Role.ROLE_STUDENT);

		getUsers.add(user2);

		when(repo.findAll()).thenReturn(getUsers);

		mvc.perform(get(uri)) // perform get request
				.andDo(print()) // print request sent/response given
				.andExpect(status().isOk()) // expect a 200 status code
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE)) // checks content type is json
				.andExpect(jsonPath("$.length()").value(getUsers.size())) // length of the list matches one above
				.andExpect(jsonPath("$[0].id").value(getUsers.get(0).getId())) // check each column value for the
				.andExpect(jsonPath("$[0].firstName").value(getUsers.get(0).getUsername()))
				.andExpect(jsonPath("$[0].lastName").value(getUsers.get(0).getPassword()))
				.andExpect(jsonPath("$[0].email").value(getUsers.get(0).getEmail()))
				.andExpect(jsonPath("$[0].role").value(getUsers.get(0).getRole())) // string value, won't
				// work if just compare
				.andExpect(jsonPath("$[1].id").value(getUsers.get(1).getId())) // check each column value for the
				.andExpect(jsonPath("$[1].firstName").value(getUsers.get(1).getUsername()))
				.andExpect(jsonPath("$[1].lastName")value(getUsers.get(1).getPassword()))
				.andExpect(jsonPath("$[1].email").value(getUsers.get(1).getEmail()))
				.andExpect(jsonPath("$[1].role").value(getUsers.get(1).getRole())); // the LocalDate object

		verify(service, times(1)).getUserById(id);
		verifyNoMoreInteractions(service); // after checking above, service is no longer called

	}

	@Test
	void testGetUserById() throws Exception {

		Integer id = 1;
		String uri = STARTING_URI + "/user/{id}";

		User user = new User();
		user.setUsername("pep");
		user.setPassword("pass");
		user.setEmail("p@p.p");
		user.setRole(Role.ROLE_STUDENT);

		when(service.getUserById(id).get()).thenReturn(user);

		mvc.perform(get(uri)) // perform get request
				.andDo(print()) // print request sent/response given
				.andExpect(status().isOk()) // expect a 200 status code
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE)) // checks content type is json
				.andExpect(jsonPath("$.id").value(user.getId())) // check each column value for the
				.andExpect(jsonPath("$.firstName").value(user.getUsername()))
				.andExpect(jsonPath("$.lastName").value(user.getPassword()))
				.andExpect(jsonPath("$.email").value(user.getEmail()))
				.andExpect(jsonPath("$.role").value(user.getRole())); // string value, won't

		verify(service, times(1)).getUserById(id);
		verifyNoMoreInteractions(service);

	}

	@Test
	void testGetUserByIdNotFound() throws Exception {

		int id = 1;
		String uri = STARTING_URI + "/user/{id}";

		when(service.getUserById(id).get()).thenThrow(new ResourceNotFoundException("User", id));
		mvc.perform(get(uri, id)).andDo(print()).andExpect(status().isNotFound());

		verify(service, times(1)).getUserById(id);
		verifyNoMoreInteractions(service);
	}
	
//	@Test
//	void testCreateUser() throws Exception{
//		String uri = STARTING_URI + "/user";
//
//		User user = new User();
//		user.setId(1);
//		user.setUsername("pep");
//		user.setPassword("pass");
//		user.setEmail("p@p.p");
//		user.setRole(Role.ROLE_STUDENT);
//		when(service.)
//	}
//	@Test
//	void testUpdateUser() throws Exception{
//		
//	}
//	@Test
//	void testUpdateUserNotFound() throws Exception{
//		
//	}
//	@Test
//	void testDeleteUser() throws Exception{
//		
//	}
//	
//	@Test
//	void testDeleteUserNotFound() throws Exception{
//		
//	}

}
