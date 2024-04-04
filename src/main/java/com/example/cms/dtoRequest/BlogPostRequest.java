package com.example.cms.dtoRequest;

import com.example.cms.enums.PostType;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BlogPostRequest {
	
	@NotNull
	private String title;
	private String  subTitle;
	
	private String summary;
	private PostType postType;
}
