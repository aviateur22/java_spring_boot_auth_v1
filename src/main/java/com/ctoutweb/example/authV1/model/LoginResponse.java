package com.ctoutweb.example.authV1.model;

import java.util.Objects;

public class LoginResponse {
	
	private final String accessToken;

	public LoginResponse(String accessToken) {
		super();
		this.accessToken = accessToken;
	}	
	
	public LoginResponse(Builder builder) {
		super();
		this.accessToken = builder.accessToken;
	}	
	
	
	/**
	 * @return the accessToken
	 */
	public String getAccessToken() {
		return accessToken;
	}

	@Override
	public int hashCode() {
		return Objects.hash(accessToken);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoginResponse other = (LoginResponse) obj;
		return Objects.equals(accessToken, other.accessToken);
	}

	@Override
	public String toString() {
		return "LoginResponse [accessToken=" + accessToken + "]";
	}
	
	public static class Builder {
		private String accessToken;		
		

		public Builder accessToken(String token) {
			this.accessToken = token;
			return this;
		}
		
		public LoginResponse build() {
			return new LoginResponse(this);
		}
	}


	
	

}
