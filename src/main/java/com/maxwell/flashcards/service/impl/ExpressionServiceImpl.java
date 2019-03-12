package com.maxwell.flashcards.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxwell.flashcards.entity.ExpressionEntity;
import com.maxwell.flashcards.repository.ExpressionRepository;
import com.maxwell.flashcards.service.ExpressionService;

@Service
public class ExpressionServiceImpl implements ExpressionService {

	@Autowired
	private ExpressionRepository repository;

	public void addWord(ExpressionEntity word) {
		try {
			repository.save(word);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void removeWordById(Long id) {
		repository.deleteById(id);
	}
	
	public void updateWord(ExpressionEntity word) {
		repository.save(word);
	}

	@Override
	public List<ExpressionEntity> findAll() {
		List<ExpressionEntity> listExpressions = repository.findAll();

		if (listExpressions != null && !listExpressions.isEmpty()) {
			return listExpressions;
		}

		return null;
	}

	@Override
	public ExpressionEntity findByExpression(String expression) {
		ExpressionEntity expressionFound = repository.findByExpression(expression);

		if (expressionFound != null) {
			return expressionFound;
		}

		return null;
	}

}
