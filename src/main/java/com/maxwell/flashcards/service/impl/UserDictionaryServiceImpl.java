package com.maxwell.flashcards.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxwell.flashcards.entity.UserDictionaryEntity;
import com.maxwell.flashcards.repository.UserDictionaryRepository;
import com.maxwell.flashcards.service.UserDictionaryService;

@Service
public class UserDictionaryServiceImpl implements UserDictionaryService {
	
	@Autowired
	private UserDictionaryRepository repository;

	@Override
	public UserDictionaryEntity findByUserId(Long id) {
		return repository.findByUserId(id);
	}

	@Override
	public List<UserDictionaryEntity> findAllDictionaryByUserId(Long id) {
		return repository.findAllDictionaryByUserId(id);
	}

	@Override
	public UserDictionaryEntity findByDictionaryId(Long id) {
		return repository.findByDictionaryId(id);
	}

	@Override
	public List<UserDictionaryEntity> findAll() {
		return repository.findAll();
	}
	
	public UserDictionaryEntity save(UserDictionaryEntity userDictionary) {
		return repository.save(userDictionary);
	}
	
	public void remove(Long id) {
		repository.deleteById(findByDictionaryId(id).getId());
	}
	
}

