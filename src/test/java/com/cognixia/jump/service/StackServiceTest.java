package com.cognixia.jump.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cognixia.jump.model.Stack;
import com.cognixia.jump.repository.StackRepository;

@ExtendWith(MockitoExtension.class)
public class StackServiceTest {

	@Mock
	private StackRepository repo;

	@InjectMocks
	private StackService service;

	@Test
	void testGetStackById() throws Exception {

		int id = 1;
		Stack stack = new Stack();
		stack.setPubliclyVisible(false);
		stack.setSubject("Subject");
		stack.setTitle("title");

		when(repo.findById(id)).thenReturn(Optional.of(stack));

		Stack result = service.getStackById(id).get();

		assertEquals(stack, result);

	}

	@Test
	void testUpdateVisible() throws Exception {

		int id = 1;
		boolean vis = true;
		Stack stack = new Stack();
		stack.setPubliclyVisible(false);
		stack.setSubject("Subject");
		stack.setTitle("title");

		when(service.updateVisible(id, vis)).thenReturn(null);
		when(repo.findById(id)).thenReturn(Optional.of(stack));

		Stack updated = service.updateVisible(id, vis).get();

		assertEquals(stack, updated);

	}

	@Test
	void testUpdateTitle() throws Exception {
		int id = 1;
		String title = "newtitle";
		Stack stack = new Stack();
		stack.setPubliclyVisible(false);
		stack.setSubject("Subject");
		stack.setTitle("title");

		when(service.updateTitle(id, title)).thenReturn(null);
		when(repo.findById(id)).thenReturn(Optional.of(stack));

		Stack updated = service.updateTitle(id, title).get();

		assertEquals(stack, updated);
	}

	@Test
	void testUpdateSubject() throws Exception {
		int id = 1;
		String subject = "newsubject";
		Stack stack = new Stack();
		stack.setPubliclyVisible(false);
		stack.setSubject("Subject");
		stack.setTitle("title");

		when(service.updateSubject(id, subject)).thenReturn(null);
		when(repo.findById(id)).thenReturn(Optional.of(stack));

		Stack updated = service.updateSubject(id, subject).get();

		assertEquals(stack, updated);
	}

}
