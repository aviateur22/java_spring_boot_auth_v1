package com.ctoutweb.example.authV1.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ctoutweb.example.authV1.exception.UserNotFindException;
import com.ctoutweb.example.authV1.model.User;
import com.ctoutweb.example.authV1.repository.UserRepositoryImpl;

@Service
public class PrincipalUserDetailService implements UserDetailsService {
	
	private final UserRepositoryImpl userRepository;

	public PrincipalUserDetailService(UserRepositoryImpl userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println(username);
		User user = userRepository.findUserByEmail(username).orElseThrow(()-> new UserNotFindException("User email or password not find"));
		
		if(user.getRoles() == null || user.getRoles().isEmpty()) {
			user.setRoles(List.of(Role.ADMIN, Role.USER));
		}
		
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		
		for(Role role: user.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.name()));
		}		
		
		return new UserPrincipal.UserBuilder()
				.id(user.getId())
				.email(user.getEmail())
				.password(user.getPassword())
				.authorities(authorities)
				.build();
	}

}
