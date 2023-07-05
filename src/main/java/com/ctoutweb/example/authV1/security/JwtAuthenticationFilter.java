package com.ctoutweb.example.authV1.security;

import java.io.IOException;
import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	private final JwtDecoder jwtDecoder;
	
	private final JwtToPrincipalConverter jwtToPrincipalConverter;
	
	

	public JwtAuthenticationFilter(JwtDecoder jwtDecoder, JwtToPrincipalConverter jwtToPrincipalConverter) {
		super();
		this.jwtDecoder = jwtDecoder;
		this.jwtToPrincipalConverter = jwtToPrincipalConverter;
	}



	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		extractTokenFromRequest(request)		
			.map(token->jwtDecoder.decode(token))
			.map(decodedJwt->jwtToPrincipalConverter.convert(decodedJwt))
			.map(jwtToPrincipal-> new UserPrincipalAuthenticationToken(jwtToPrincipal))
			.ifPresent(authentication->SecurityContextHolder.getContext().setAuthentication(authentication));		
		
		filterChain.doFilter(request, response);
		
		
	}
	
	/**
	 * Récupération token
	 * @param request HttpServletRequest
	 * @return String
	 */
	private Optional<String> extractTokenFromRequest(HttpServletRequest request){
		var token = request.getHeader("Authorization");
		
		if (!StringUtils.hasText(token) || !token.startsWith("Bearer ")) return Optional.empty();		
		
		return Optional.of(token.substring(7));
	}
	
	

}
