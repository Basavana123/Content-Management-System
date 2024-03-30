package com.example.cms.Exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TopicNotSpecifiedException extends RuntimeException {

	private String message;

	@Override
	public String getMessage() {

		return message;
	}

}
