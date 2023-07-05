package com.ctoutweb.example.authV1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.ctoutweb.example.authV1.model.ErrorMessage;

@ControllerAdvice
public class ExceptionHandler {

	@org.springframework.web.bind.annotation.ExceptionHandler(value = {EmailAlreadyExistException.class})
	public ResponseEntity<ErrorMessage> emailAlreadyExistException(EmailAlreadyExistException ex, WebRequest reques){
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage());
		 return new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(value = {UserNotFindException.class})
	public ResponseEntity<ErrorMessage> userNotFindException(UserNotFindException ex, WebRequest reques){
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage());
		 return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
	}
}
