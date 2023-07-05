package com.ctoutweb.example.authV1.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctoutweb.example.authV1.model.LoginRequest;
import com.ctoutweb.example.authV1.model.LoginResponse;
import com.ctoutweb.example.authV1.model.RegisterRequest;
import com.ctoutweb.example.authV1.model.RegisterResponse;
import com.ctoutweb.example.authV1.security.UserPrincipal;
import com.ctoutweb.example.authV1.service.AuthService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
	
	private final AuthService authService;

	
	public AuthController(AuthService authService) {
		super();		
		this.authService = authService;

	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {	
		
		
		
		LoginResponse response = authService.login(request);
		return new ResponseEntity<>(response, HttpStatus.OK);		

	}
	
	@PostMapping("/register")
	public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request) {	
		
		RegisterResponse response = authService.register(request);		
		return new ResponseEntity<>(response, HttpStatus.CREATED);		

	}
		
}


