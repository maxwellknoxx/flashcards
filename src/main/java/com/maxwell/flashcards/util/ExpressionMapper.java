package com.maxwell.flashcards.util;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.maxwell.flashcards.entity.ExpressionEntity;
import com.maxwell.flashcards.model.Expression;

@Component
public class ExpressionMapper {
	
	public static Expression convertEntityToModel(ExpressionEntity entity) {
		return Expression.builder().id(entity.getId()).expression(entity.getExpression()).meaning(entity.getMeaning())
				.hits(entity.getHits()).fails(entity.getFails()).dictionary(entity.getDictionary().getDictionaryName())
				.dictionaryId(entity.getDictionary().getId()).build();
	}

	public static List<Expression> convertEntityToModelList(List<ExpressionEntity> entities) {
		return entities.stream().filter(Objects::nonNull)
				.map(entity -> Expression.builder().id(entity.getId()).expression(entity.getExpression())
						.meaning(entity.getMeaning()).hits(entity.getHits()).fails(entity.getFails())
						.dictionary(entity.getDictionary().getDictionaryName())
						.dictionaryId(entity.getDictionary().getId()).build())
				.collect(Collectors.toList());
	}

}
