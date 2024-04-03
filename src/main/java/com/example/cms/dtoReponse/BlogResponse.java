package com.example.cms.dtoReponse;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BlogResponse {

	private int blogId;
	private String title;
	private List<String> topics;
	private String summary;
	

}
