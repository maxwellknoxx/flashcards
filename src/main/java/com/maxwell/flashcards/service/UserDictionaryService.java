package com.maxwell.flashcards.service;

import java.util.List;

import com.maxwell.flashcards.entity.UserDictionaryEntity;

public interface UserDictionaryService {

	UserDictionaryEntity findByUserId(Long id);

	List<UserDictionaryEntity> findAllDictionaryByUserId(Long id);

	UserDictionaryEntity findByDictionaryId(Long id);

	List<UserDictionaryEntity> findAll();

}
