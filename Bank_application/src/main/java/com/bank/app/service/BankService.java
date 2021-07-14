package com.bank.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.bank.app.model.Customer;
import com.bank.app.model.CustomerSecurity;
import com.bank.app.repository.CustomerRepository;

@Service
public class BankService implements UserDetailsService {
	
	@Autowired
	private CustomerRepository customerRepo;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<Customer> customers  = customerRepo.findByEmail(username);
		System.out.println(customers);
		if(customers.size() == 0) {
			throw new UsernameNotFoundException("Username not found or not registered");
		}
		return new CustomerSecurity(customers.get(0));
	}
	


}
