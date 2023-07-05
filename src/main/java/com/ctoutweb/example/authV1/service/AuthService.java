package com.ctoutweb.example.authV1.service;


import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ctoutweb.example.authV1.exception.EmailAlreadyExistException;

import com.ctoutweb.example.authV1.model.LoginRequest;
import com.ctoutweb.example.authV1.model.LoginResponse;
import com.ctoutweb.example.authV1.model.RegisterRequest;
import com.ctoutweb.example.authV1.model.RegisterResponse;
import com.ctoutweb.example.authV1.model.User;
import com.ctoutweb.example.authV1.repository.UserRepositoryImpl;
import com.ctoutweb.example.authV1.security.JwtIssuer;
import com.ctoutweb.example.authV1.security.UserPrincipal;

@Service
public class AuthService {
	
	private final UserRepositoryImpl userRepository;
	private final JwtIssuer jwtIssuer;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	
	
	public AuthService(UserRepositoryImpl userRepository, JwtIssuer jwtIssuer, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
		super();
		this.userRepository = userRepository;
		this.jwtIssuer = jwtIssuer;
		this.passwordEncoder = passwordEncoder;
		this.authenticationManager = authenticationManager;
	}


	public RegisterResponse register(RegisterRequest request) {
		Optional<User> findUserWithSameEmail = userRepository.findUserByEmail(request.getEmail());
		
		findUserWithSameEmail.ifPresent(user->{
			throw new EmailAlreadyExistException("User email already exist");				
		});
		
		RegisterRequest EncodedRequest = new RegisterRequest();
	
		
		EncodedRequest.setEmail(request.getEmail());
		EncodedRequest.setPassword(passwordEncoder.encode(request.getPassword()));
		
		Long id = userRepository.saveUser(EncodedRequest);
		return new RegisterResponse.Builder().id(id).build(); 
	}
	
	public LoginResponse login(LoginRequest request) {
		
		var authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		UserPrincipal principal =(UserPrincipal) authentication.getPrincipal();	
		
		//User user = userRepository.findUserByEmail(request.getEmail()).orElseThrow(()-> new UserNotFindException("User email or password not find"));
				
//		if(user.getRoles() == null || user.getRoles().isEmpty()) {
//			user.setRoles(List.of(Role.USER, Role.ADMIN));
//		}
		var token = jwtIssuer.issue(principal);			
									
		return  new LoginResponse.Builder().accessToken(token).build();
	}
}
