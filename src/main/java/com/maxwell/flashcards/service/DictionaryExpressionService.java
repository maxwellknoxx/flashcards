package com.maxwell.flashcards.service;

import java.util.List;

import com.maxwell.flashcards.entity.DictionaryEntity;
import com.maxwell.flashcards.entity.ExpressionEntity;

public interface DictionaryExpressionService {

	DictionaryEntity findDictionaryById(Long id);
	
	List<ExpressionEntity> findByDictionaryId(Long dictionariId);

}
