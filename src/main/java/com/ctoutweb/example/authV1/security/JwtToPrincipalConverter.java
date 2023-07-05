package com.ctoutweb.example.authV1.security;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.auth0.jwt.interfaces.DecodedJWT;

@Component
public class JwtToPrincipalConverter {

	public UserPrincipal convert(DecodedJWT jwt) {
		return new UserPrincipal.UserBuilder()
				.id(jwt.getClaim("id").asLong())
				.email(jwt.getSubject())
				.authorities(extractAuthorityFromClaim(jwt))
				.build();
				
	}
	
	private List<SimpleGrantedAuthority> extractAuthorityFromClaim(DecodedJWT jwt){
		var claim = jwt.getClaim("authorities");		
		
		if(claim.isNull() || claim.isMissing()) return List.of();
		
		return claim.asList(SimpleGrantedAuthority.class);
	}
}
