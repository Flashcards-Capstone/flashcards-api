package com.cognixia.jump.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.model.Stack;

@Repository
public interface StackRepository extends JpaRepository<Stack, Integer>{

//	@Query("select s from Stack s where s.user_id = ?1")
//	public List<Stack> stacksWithSameUserId(Integer user_id);
	
	@Query("select s from Stack s where s.subject = ?1")
	public List<Stack> stacksInSameSubject(String subject);
	
	@Transactional
	@Modifying
	@Query("UPDATE Stack s SET s.publiclyVisible= :publiclyVisible WHERE s.id = :id")
	public int updateVisibility(@Param(value="id") int id, @Param(value="publiclyVisible") Boolean publiclyVisible);
	
	@Transactional
	@Modifying
	@Query("UPDATE Stack s SET s.title= :title WHERE s.id = :id")
	public int updateTitle(@Param(value="id") int id, @Param(value="title") String title);
	
	@Transactional
	@Modifying
	@Query("UPDATE Stack s SET s.subject= :subject WHERE s.id = :id")
	public int updateSubject(@Param(value="id") int id, @Param(value="subject") String subject);
}
