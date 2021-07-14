package com.bank.app.config;

import java.util.Collections;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;


@Configuration
public class ProjectSecurityConfig extends WebSecurityConfigurerAdapter{
	
	/**
	 *  /accounts -> secured
	 *  /balance -> secured
	 *  /cards -> secured
	 *  /loans -> secured
	 *  /notices -> unsecured
	 *  /contacts -> unsecured
	 * 
	*/
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().configurationSource(new CorsConfigurationSource() {
			@Override
			public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
				CorsConfiguration config =  new CorsConfiguration();
				config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
				config.setAllowedMethods(Collections.singletonList("*"));
				config.setAllowCredentials(true);
				config.setAllowedHeaders(Collections.singletonList("*"));
				config.setMaxAge(3600L);
				return config;
			}
			
		})
		
		.and()
		.csrf().ignoringAntMatchers("/bank/api/contacts").csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
		.ignoringAntMatchers("/bank/api/notices").csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
		.ignoringAntMatchers("/bank/api/customer").csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
		
		.and()
		.authorizeRequests()
		.antMatchers("/bank/api/accounts").authenticated()
		.antMatchers("/bank/api/balance").authenticated()
		.antMatchers("/bank/api/cards").authenticated()
		.antMatchers("/bank/api/loans").authenticated()
		.antMatchers("/bank/api/notices").permitAll()
		.antMatchers("/bank/api/contacts").permitAll()
		.antMatchers("/bank/api/customer").permitAll()
	
		.and()
		.httpBasic();
		
	}

	/*
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("admin").password("12345").authorities("admin")
		.and()
		.withUser("user").password("12345").authorities("user").and()
		.passwordEncoder(NoOpPasswordEncoder.getInstance());
	}
	*/
	
	/*
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		InMemoryUserDetailsManager userDetailsService = new InMemoryUserDetailsManager();
		UserDetails user = User.withUsername("admin").password("12345").authorities("admin").build();
		UserDetails user1 = User.withUsername("user").password("12345").authorities("user").build();
		
		userDetailsService.createUser(user);
		userDetailsService.createUser(user1);
		
		auth.userDetailsService(userDetailsService);
		
	}
	*/
	
	/*
	@Bean
	public UserDetailsService UserDetailsService(DataSource datasource) {
		return new JdbcUserDetailsManager(datasource);
	}*/
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	

}
