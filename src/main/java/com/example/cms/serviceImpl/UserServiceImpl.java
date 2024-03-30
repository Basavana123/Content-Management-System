package com.example.cms.serviceImpl;

import org.springframework.http.HttpStatus; 
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.cms.Entity.User;
import com.example.cms.Exception.UserAllReadyExistByEmailException;
import com.example.cms.Exception.UserNotFoundException;
import com.example.cms.Utility.ResponseStructure;
import com.example.cms.dto.UserRequest;
import com.example.cms.dto.UserResponse;
import com.example.cms.repository.UserRepository;
import com.example.cms.service.UserService;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
	
	private PasswordEncoder passwordEncoder;

	private UserRepository userRepository;

	private ResponseStructure<UserResponse> structure;
	
    @Override
	public ResponseEntity<ResponseStructure<UserResponse>> registerUser(UserRequest userRequest) {
		if(userRepository.existsByEmail(userRequest.getEmail()))
			throw new UserAllReadyExistByEmailException("Failed to register User");
		
		User user= mapToUserEntity(userRequest, new User());
		userRepository.save(user);
		return ResponseEntity.ok(structure.setStatus(HttpStatus.OK.value())
				.setMessage("User Registered Successfully")
				.setData(mapToUserResponse(user)));
	}

    
    @Override
	public ResponseEntity<ResponseStructure<UserResponse>> deleteUser(int userId) {
	   
		return userRepository.findById(userId).map(user->{
			user.setDeleted(true);
			userRepository.save(user);
			return ResponseEntity.ok(structure.setStatus(HttpStatus.OK.value())
					.setMessage("user deleted successfully").setData(mapToUserResponse(userRepository.save(user))));
		}).orElseThrow(()-> new UserNotFoundException("User not found  success fully"));
	}
    

	
	private UserResponse mapToUserResponse(User user) {
		
		return new UserResponse(user.getUserId(), 
				user.getUserName(),
				user.getEmail(),
				user.getCreatedAt(),
				user.getLastmodifiedAt());
	}
	
	
	private User mapToUserEntity(UserRequest userRequest, User user) {
		user.setEmail(userRequest.getEmail());
		user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
		user.setUserName(userRequest.getUserName());
		user.setDeleted(false);
		return user;
	}
	

//	private UserResponse mapToUserResponse(User user) {
//		// TODO Auto-generated method stub
//		return  UserResponse.builder()
//				.userId(user.getUserId()
//				. userName(user.getUserName())
//				.email(user.getEmail())
//				.build);
//	}

	
	
}
