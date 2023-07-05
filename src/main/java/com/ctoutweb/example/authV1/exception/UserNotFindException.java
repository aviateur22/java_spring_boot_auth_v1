package com.ctoutweb.example.authV1.exception;

public class UserNotFindException extends RuntimeException {
	public UserNotFindException(String message) {
		super(message);
	}
}
