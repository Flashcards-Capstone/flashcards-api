package com.cognixia.jump.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//Advise controller on what to do when certain exceptions are thrown
@ControllerAdvice
public class GlobalExceptionHandler {
	//ex: represents the exception obj that is thrown by program
	//request: copy of request sent to spring
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> methodArgumentNotValid(){
		return null;
		
	}
}
