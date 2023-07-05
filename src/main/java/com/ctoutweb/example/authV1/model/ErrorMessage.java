package com.ctoutweb.example.authV1.model;

import java.util.Objects;

public class ErrorMessage {
	private String errorMessage;

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public ErrorMessage(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}

	@Override
	public int hashCode() {
		return Objects.hash(errorMessage);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ErrorMessage other = (ErrorMessage) obj;
		return Objects.equals(errorMessage, other.errorMessage);
	}

	@Override
	public String toString() {
		return "ErrorMessage [errorMessage=" + errorMessage + "]";
	}
	
	
	
	
	
}
