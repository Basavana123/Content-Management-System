package com.example.cms.dto;


import java.util.List;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BlogRequest {
	
	@Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z])", message = "The title should only contain alphabets.")
	private String title;
	@NotNull
	private List<String> topics;
	private String summary;
	

}
