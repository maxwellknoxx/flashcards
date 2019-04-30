package com.maxwell.flashcards.service;

import java.util.List;

import com.maxwell.flashcards.entity.DictionaryEntity;

public interface DictionaryService {

	List<DictionaryEntity> findAll();
	
	DictionaryEntity findByDictionaryName(String dictionaryName);
	
	DictionaryEntity findDictionaryById(Long id);
	
}
