package com.bank.app.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.app.model.Authority;
import com.bank.app.model.Customer;
import com.bank.app.repository.AuthorityRepository;
import com.bank.app.repository.CustomerRepository;

@RestController
@RequestMapping("/bank/api/customer")
public class CustomerController {
	
	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private AuthorityRepository authRepo;
	
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@PostMapping
	public ResponseEntity<Customer> addCustomer(@Valid @RequestBody Customer customer, BindingResult result){
		
		Authority authority = new Authority();
		
		Set<Authority> authorities = new HashSet<Authority>();
		
		Customer newCustomer = new Customer();
		String password =  customer.getPwd();
		
		
		long millis=System.currentTimeMillis();  
	    java.sql.Date date=new java.sql.Date(millis);
		
	    newCustomer.setCreateDt(date.toString());
		newCustomer.setRole("user");
		newCustomer.setEmail(customer.getEmail());
		newCustomer.setPwd(passwordEncoder.encode(password));
		newCustomer.setName(customer.getName());
		newCustomer.setMobileNumber(customer.getMobileNumber());
		
		authority.setCustomer(newCustomer);
		authority.setName("ROLE_USER");
		authorities.add(authority);
		
		newCustomer.setAuthorities(authorities);
		
		authRepo.save(authority);
		Customer cusDb = customerRepo.save(newCustomer);
		return ResponseEntity.status(HttpStatus.CREATED).body(cusDb);
	}

}
