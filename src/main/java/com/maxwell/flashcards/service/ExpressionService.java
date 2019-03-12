package com.maxwell.flashcards.service;

import java.util.List;

import com.maxwell.flashcards.entity.ExpressionEntity;

public interface ExpressionService {

	List<ExpressionEntity> findAll();

	ExpressionEntity findByExpression(String expression);

}
