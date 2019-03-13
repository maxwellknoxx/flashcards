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
		dictionaryModel.setWrongWords(dictionaryEntity.getWrongWords());

		return dictionaryModel;
	}

	public Expression convertToModel(ExpressionEntity expressionEntity) {
		Expression expression = new Expression();

		expression.setExpression(expressionEntity.getExpression());
		expression.setFails(expressionEntity.getFails());
		expression.setHits(expressionEntity.getHits());
		expression.setId(expressionEntity.getId());
		expression.setMeaning(expressionEntity.getMeaning());

		return expression;
	}

}
