package com.cognixia.jump.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.model.Stack;
import com.cognixia.jump.model.User;



@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	

	public Optional<User> findByUsername(String username);
	
	//@Query("select u.id from User u where u.username = ?1 AND u.password = ?2")
	@Query("select u.id from User u where u.username = ?1")

	//public Integer loggedInUser(String username, String password);
	public Integer loggedInUser(String username);

}