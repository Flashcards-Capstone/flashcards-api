package com.cognixia.jump.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.model.Stack;

@Repository
public interface StackRepository extends JpaRepository<Stack, Integer>{
	@Query("select s from Stack s where s.subject = ?1")
	public List<Stack> stacksInSameSubject(String subject);
	
}
