package com.example.cms.serviceImpl;

import java.util.Arrays;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.cms.Entity.Blog;
import com.example.cms.Entity.User;
import com.example.cms.Exception.BlogNotFoundById;
import com.example.cms.Exception.TitleNotAvalibleEXception;
import com.example.cms.Exception.TopicNotSpecifiedException;
import com.example.cms.Exception.UserNotFoundByIdException;
import com.example.cms.Utility.ResponseStructure;
import com.example.cms.dtoReponse.BlogResponse;
import com.example.cms.dtoRequest.BlogRequest;
import com.example.cms.repository.BlogRepository;
import com.example.cms.repository.UserRepository;
import com.example.cms.service.BlogService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BlogServiceImpl implements BlogService{
	
	private BlogRepository blogRepository;
	
	 
	private UserRepository userRepository;
	
	private ResponseStructure<BlogResponse> responseStructure;
	

	@Override
	public ResponseEntity<ResponseStructure<BlogResponse>> createBlogByUserId(int userId,BlogRequest blogRequest) {
		
		return userRepository.findById(userId).map(user->{
			if(blogRepository.existsByTitle(blogRequest.getTitle()))
					throw new TitleNotAvalibleEXception("failed to create blog");
			
			if(blogRequest.getTopics().size()<1)
				throw new TopicNotSpecifiedException("failed to create blog");
			
			Blog blog = mapToBlogEntity(blogRequest, new Blog());
			 blog.setUser((User) Arrays.asList(user));
			 blogRepository.save(blog);		
			 
			 return ResponseEntity.ok(responseStructure.setStatus(HttpStatus.OK.value())
					 .setMessage("blog created")
					 .setData(mapToBlogResponse(blog))
					 );
		}).orElseThrow(()->new UserNotFoundByIdException("Failed to create blog"));
	}
	
	@Override
	public ResponseEntity<ResponseStructure<BlogResponse>> findBlogByBlogId(int blogId) {
		
		return blogRepository.findById(blogId).map(blog->{
			return ResponseEntity.ok(responseStructure.setStatus(HttpStatus.FOUND.value())
					.setMessage("Blog found By BlodId").setData(mapToBlogResponse(blogRepository.save(blog))));
		}).orElseThrow(()->new BlogNotFoundById("blog not found"));
	}
	
	@Override
	public ResponseEntity<ResponseStructure<BlogResponse>> updateBlogByBlogId(int blogId, BlogRequest blogRequest) {
		return blogRepository.findById(blogId).map(blog->{
			if(blogRequest.getTopics().size() < 1)
				throw new TopicNotSpecifiedException("failed to update blog");
			
			Blog blog1=mapToBlogEntity(blogRequest, blog);
			blog1.setUser(blog.getUser());
			
			blogRepository.save(blog1);
			
			return ResponseEntity.ok(responseStructure.setStatus(HttpStatus.OK.value())
					.setMessage("Blog update Sucessfully")
					.setData(mapToBlogResponse(blog1)));
		}).orElseThrow(()->new BlogNotFoundById("blog is not updated"));
	}
	
	
	@Override
	public boolean tileAvailability(String title) {
		return blogRepository.existsByTitle(title);
	}
	
	
	private BlogResponse mapToBlogResponse(Blog blog)  {
		
		return new BlogResponse(blog.getBlogId(),
		blog.getTitle(),
		blog.getTopics(),
		blog.getSummary());
	}
	
	private Blog mapToBlogEntity(BlogRequest blogRequest,Blog blog) {
		blog.setTitle(blogRequest.getTitle());
		blog.setTopics(blogRequest.getTopics());
		blog.setSummary(blogRequest.getSummary());
		
		return blog;
	}

}
