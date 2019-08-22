package com.maxwell.flashcards.service;

import java.util.List;

import com.maxwell.flashcards.model.Dictionary;

public interface DictionaryService {

	List<Dictionary> findAll();
	
	Dictionary findByDictionaryName(String dictionaryName);
	
	Dictionary findDictionaryById(Long id);
	
	List<Dictionary> findDictionaryByUserId(Long id);
	
}
