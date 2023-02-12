package com.cognixia.jump.service;

import static org.mockito.Mockito.when;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cognixia.jump.model.User;
import com.cognixia.jump.model.User.Role;
import com.cognixia.jump.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	
	@Mock
	private UserRepository repo;
	
	
	@InjectMocks
	private UserService service;
	
	void testGetUserById() throws Exception{
		
		int id = 1;
		User user = new User();
		user.setUsername("pep");
		user.setPassword("pass");
		user.setEmail("p@p.p");
		user.setRole(Role.ROLE_STUDENT);
		
		when(repo.findById(id) ).thenReturn(Optional.of(user));
		
		User result = service.getUserById(id).get();
		
		
		assertEquals(user, result);
		
		
		
		
		
		
		
	}
	
	

}
