package com.bank.app.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.bank.app.model.Authority;
import com.bank.app.model.Customer;
import com.bank.app.repository.CustomerRepository;

public class BankUserPwdAuthProvider implements AuthenticationProvider {

	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String pwd = authentication.getCredentials().toString();
		
		List<Customer> customer = customerRepo.findByEmail(username);
		
		if(customer.size() > 0) {
			if(passwordEncoder.matches(pwd, customer.get(0).getPwd())) {
				//List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
				//authorities.add(new SimpleGrantedAuthority(customer.get(0).getRole()));
				return new UsernamePasswordAuthenticationToken(username, pwd, getGrantedAuthorities(customer.get(0).getAuthorities()));
			}else {
				throw new BadCredentialsException("invalid credentials");
			}
		}else {
			throw new BadCredentialsException("No user registered with this details");
		}
	}
	
	private List<GrantedAuthority> getGrantedAuthorities(Set<Authority> authorities){
		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		
		for(Authority authority:authorities) {
			grantedAuthorities.add(new SimpleGrantedAuthority(authority.getName()));
			System.out.println(authority.getName());
		}
		
		return grantedAuthorities;
		
	}

	@Override
	public boolean supports(Class<?> authenticationType) {
		return authenticationType.equals(UsernamePasswordAuthenticationToken.class);
	}

}
