package com.bank.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bank.app.model.Authority;

@Repository
public interface AuthorityRepository extends CrudRepository<Authority, Long>{
	

}
