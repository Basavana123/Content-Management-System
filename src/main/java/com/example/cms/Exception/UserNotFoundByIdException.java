package com.example.cms.Exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserNotFoundByIdException extends RuntimeException{
	
	private String message;
	
	@Override
	public String getMessage() {
		
		return message;
	}

}
