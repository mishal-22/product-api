package com.livares.intern.service;

import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.livares.intern.entity.Token;
import com.livares.intern.entity.Users;
import com.livares.intern.repository.TokenRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	 @Value("${security.jwt.secret-key}")
	    private String secretKey;

	    @Value("${security.jwt.expiration-time}")
	    private long jwtExpiration;

	    @Autowired
	    private TokenRepository tokenRepository;

	    public long getExpirationTime() {
	        return jwtExpiration;
	    }

	    public String extractUsername(String token) {
	        return extractClaim(token, Claims::getSubject);
	    }

	    public boolean isValid(String token, UserDetails user) {
	        String username = extractUsername(token);

	        boolean validToken = tokenRepository.findByToken(token).map(t -> !t.isLoggedOut())
	                .orElse(false);

	        return (username.equals(user.getUsername())) && !isTokenExpired(token) && validToken;
	    }

	    private boolean isTokenExpired(String token) {
	        return extractExpiration(token).before(new Date());
	    }

	    private Date extractExpiration(String token) {
	        return extractClaim(token, Claims::getExpiration);
	    }

	    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
	        Claims claims = extractAllClaims(token);
	        return resolver.apply(claims);
	    }

	    private Claims extractAllClaims(String token) {
	        return Jwts.parser().verifyWith(getSigninKey()).build().parseSignedClaims(token)
	                .getPayload();
	    }

	    public String generateToken(Users user) {
	        String token = Jwts.builder().subject(user.getUsername())
	                .issuedAt(new Date(System.currentTimeMillis()))
	                .expiration(new Date(System.currentTimeMillis() + jwtExpiration))
	                .signWith(getSigninKey()).compact();

	        Token tokenObj = new Token();
	        tokenObj.setToken(token);
	        tokenRepository.save(tokenObj);

	        return token;
	    }

	    private SecretKey getSigninKey() {
	        byte[] keyBytes = Decoders.BASE64URL.decode(secretKey);
	        return Keys.hmacShaKeyFor(keyBytes);
	    }

}
