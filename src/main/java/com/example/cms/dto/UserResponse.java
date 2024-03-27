package com.example.cms.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class UserResponse {
	
	
	
	private int userId;
	private String userName;
	private String email;
	private LocalDate createdAt;
	private LocalDate lastModifiedAt;
	
//	public UserResponse(int userId, String userName, String email, LocalDate createdAt,
//			LocalDate lastmodifiedAt) {
//		this.userId=userId;
//		this.UserName=userName;
//		this.email=email;
//		this.createdAt=createdAt;
//		this.lastModifiedAt=lastModifiedAt;
//		
//	}
	
	
}
