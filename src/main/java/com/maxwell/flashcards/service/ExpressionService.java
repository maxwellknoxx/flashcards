package com.maxwell.flashcards.service;

import java.util.List;

import com.maxwell.flashcards.model.Expression;

public interface ExpressionService {

	List<Expression> findAll();

	Expression findByExpression(String expression);
	
	List<Expression> findByDictionaryId(Long dictionariId);

}
