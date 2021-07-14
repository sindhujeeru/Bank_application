package com.bank.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bank.app.model.Contact;



@Repository
public interface ContactRepository extends CrudRepository<Contact, Long> {
	
	
}
