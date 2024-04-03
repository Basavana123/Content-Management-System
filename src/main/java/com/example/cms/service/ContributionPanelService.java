package com.example.cms.service;

import org.springframework.http.ResponseEntity;

import com.example.cms.Utility.ResponseStructure;
import com.example.cms.dtoReponse.PanelResponse;

public interface ContributionPanelService {

	ResponseEntity<ResponseStructure<PanelResponse>> addContributers(int userId, int panelId);
	
	ResponseEntity<ResponseStructure<PanelResponse>> removeUserFromContributers(int userId, int panelId);

}
