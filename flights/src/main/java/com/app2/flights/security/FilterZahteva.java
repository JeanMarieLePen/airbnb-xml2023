package com.app2.flights.security;

import java.io.IOException;
import java.util.Enumeration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.app2.flights.services.GenerateJwt;
import com.app2.flights.services.MyUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterZahteva extends OncePerRequestFilter{

	
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	@Autowired
	private GenerateJwt generateJwt;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		String uri = request.getRequestURI();
		if(!uri.equals("/let/pretraga")) {
			System.out.println(request.getHeaderNames());
			Enumeration<String> headerNames = request.getHeaderNames();
			while(headerNames.hasMoreElements()) {
			  String headerName = (String)headerNames.nextElement();
			  System.out.println("" + headerName);
			  System.out.println("" + request.getHeader(headerName));
			}
//			final int test = request.getIntHeader("AUTHORIZATION");
			final String authorizationHeader = request.getHeader("Authorization");
			String email = null;
			String jwt = null;
			System.out.println("ISPIS AUTHORIZATION HEADER-a U AXIOS HTTP-ZAHTEVU: " + request.getHeader("Authorization"));
			if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
				jwt = authorizationHeader.substring(7);
				email = generateJwt.extractEmail(jwt);
			}
			
			if(email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails userDetails = this.myUserDetailsService.loadUserByUsername(email);
				if(generateJwt.validateToken(jwt, userDetails)) {
					UsernamePasswordAuthenticationToken upwToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
					upwToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(upwToken);
				}
			}
		}
		
		
		filterChain.doFilter(request, response);
	}
}
