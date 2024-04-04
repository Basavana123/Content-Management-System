package com.example.cms.dtoReponse;

import com.example.cms.enums.PostType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BlogPostResponse {
	
	private int blogId;
	private String title;
	private String  subTitle;
	private String summary;
	private PostType postType;

}
