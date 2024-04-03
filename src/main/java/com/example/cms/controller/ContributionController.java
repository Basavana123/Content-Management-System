package com.example.cms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.cms.Utility.ResponseStructure;
import com.example.cms.dtoReponse.PanelResponse;
import com.example.cms.service.ContributionPanelService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class ContributionController {
	
	private ContributionPanelService contributionPanelService;
	
	public ResponseEntity<ResponseStructure<PanelResponse>> addContributers(@RequestParam int userId,@RequestParam int panelId){
		return contributionPanelService.addContributers(userId,panelId);
		
	}
	
	

}
