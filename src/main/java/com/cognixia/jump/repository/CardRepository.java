package com.cognixia.jump.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.model.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer>{
	
}
