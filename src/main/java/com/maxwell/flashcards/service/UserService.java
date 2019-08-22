package com.maxwell.flashcards.service;

import com.maxwell.flashcards.entity.UserEntity;
import com.maxwell.flashcards.model.User;

public interface UserService {
	
	User save(UserEntity user);
	
	User findByUserName(String userName);
	
	User findUserByEmail(String email);
	
}
