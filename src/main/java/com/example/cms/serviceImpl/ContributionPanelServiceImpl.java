package com.example.cms.serviceImpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.cms.Entity.ContributionPanel;
import com.example.cms.Exception.IllegalAccessRequestException;
import com.example.cms.Exception.PanelNotFoundByIdException;
import com.example.cms.Exception.UserNotFoundByIdException;
import com.example.cms.Utility.ResponseStructure;
import com.example.cms.dtoReponse.PanelResponse;
import com.example.cms.repository.BlogRepository;
import com.example.cms.repository.ContributionPanelRepository;
import com.example.cms.repository.UserRepository;
import com.example.cms.service.ContributionPanelService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ContributionPanelServiceImpl implements ContributionPanelService{

	private ContributionPanelRepository contributionPanelRepository;
	private UserRepository userRepository;
	private BlogRepository blogRepository;
	private ResponseStructure<PanelResponse> responseStructure;

	private PanelResponse mapToContributionPanelResponse(ContributionPanel panel) {

//		return PanelResponse.builder().panelId(panel.getPanelId()).build();
		return new PanelResponse(panel.getPanelId());

	}
	
	@Override
	public ResponseEntity<ResponseStructure<PanelResponse>> addContributers(int userId, int panelId) {
		// TODO Auto-generated method stub
		String email=SecurityContextHolder.getContext().getAuthentication().getName();
		   return userRepository.findByEmail(email).map(owner->{
			return contributionPanelRepository.findById(panelId).map(panel ->{
				if(!blogRepository.existsByUserAndContributionPanel(owner,panel))
					throw new IllegalAccessRequestException("Failed to add Contribution");
				 return userRepository.findById(userId).map(contribution->{
			        if(!panel.getUsers().contains(contribution)&& panel.getUsers().contains(owner))
						panel.getUsers().add(contribution);

					contributionPanelRepository.save(panel);
					return ResponseEntity.ok(responseStructure.setStatus(HttpStatus.OK.value())
							.setMessage("Contribution added Succesfully")
							.setData(mapToContributionPanelResponse(panel)));
				}).orElseThrow(()->new UserNotFoundByIdException("User Not found By Id"));
			}).orElseThrow(()-> new PanelNotFoundByIdException("panel Not Found By Id"));
		}).get();
	}

	@Override
	public ResponseEntity<ResponseStructure<PanelResponse>> removeUserFromContributers(int userId, int panelId) {
		
		String email=SecurityContextHolder.getContext().getAuthentication().getName();
		return  userRepository.findByEmail(email).map(owner->{
			return contributionPanelRepository.findById(panelId).map(panel ->{
				if(!blogRepository.existsByUserAndContributionPanel(owner,panel))
					throw new IllegalAccessRequestException("Failed to add Contribution");
				return userRepository.findById(userId).map(contribution->{
					//if(!panel.getUsers().contains(contribution)&& panel.getUsers().contains(owner))
						panel.getUsers().remove(contribution);

					contributionPanelRepository.save(panel);
					return ResponseEntity.ok(responseStructure.setStatus(HttpStatus.OK.value())
							.setMessage("removed user from Contribution")
							.setData(mapToContributionPanelResponse(panel)));
				}).orElseThrow(()->new UserNotFoundByIdException("User Not found By Id"));
			}).orElseThrow(()-> new PanelNotFoundByIdException("panel Not Found By Id"));
		}).get();
	}

	

}








