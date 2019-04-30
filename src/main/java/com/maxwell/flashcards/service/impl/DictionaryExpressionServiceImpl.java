package com.maxwell.flashcards.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxwell.flashcards.entity.DictionaryEntity;
import com.maxwell.flashcards.entity.ExpressionEntity;
import com.maxwell.flashcards.repository.DictionaryRepository;
import com.maxwell.flashcards.repository.ExpressionRepository;
import com.maxwell.flashcards.service.DictionaryExpressionService;

@Service
public class DictionaryExpressionServiceImpl implements DictionaryExpressionService {

	@Autowired
	private DictionaryRepository repository;

	@Autowired
	private ExpressionRepository expressionRepository;

	@Override
	public DictionaryEntity findDictionaryById(Long id) {
		Optional<DictionaryEntity> result = repository.findById(id);
		DictionaryEntity dictionaryEntity = result.orElse(null);
		return dictionaryEntity;
	}

	public void saveDictionary(DictionaryEntity dictionaryEntity) {
		repository.save(dictionaryEntity);
	}

	public void saveExpression(ExpressionEntity expressionEntity) {
		expressionRepository.save(expressionEntity);
	}

	public void updateHitsFails(DictionaryEntity dictionaryEntity, ExpressionEntity expressionEntity) {
		repository.save(dictionaryEntity);
		expressionRepository.save(expressionEntity);
	}

	@Override
	public List<ExpressionEntity> findByDictionaryId(Long dictionariId) {
		return expressionRepository.findByDictionaryId(dictionariId);
	}

}
