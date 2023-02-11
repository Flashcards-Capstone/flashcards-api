package com.cognixia.jump.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.jump.model.Stack;
import com.cognixia.jump.repository.StackRepository;

@Service
public class StackService {
	@Autowired
	StackRepository repo;
	
	
	public Optional<Stack> getStackById(int id){
		
		return repo.findById(id);
		
	}
	
	
	
	public Optional<Stack> updateVisible(int id, Boolean publiclyVisible){
		int count = repo.updateVisibility(id, publiclyVisible);
		if(count > 0) {
			return getStackById(id);
		}
		else {
			return Optional.empty();
		}
				
	}
	
	public Optional<Stack> updateTitle(int id, String title){
		int count = repo.updateTitle(id, title);
		if(count > 0) {
			return getStackById(id);
		}
		else {
			return Optional.empty();
		}
		
	}
	public Optional<Stack> updateSubject(int id, String subject){
		int count = repo.updateSubject(id, subject);
		if(count > 0) {
			return getStackById(id);
		}
		else {
			return Optional.empty();
		}
		
	}

}
