package com.example.cms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.cms.Utility.ResponseStructure;
import com.example.cms.dto.BlogRequest;
import com.example.cms.dto.BlogResponse;
import com.example.cms.service.BlogService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class BlogController {
	
	private BlogService blogService;
	
	@PostMapping("/users/{userId}/Blogs")
	public ResponseEntity<ResponseStructure<BlogResponse>> createBlogByUserId(@RequestParam int userId,@RequestBody BlogRequest blogRequest){
		return blogService.createBlogByUserId(userId,blogRequest);
	}

	@GetMapping("/blogs/{blogId}")
	public ResponseEntity<ResponseStructure<BlogResponse>> findBlogByBoldId(@PathVariable int blogId){
		return blogService.findBlogByBlogId(blogId);
	}
	@PutMapping("/blogs/{blogId}")
	public ResponseEntity<ResponseStructure<BlogResponse>> updateBlogByBlogId(@RequestParam int blogId, @RequestBody BlogRequest blogRequest){
		return blogService.updateBlogByBlogId(blogId,blogRequest);
	}
	
	@GetMapping("/titles/{title}/blogs")
	public boolean titleAvailability(@PathVariable String title){
		return blogService.tileAvailability(title);
	}
}
