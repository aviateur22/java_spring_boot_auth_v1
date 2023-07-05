package com.ctoutweb.example.authV1.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ctoutweb.example.authV1.security.JwtAuthenticationFilter;
import com.ctoutweb.example.authV1.security.PrincipalUserDetailService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	
	private final PrincipalUserDetailService principalUserDetailService;

    public WebSecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter, PrincipalUserDetailService principalUserDetailService) {
		super();
		this.jwtAuthenticationFilter = jwtAuthenticationFilter;
		this.principalUserDetailService = principalUserDetailService;
	}

	@Bean
    SecurityFilterChain appSecurity(HttpSecurity http) throws Exception {
		
		http
			.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		http
       		.csrf(csrf-> csrf
       			.disable())
       		.sessionManagement(session-> session
       			.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
       	
       		.formLogin(form->form
       			.disable())       
       		.authorizeHttpRequests(auth->auth
       			.requestMatchers("api/v1/").permitAll()
       			.requestMatchers("/api/v1/auth/**").permitAll()
       			.anyRequest().authenticated());
       
       return http.build();
       
       	
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
		return http.getSharedObject(AuthenticationManagerBuilder.class)
				.userDetailsService(principalUserDetailService)
				.passwordEncoder(passwordEncoder())
				.and().build();
	}
}
