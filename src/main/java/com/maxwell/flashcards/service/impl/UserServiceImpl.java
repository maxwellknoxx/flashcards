package com.maxwell.flashcards.service.impl;

import java.util.Optional;

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
	
	public Optional<UserEntity> findUserById(Long id) {
		return repository.findById(id);
	}
	
	public UserEntity update(UserEntity user) {
		return repository.save(user);
	}

	@Override
	public UserEntity findByUserName(String userName) {
		return repository.findByUserName(userName);
	}

	@Override
	public UserEntity findUserByEmail(String email) {
		return repository.findUserByEmail(email);
	}
	
	

}
