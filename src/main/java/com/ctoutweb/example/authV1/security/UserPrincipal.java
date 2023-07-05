package com.ctoutweb.example.authV1.security;

import java.util.Collection;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrincipal implements UserDetails {	
	
	private final Long id;
	private final String email;
	private final String password;
	private final Collection<? extends GrantedAuthority> authorities;
	
	
	public UserPrincipal(UserBuilder builder) {	
		this.email = builder.email;
		this.id = builder.id;
		this.password = builder.password;
		this.authorities = builder.authorities;
	}

	public UserPrincipal(Long id, String email, Collection<? extends GrantedAuthority> authorities, String password) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}


	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public static class UserBuilder {
		
		private Long id;
		private String email;
		private String password;
		private Collection<? extends GrantedAuthority> authorities;		
	
		
		public UserBuilder id(long id) {
			this.id = id;
			return this;
		}
		
		public UserBuilder email(String email) {
			this.email = email;
			return this;
		}
		
		public UserBuilder password(String password) {
			this.password = password;
			return this;
		}
		
		public UserBuilder authorities(Collection<? extends GrantedAuthority> authorities){
			this.authorities = authorities;
			return this;
		}

		public UserPrincipal build() {
			return new UserPrincipal(this);
				
		}
		
		
		
	}

}
