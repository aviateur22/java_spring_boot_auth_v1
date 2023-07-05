package com.ctoutweb.example.authV1.model;

import java.util.Objects;

public class RegisterRequest {

	private String email;
	private String password;
	private String cfmPassword;
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
	 * @return the cfmPassword
	 */
	public String getCfmPassword() {
		return cfmPassword;
	}
	/**
	 * @param cfmPassword the cfmPassword to set
	 */
	public void setCfmPassword(String cfmPassword) {
		this.cfmPassword = cfmPassword;
	}
	@Override
	public String toString() {
		return "RegisterRequest [email=" + email + ", password=" + password + ", cfmPassword=" + cfmPassword + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(cfmPassword, email, password);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RegisterRequest other = (RegisterRequest) obj;
		return Objects.equals(cfmPassword, other.cfmPassword) && Objects.equals(email, other.email)
				&& Objects.equals(password, other.password);
	}
	
	public static class Builder {
		
	}
}
