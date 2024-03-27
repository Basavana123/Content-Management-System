package com.example.cms.controller;

import org.springframework.web.bind.annotation.GetMapping;


import org.springframework.web.bind.annotation.RestController;


import com.example.cms.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class UserController {
	
	@GetMapping("/users/test")
	public String test() {
		return "hello from cms";
	}
	
  private UserService userService;
	
	
	

}
