package com.ctoutweb.example.authV1.model;

import java.util.Objects;

public class RegisterResponse {
	private Long id;
	
	public RegisterResponse(Builder builder) {
		this.id = builder.id;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RegisterResponse other = (RegisterResponse) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "RegisterResponse [id=" + id + "]";
	}
	
	public static class Builder {
		
		private Long id;
		
		public Builder id(long id) {
			this.id = id;
			return this;			
		}
		
		public RegisterResponse build() {
			return new RegisterResponse(this);
		}
	}
	
}
