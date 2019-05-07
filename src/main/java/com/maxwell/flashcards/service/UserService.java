package com.maxwell.flashcards.service;

import com.maxwell.flashcards.entity.UserEntity;

public interface UserService {
	
	UserEntity save(UserEntity user);
	
	UserEntity findByUserName(String userName);
	
	UserEntity findUserByEmail(String email);
	
}
