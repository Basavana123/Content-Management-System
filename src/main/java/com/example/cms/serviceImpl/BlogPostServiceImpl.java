package com.example.cms.serviceImpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.cms.Entity.BlogPost;
import com.example.cms.Exception.BlogNotFoundById;
import com.example.cms.Exception.BlogPostNotFoundByIdException;
import com.example.cms.Exception.IllegalAccessRequestException;
import com.example.cms.Exception.UserNotFoundByIdException;
import com.example.cms.Utility.ResponseStructure;
import com.example.cms.dtoReponse.BlogPostResponse;
import com.example.cms.dtoRequest.BlogPostRequest;
import com.example.cms.enums.PostType;
import com.example.cms.repository.BlogPostRepository;
import com.example.cms.repository.BlogRepository;
import com.example.cms.repository.ContributionPanelRepository;
import com.example.cms.repository.UserRepository;
import com.example.cms.service.BlogPostService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BlogPostServiceImpl implements BlogPostService{
	
	private BlogPostRepository blogPostRepository;
	
	private BlogRepository blogRepository;
	
	private UserRepository userRepository;
	
	private ContributionPanelRepository contributionPanelRepository;
	
	private ResponseStructure<BlogPostResponse> responseStructure;

	@Override
	public ResponseEntity<ResponseStructure<BlogPostResponse>> createDraft(int blogId, BlogPostRequest blogPostRequest) {
	    String email=SecurityContextHolder.getContext().getAuthentication().getName();
		return userRepository.findByEmail(email).map(user->{
		return blogRepository.findById(blogId).map(blog->{
				 if(!blog.getUser().getEmail().equals(email)&&
						 contributionPanelRepository.existsByPanelIdAndContributors(blog.getContributionPanel().getPanelId(), user))
					 throw new UserNotFoundByIdException("Invalid Input");
				 
				 BlogPost blogPost= mapToBlogPostEntity(blogPostRequest,new BlogPost());
				 blogPost.setBlog(blog);
				 blogPost.setPostType(PostType.DRAFT);
				 
				 return ResponseEntity.ok(responseStructure.setStatus(HttpStatus.OK.value())
						 .setMessage("Draft added successfully")
						 .setData(mapToBlogPostResponse(blogPostRepository.save(blogPost))));
				 
			 }).orElseThrow(()-> new BlogNotFoundById("bog not found By Id") );
		}).orElseThrow(()-> new UserNotFoundByIdException("user not found by Id"));
	}
	
	@Override
	public ResponseEntity<ResponseStructure<BlogPostResponse>> updateDraft(int blogPostId,BlogPostRequest blogPostRequest) {
		String email=SecurityContextHolder.getContext().getAuthentication().getName();
		return userRepository.findByEmail(email).map(user->{
			return blogPostRepository.findById(blogPostId).map(blogPost->{
				if(!blogPost.getBlog().getUser().getEmail().equals(email)&&!contributionPanelRepository
						.existsByPanelIdAndContributors(blogPost.getBlog().getContributionPanel().getPanelId(), user))
					throw new IllegalAccessRequestException("failed to update Draft");

			    return  ResponseEntity.ok(responseStructure.setStatus(HttpStatus.OK.value())
						 .setMessage("Draft updated successfully")
						 .setData(mapToBlogPostResponse(blogPostRepository.save(mapToBlogPostEntity(blogPostRequest, new BlogPost())))));	
			}).orElseThrow(()->new BlogPostNotFoundByIdException("Invalid Input"));
		}).get();
		
	}

	@Override
	public ResponseEntity<ResponseStructure<BlogPostResponse>> deleteDraft(int blogPostId) {
		String email=SecurityContextHolder.getContext().getAuthentication().getName();
		return blogPostRepository.findById(blogPostId).map(blogPost->{
			if(!blogPost.getBlog().getUser().getEmail().equals(email)|| !blogPost.getCreatedBy().equals(email))
				throw new IllegalAccessRequestException("failed to delete draft");
			
			blogPostRepository.delete(blogPost);
			
			return ResponseEntity.ok(responseStructure.setStatus(HttpStatus.OK.value())
					.setMessage("Draft Successfully Delete")
					.setData(mapToBlogPostResponse(blogPost)));
		}).orElseThrow(()-> new BlogPostNotFoundByIdException("BlogPost is not found By Id"));
	}
	

	private BlogPostResponse mapToBlogPostResponse(BlogPost blogPost) {
		
		return new BlogPostResponse(blogPost.getPostId(), blogPost.getTitle(), blogPost.getSubTitle(), blogPost.getSummary(), blogPost.getPostType());
	}

	private BlogPost mapToBlogPostEntity(BlogPostRequest blogPostRequest, BlogPost blogPost) {
		blogPost.setPostType(blogPostRequest.getPostType());
		blogPost.setSubTitle(blogPostRequest.getSubTitle());
		blogPost.setTitle(blogPostRequest.getTitle());
		blogPost.setSummary(blogPostRequest.getSummary());
		return blogPost;
	}

	




	
}
