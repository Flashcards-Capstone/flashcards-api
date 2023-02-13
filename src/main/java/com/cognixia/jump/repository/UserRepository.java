package com.cognixia.jump.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.model.Stack;
import com.cognixia.jump.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	public Optional<User> findByUsername(String username);
	
	@Query("select * from User u where u.username = ?1 AND u.password = ?2")
	public Optional<User> loggedInUser(String username, String password);
	
	
	//public User findById(Integer user_id);



}