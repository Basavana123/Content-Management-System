package com.example.cms.Utility;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.cms.Exception.UserAllReadyExistByEmailException;

import lombok.AllArgsConstructor;

@RestControllerAdvice
@AllArgsConstructor
public class ApplicationExceptionHandler {

	private ErrorStructure<String> structure;
	
	private ResponseEntity<ErrorStructure<String>> errorResponse(HttpStatus status, String message, String rootCause)
	{
		return new ResponseEntity<ErrorStructure<String>>(structure
				.setStatus(status.value())
				.setMessage(message)
				.setRootCause(rootCause), status);
	}
	
	
	public ResponseEntity<ErrorStructure<String>> handlerUserAllreadyExistByEmail(UserAllReadyExistByEmailException ex){
		return errorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), "User Already exists with the given email Id");
	}
}
