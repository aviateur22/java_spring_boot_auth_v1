package com.ctoutweb.example.authV1.model;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.ctoutweb.example.authV1.security.Role;

public class User {
	private Long id;
	private String email;
	private String password;
	private List<Role> roles;
	private Date createdAt;
	private Date updatedAt;
	
	public User() {
		
	}
	
	public User(Long id, String email, String password, List<Role> roles, Date createdAt, Date updatedAt) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.roles = roles;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	
	public User(UserBuilder builder) {
		this.id = builder.id;
		this.email = builder.email;
		this.password = builder.password;
		this.roles = builder.roles;
		
		
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the roles
	 */
	public List<Role> getRoles() {
		return roles;
	}
	/**
	 * @param roles the roles to set
	 */
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, password, roles);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && Objects.equals(password, other.password)
				&& Objects.equals(roles, other.roles);
	}
	@Override
	public String toString() {
		return "User [email=" + email + ", password=" + password + ", roles=" + roles + "]";
	}
	

	public static class UserBuilder {
		private Long id;
		private String email;
		private String password;
		private List<Role> roles;
		

		public UserBuilder(long id, String email, String password, List<Role> roles) {
			super();
			this.id = id;
			this.email = email;
			this.password = password;
			this.roles = roles;
		}
		
		
		public UserBuilder id(long id) {
			this.id = id;
			return this;
		}
		
		public UserBuilder email(String email) {
			this.email = email;
			return this;
		}
		
		public UserBuilder roles(List<Role> roles){
			this.roles = roles;
			return this;
		}

		
		public User build() {
			return new User(this);
		}
	}
}
