package com.bank.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.app.model.Accounts;
import com.bank.app.model.Customer;
import com.bank.app.repository.AccountsRepository;

@RestController
@RequestMapping("/bank/api/accounts")
public class AccountController {
	
	@Autowired
	private AccountsRepository accountsRepository;
	
	@PostMapping
	public Accounts getAccountDetails(@RequestBody Customer customer) {
		Accounts accounts = accountsRepository.findByCustomerId(customer.getId());
		if (accounts != null ) {
			return accounts;
		}else {
			return null;
		}
	}

}
