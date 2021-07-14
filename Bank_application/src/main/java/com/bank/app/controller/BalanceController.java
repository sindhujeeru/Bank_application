package com.bank.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.app.model.AccountTransactions;
import com.bank.app.model.Customer;
import com.bank.app.repository.AccountTransactionsRepository;

@RestController
@RequestMapping("/bank/api/balance")
public class BalanceController {
	
	@Autowired
	private AccountTransactionsRepository accountTransactionsRepository;
	
	@PostMapping
	public List<AccountTransactions> getBalanceDetails(@RequestBody Customer customer) {
		List<AccountTransactions> accountTransactions = accountTransactionsRepository.
				findByCustomerIdOrderByTransactionDtDesc(customer.getId());
		if (accountTransactions != null ) {
			return accountTransactions;
		}else {
			return null;
		}
	}

}
