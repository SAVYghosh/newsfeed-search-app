package com.news.controller;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.news.entity.ErrorResponse;

@RestController
public class GlobalErrorHandlerController {
	
	Logger LOGGER = LoggerFactory.getLogger(GlobalErrorHandlerController.class);

	@GetMapping("/test")
	public void errorHandleTest(){
		LOGGER.debug("Inside error handle Test");
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleError(Exception exception){
		LOGGER.info("{}",exception);
		ErrorResponse error = new ErrorResponse();
		String errorMessage="";
		error.setTimestamp(ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT));
		if(exception instanceof MethodArgumentNotValidException){
			Iterator<FieldError> iterator=((MethodArgumentNotValidException) exception).getBindingResult().getFieldErrors().stream().iterator();
			while(iterator.hasNext()){
				errorMessage+=iterator.next().getDefaultMessage();		
			}
			error.setReasonCode(HttpStatus.BAD_REQUEST.value());
			error.setErrorMessage(errorMessage);
			return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
		}
		if(exception instanceof AuthenticationException){
			error.setReasonCode(HttpStatus.BAD_REQUEST.value());
			error.setErrorMessage("Wrong Credential,Contact Admin");
			return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
		}
		if(exception instanceof BadCredentialsException){
			error.setReasonCode(HttpStatus.BAD_REQUEST.value());
			error.setErrorMessage("Wrong Credential,Contact Admin");
			return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
		}
		
		error.setErrorMessage("System error,Contact Admin");
		error.setReasonCode(HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS.value());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
	} 
}

