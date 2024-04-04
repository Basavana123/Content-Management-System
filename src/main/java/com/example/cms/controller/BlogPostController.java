package com.example.cms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.cms.Utility.ResponseStructure;
import com.example.cms.dtoReponse.BlogPostResponse;
import com.example.cms.dtoRequest.BlogPostRequest;
import com.example.cms.service.BlogPostService;

import lombok.AllArgsConstructor;


@AllArgsConstructor
@RestController
public class BlogPostController {
	
	private BlogPostService blogPostService;
	
	@PostMapping("/blogs/{blogId}/blog-posts")
	public ResponseEntity<ResponseStructure<BlogPostResponse>> createDraft(@RequestParam int blogId,@RequestBody BlogPostRequest blogPost){
		return blogPostService.createDraft(blogId,blogPost);
	}
	
	@PutMapping("/blog-posts/{blogPostId}")
	public ResponseEntity<ResponseStructure<BlogPostResponse>> updateDraft(@RequestParam int blogPostId,@RequestBody BlogPostRequest blogPostRequest){
		return blogPostService.updateDraft(blogPostId,blogPostRequest);
	}
	
	@DeleteMapping("/blog-posts/{blogPostId}")
	public ResponseEntity<ResponseStructure<BlogPostResponse>> deleteDraft(@RequestParam int blogPostId ){
		return blogPostService.deleteDraft(blogPostId);
	}
	

	

}
