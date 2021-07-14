package com.bank.app.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.bank.app.model.Cards;

public interface CardRepository extends CrudRepository<Cards, Long> {
	
	List<Cards> findByCustomerId(long customerId);

}
