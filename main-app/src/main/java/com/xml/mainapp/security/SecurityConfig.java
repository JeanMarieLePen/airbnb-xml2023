package com.xml.mainapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.core.filter.TokenFilter;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig{
	
	@Autowired
	private AppConfig appConfig;
	
	@Autowired
	private FilterZahteva filter; 
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.cors().and().csrf().disable().authorizeHttpRequests().requestMatchers("/login/**", "/main-app-websockets/**", "/main-app-websockets/host/**") 
		//, "/actuator/*", "/actuator", "/actuator/prometheus"
		.permitAll()
		.anyRequest().authenticated()
		.and()
		 .httpBasic()
		 .and()
		 .sessionManagement()
		 .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		 .and().authenticationProvider(appConfig.authenticationProvider())
		 .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
}
