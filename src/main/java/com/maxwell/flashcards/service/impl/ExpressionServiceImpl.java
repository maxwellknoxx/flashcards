package com.maxwell.flashcards.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxwell.flashcards.entity.ExpressionEntity;
import com.maxwell.flashcards.exception.ResourceNotFoundException;
import com.maxwell.flashcards.repository.ExpressionRepository;
import com.maxwell.flashcards.service.ExpressionService;

@Service
public class ExpressionServiceImpl implements ExpressionService {

	@Autowired
	private ExpressionRepository repository;

	public ExpressionEntity addExpression(ExpressionEntity expression) {
		try {
			return repository.save(expression);
		} catch (Exception e) {
			throw new ResourceNotFoundException("Something went wrong -> create expression -> " + e.getMessage());
		}
	}

	public void removeExpressionById(Long id) {
		ExpressionEntity expression = repository.findById(id).orElseThrow();
		if (Objects.isNull(expression)) {
			throw new ResourceNotFoundException("Expression " + id + " does not exist");
		}
		repository.deleteById(id);
	}

	public ExpressionEntity updateExpression(ExpressionEntity expression) {
		try {
			return repository.save(expression);
		} catch (Exception e) {
			throw new ResourceNotFoundException("Something went wrong -> update expression -> " + e.getMessage());
		}

	}

	@Override
	public List<ExpressionEntity> findAll() {
		List<ExpressionEntity> listExpressions = repository.findAll();
		if (listExpressions == null) {
			throw new ResourceNotFoundException("Something went wrong -> findAll");
		}
		return listExpressions;
	}

	@Override
	public ExpressionEntity findByExpression(String expression) {
		ExpressionEntity expressionFound = repository.findByExpression(expression);
		if (expressionFound == null) {
			throw new ResourceNotFoundException("Something went wrong -> findByExpression");
		}
		return expressionFound;
	}

	@Override
	public List<ExpressionEntity> findByDictionaryId(Long dictionariId) {
		List<ExpressionEntity> list = repository.findByDictionaryId(dictionariId);
		if (list == null) {
			throw new ResourceNotFoundException("Something went wrong -> findByDictionaryId");
		}
		return list;
	}

}
