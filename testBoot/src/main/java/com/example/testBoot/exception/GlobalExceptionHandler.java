package com.example.testBoot.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResponseNotFoundException.class)
	public ResponseEntity<?> handleResponseNotFoundException(ResponseNotFoundException exception,WebRequest request){
		ErrorMessage msg = new ErrorMessage(new Date(),exception.getMessage());
		return new ResponseEntity(msg,HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> GeneralException(ResponseNotFoundException exception,WebRequest request){
		ErrorMessage msg = new ErrorMessage(new Date(),exception.getMessage());
		return new ResponseEntity(msg,HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
