package com.cognixia.jump.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
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
	void testGetStacks() throws Exception {

		List<Stack> getStacks = new ArrayList<Stack>();
		Stack stack1 = new Stack();
		stack1.setId(1);
		stack1.setPubliclyVisible(false);
		stack1.setTitle("title");
		stack1.setSubject("subject");

		getStacks.add(stack1);

		Stack stack2 = new Stack();
		stack1.setId(2);
		stack2.setPubliclyVisible(false);
		stack2.setTitle("title");
		stack2.setSubject("subject");

		getStacks.add(stack2);

		when(service.getStacks()).thenReturn(getStacks);

		List<Stack> result = service.getStacks();

		for (int i = 0; i < getStacks.size(); i++) {

			if (getStacks.get(i).equals(result.get(i))) {
				System.out.println("Equal");
			} else {

				// if there is no assertion used, then the test will always succeed. So you
				// can use fail() to make the test fail when you need to
				fail();
			}
		}
	}

	@Test
	void testGetStackById() throws Exception {

		int id = 1;
		Stack stack = new Stack();
		stack.setId(id);
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
		
		Stack stack = new Stack();
		stack.setId(id);
		stack.setPubliclyVisible(false);
		stack.setSubject("Subject");
		stack.setTitle("title");

		
		
		boolean vis = true;


		when(service.updateVisible(id, vis)).thenReturn(Optional.of(stack));
		when(repo.findById(id)).thenReturn(Optional.of(stack));

		Stack updated = service.updateVisible(id, vis).get();

		assertEquals(stack, updated);

	}

	@Test
	void testUpdateTitle() throws Exception {
		int id = 1;
		String title = "newtitle";
		Stack stack = new Stack();
		stack.setId(id);
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
