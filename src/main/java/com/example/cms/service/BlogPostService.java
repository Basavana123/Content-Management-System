package com.example.cms.service;

import org.springframework.http.ResponseEntity;

import com.example.cms.Utility.ResponseStructure;
import com.example.cms.dtoReponse.BlogPostResponse;
import com.example.cms.dtoRequest.BlogPostRequest;

public interface BlogPostService {

	ResponseEntity<ResponseStructure<BlogPostResponse>> createDraft(int blogId, BlogPostRequest blogPost);

	ResponseEntity<ResponseStructure<BlogPostResponse>> updateDraft(int blogPostId, BlogPostRequest blogPostRequest);

	ResponseEntity<ResponseStructure<BlogPostResponse>> deleteDraft(int blogPostId);
	
	

}
