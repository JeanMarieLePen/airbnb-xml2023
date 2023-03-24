package com.app2.flights.services;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.app2.flights.model.user.Korisnik;
import com.app2.flights.repositories.KorisnikRep;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class GenerateJwt {

	@Autowired
	private KorisnikRep korisnikRep;
	
	private String SECRET_KEY = "6D597133743677397A24432646294A404E635266546A576E5A7234753778214125442A472D4B6150645367566B58703273357638792F423F4528482B4D6251655468576D5A7133743677397A24432646294A404E635266556A586E327235753778214125442A472D4B6150645367566B59703373367639792F423F4528482B4D6251655468576D5A7134743777217A25432646294A404E635266556A586E3272357538782F413F4428472D4B6150645367566B5970337336763979244226452948404D6251655468576D5A7134743777217A25432A462D4A614E645266556A586E3272357538782F413F4428472B4B6250655368566B5970337336763979244226452948404D635166546A576E5A7134743777217A25432A462D4A614E645267556B58703273357538782F413F4428472B4B6250655368566D5971337436773979244226452948404D635166546A576E5A7234753778214125432A462D4A614E645267556B58703273357638792F423F4528472B4B6250655368566D597133743677397A24432646294A404D635166546A576E5A7234753778214125442A472D4B6150645267556B58703273357638792F423F4528482B4D6251655468566D597133743677397A24432646294A404E635266556A586E327234753778214125442A472D4B6150645367566B59703373367638792F423F4528482B4D6251655468";
	
	public String extractEmail(String token) {
		return extractClaim(token, Claims::getSubject);
	}
	
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}
	
	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	
	private Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}
	
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		List<SimpleGrantedAuthority> tempLista = (List<SimpleGrantedAuthority>) userDetails.getAuthorities();
		System.out.println("KREIRANJE TOKENA: ULOGE -> " + userDetails.getAuthorities());
		try {
			Korisnik k = this.korisnikRep.findByEmail(userDetails.getUsername());
			if(k != null) {
				claims.put("userid", k.getId());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		try {
			if(tempLista.size() > 0) {
				claims.put("authority", tempLista.get(0).getAuthority());
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return createToken(claims, userDetails.getUsername());
	}
	
	private String createToken(Map<String, Object> claims, String subject) {
		System.out.println("KREIRANJE TOKENA");
		String token = Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
				.signWith(getSignInKey(), SignatureAlgorithm.HS256).compact();
		return token;
	}
	
	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token)
				.getBody();
	}
	
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String email = this.extractEmail(token);
		return (email.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
	
	private Key getSignInKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
		//hmacSha algoritam
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
