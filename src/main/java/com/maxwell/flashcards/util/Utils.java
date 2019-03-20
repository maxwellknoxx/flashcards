package com.maxwell.flashcards.util;

import com.maxwell.flashcards.entity.DictionaryEntity;
import com.maxwell.flashcards.entity.ExpressionEntity;
import com.maxwell.flashcards.model.Dictionary;
import com.maxwell.flashcards.model.Expression;

public class Utils {

	public Dictionary convertToModel(DictionaryEntity dictionaryEntity) {
		Dictionary dictionaryModel = new Dictionary();

		dictionaryModel.setDictionaryName(dictionaryEntity.getDictionaryName());
		dictionaryModel.setHitWords(dictionaryEntity.getHitWords());
		dictionaryModel.setId(dictionaryEntity.getId());
		dictionaryModel.setFailWords(dictionaryEntity.getfailWords());

		return dictionaryModel;
	}

	public DictionaryEntity convertToEntity(Dictionary dictionary) {
		DictionaryEntity dictionaryEntity = new DictionaryEntity();

		dictionaryEntity.setDictionaryName(dictionary.getDictionaryName());
		dictionaryEntity.setHitWords(dictionary.getHitWords());
		dictionaryEntity.setId(dictionary.getId());
		dictionaryEntity.setfailWords(dictionary.getFailWords());

		return dictionaryEntity;
	}

	public Expression convertToModel(ExpressionEntity expressionEntity) {
		Expression expression = new Expression();

		expression.setExpression(expressionEntity.getExpression());
		expression.setFails(expressionEntity.getFails());
		expression.setHits(expressionEntity.getHits());
		expression.setId(expressionEntity.getId());
		expression.setMeaning(expressionEntity.getMeaning());
		expression.setDictionary(convertToModel(expressionEntity.getDictionary()));

		return expression;
	}

	public ExpressionEntity convertToEntity(Expression expression) {
		ExpressionEntity expressionEntity = new ExpressionEntity();

		expressionEntity.setExpression(expression.getExpression());
		expressionEntity.setFails(expression.getFails());
		expressionEntity.setHits(expression.getHits());
		expressionEntity.setId(expression.getId());
		expressionEntity.setMeaning(expression.getMeaning());
		expressionEntity.setDictionary(convertToEntity(expression.getDictionary()));

		return expressionEntity;
	}

}
