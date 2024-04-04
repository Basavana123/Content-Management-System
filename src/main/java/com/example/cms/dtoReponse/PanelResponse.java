package com.example.cms.dtoReponse;

import java.util.List;

import com.example.cms.Entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PanelResponse {
	
	private int panelId;

	private List<User> Contributers;
	

}
