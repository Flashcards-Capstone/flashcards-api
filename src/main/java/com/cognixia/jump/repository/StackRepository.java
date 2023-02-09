package com.cognixia.jump.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.model.Stack;

@Repository
public interface StackRepository extends JpaRepository<Stack, Integer>{
	
}
