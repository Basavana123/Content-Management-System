package com.example.cms.service;

import org.springframework.http.ResponseEntity;

import com.example.cms.Utility.ResponseStructure;
import com.example.cms.dto.BlogRequest;
import com.example.cms.dto.BlogResponse;

public interface BlogService {


	ResponseEntity<ResponseStructure<BlogResponse>> createBlogByUserId(int userId,BlogRequest blogRequest);

	ResponseEntity<ResponseStructure<BlogResponse>> findBlogByBlogId(int blogId);

	ResponseEntity<ResponseStructure<BlogResponse>> updateBlogByBlogId(int blogId, BlogRequest blogRequest);

	boolean tileAvailability(String title);
	

}
