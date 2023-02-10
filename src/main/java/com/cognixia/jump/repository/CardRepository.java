package com.cognixia.jump.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.model.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer>{
	@Transactional
	@Modifying
	@Query("UPDATE Card c SET c.question= :question WHERE c.id = :id")
	public int updateQuestion(@Param(value="id") int id, @Param(value="question") String question);
	
	@Transactional
	@Modifying
	@Query("UPDATE Card c SET c.answer= :answer WHERE c.id = :id")
	public int updateAnswer(@Param(value="id") int id, @Param(value="answer") String answer);
	
}
