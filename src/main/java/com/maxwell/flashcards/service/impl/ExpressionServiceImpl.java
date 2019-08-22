package com.maxwell.flashcards.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxwell.flashcards.entity.ExpressionEntity;
import com.maxwell.flashcards.exception.ResourceNotFoundException;
import com.maxwell.flashcards.model.Expression;
import com.maxwell.flashcards.repository.ExpressionRepository;
import com.maxwell.flashcards.service.ExpressionService;
import com.maxwell.flashcards.util.ExpressionMapper;

@Service
public class ExpressionServiceImpl implements ExpressionService {

	@Autowired
	private ExpressionRepository repository;

	public Expression addExpression(ExpressionEntity expression) {
		ExpressionEntity entity = repository.save(expression);
		if (entity == null) {
			return null;
		}
		return ExpressionMapper.convertEntityToModel(entity);
	}

	public Boolean removeExpressionById(Long id) {
		try {
			repository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Expression updateExpression(ExpressionEntity expression) {
		ExpressionEntity entity = repository.save(expression);
		if (entity == null) {
			return null;
		}
		return ExpressionMapper.convertEntityToModel(entity);
	}

	@Override
	public List<Expression> findAll() throws ResourceNotFoundException {
		List<ExpressionEntity> list = repository.findAll();
		if (list.isEmpty()) {
			return null;
		}
		return ExpressionMapper.convertEntityToModelList(list);
	}

	@Override
	public Expression findByExpression(String expression) throws ResourceNotFoundException {
		ExpressionEntity expressionFound = repository.findByExpression(expression);
		if (expressionFound == null) {
			return null;
		}
		return ExpressionMapper.convertEntityToModel(expressionFound);
	}

	@Override
	public List<Expression> findByDictionaryId(Long dictionariId) throws ResourceNotFoundException {
		List<ExpressionEntity> list = repository.findByDictionaryId(dictionariId);
		if (list.isEmpty()) {
			return null;
		}
		return ExpressionMapper.convertEntityToModelList(list);
	}

}
