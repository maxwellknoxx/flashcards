package com.maxwell.flashcards.service.impl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxwell.flashcards.entity.UserEntity;
import com.maxwell.flashcards.exception.ResourceNotFoundException;
import com.maxwell.flashcards.repository.UserRepository;
import com.maxwell.flashcards.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;

	@Override
	public UserEntity save(UserEntity user) {
		try {
			return repository.save(user);
		} catch (Exception e) {
			throw new ResourceNotFoundException("Something went wrong -> create user -> " + e.getMessage());
		}
	}

	public UserEntity findUserById(Long id) {
		UserEntity user = repository.findById(id).orElseThrow();
		if (Objects.isNull(user)) {
			throw new ResourceNotFoundException("User " + id + " not found");
		}
		return user;
	}

	public UserEntity update(UserEntity user) {
		try {
			return repository.save(user);
		} catch (Exception e) {
			throw new ResourceNotFoundException("Something went wrong -> update user -> " + e.getMessage());
		}
	}

	@Override
	public UserEntity findByUserName(String userName) {
		UserEntity user = repository.findByUserName(userName);
		if (Objects.isNull(user)) {
			throw new ResourceNotFoundException("User " + user + " not found");
		}
		return user;
	}

	@Override
	public UserEntity findUserByEmail(String email) {
		UserEntity user = repository.findUserByEmail(email);
		if (Objects.isNull(user)) {
			throw new ResourceNotFoundException("Email " + email + " not found");
		}
		return user;
	}

}
