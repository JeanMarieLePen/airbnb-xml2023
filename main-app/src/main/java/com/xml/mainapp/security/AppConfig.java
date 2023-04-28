package com.xml.mainapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.xml.mainapp.services.MyUserDetailsService;


@Configuration
public class AppConfig {
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	
	@Bean
	public UserDetailsService userDetailsService() {
		return myUserDetailsService; 
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
	
	
	//AuthenticationProvider je objekat koji pristupa UserDetails-u,
	//vrsi enkodiranje lozinke itd.
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		//moze i ovako, ne mora bean
//		authProvider.setPasswordEncoder(new BCryptPasswordEncoder());
		
		//preko bean-a passwordEncoder
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
