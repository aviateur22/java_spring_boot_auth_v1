package com.ctoutweb.example.authV1.security;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ctoutweb.example.authV1.model.User;

@Component
public class JwtIssuer {
	
	private final JwtProperties jwtProperties;
	
	
	
	public JwtIssuer(JwtProperties jwtProperties) {
		super();
		this.jwtProperties = jwtProperties;
	}



	public String issue(UserPrincipal user) {
	
		return JWT.create()
				.withSubject(user.getEmail())
				.withIssuer(jwtProperties.getIssuer())
				.withJWTId(UUID.randomUUID().toString())
				.withExpiresAt(Instant.now().plus(Duration.ofHours(3)))
				.withClaim("authorities", user.getAuthorities().stream().map(x->x.toString()).collect(Collectors.toList()))
				.withClaim("id", user.getId())
				.sign(Algorithm.HMAC256(jwtProperties.getSecretKey()));
	}
	
}

