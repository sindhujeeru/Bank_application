package com.bank.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.app.model.Cards;
import com.bank.app.model.Customer;
import com.bank.app.repository.CardRepository;

@RestController
@RequestMapping("/bank/api/cards")
public class CardsController {
	
	@Autowired
	private CardRepository cardRepo;
	
	@PostMapping
	public List<Cards> getCardDetails(@RequestBody Customer customer) {
		List<Cards> cards = cardRepo.findByCustomerId(customer.getId());
		
		if(cards != null) {
			return cards;
		}else {
			return null;
		}
	}

}
