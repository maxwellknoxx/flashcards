package com.maxwell.flashcards.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxwell.flashcards.entity.UserEntity;
import com.maxwell.flashcards.model.User;
import com.maxwell.flashcards.repository.UserRepository;
import com.maxwell.flashcards.service.UserService;
import com.maxwell.flashcards.util.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;

	@Override
	public User save(UserEntity user) {
		UserEntity entity = repository.save(user);
		if (entity == null) {
			return null;
		}
		return UserMapper.convertEntityToModel(entity);
	}

	public UserEntity findUserById(Long id) {
		UserEntity user = repository.findById(id).orElse(null);
		if (user == null) {
			return null;
		}
		return user;
	}

	public User update(UserEntity user) {
		UserEntity entity = repository.save(user);
		if (entity == null) {
			return null;
		}
		return UserMapper.convertEntityToModel(entity);
	}

	@Override
	public User findByUserName(String userName) {
		UserEntity user = repository.findByUserName(userName);
		if (user == null) {
			return null;
		}
		return UserMapper.convertEntityToModel(user);
	}

	public UserEntity getByUserName(String userName) {
		UserEntity user = repository.findByUserName(userName);
		if (user == null) {
			return null;
		}
		return user;
	}

	@Override
	public User findUserByEmail(String email) {
		UserEntity user = repository.findUserByEmail(email);
		if (user == null) {
			return null;
		}
		return UserMapper.convertEntityToModel(user);
	}

	public UserEntity getUserByEmail(String email) {
		UserEntity user = repository.findUserByEmail(email);
		if (user == null) {
			return null;
		}
		return user;
	}

}
