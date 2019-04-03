package com.maxwell.flashcards.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxwell.flashcards.entity.UserEntity;
import com.maxwell.flashcards.repository.UserRepository;
import com.maxwell.flashcards.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;

	@Override
	public UserEntity save(UserEntity user) {
		return repository.save(user);
	}

	@Override
	public UserEntity findByUserName(String userName) {
		return repository.findByUserName(userName);
	}

}
