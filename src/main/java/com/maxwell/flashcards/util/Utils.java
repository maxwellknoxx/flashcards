package com.maxwell.flashcards.util;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.maxwell.flashcards.entity.DictionaryEntity;
import com.maxwell.flashcards.entity.ExpressionEntity;
import com.maxwell.flashcards.entity.UserEntity;
import com.maxwell.flashcards.model.Dictionary;
import com.maxwell.flashcards.model.Expression;
import com.maxwell.flashcards.model.User;

public class Utils {

	public static Dictionary convertDictionaryToModel(DictionaryEntity entity) {
		return Dictionary.builder().id(entity.getId()).dictionaryName(entity.getDictionaryName())
				.idUser(entity.getUser().getId()).username(entity.getUser().getUserName())
				.failWords(entity.getFailWords()).hitWords(entity.getHitWords())
				.expressions(convertExpressionToModelList(entity.getExpressions())).build();
	}

	public static List<Dictionary> convertDictionaryToModelList(List<DictionaryEntity> entities) {
		return entities.stream().filter(Objects::nonNull)
				.map(entity -> Dictionary.builder().id(entity.getId()).dictionaryName(entity.getDictionaryName())
						.idUser(entity.getUser().getId()).username(entity.getUser().getUserName())
						.failWords(entity.getFailWords()).hitWords(entity.getHitWords())
						.expressions(convertExpressionToModelList(entity.getExpressions())).build())
				.collect(Collectors.toList());
	}

	public static Expression convertExpressionToModel(ExpressionEntity entity) {
		return Expression.builder().id(entity.getId()).expression(entity.getExpression()).meaning(entity.getMeaning())
				.hits(entity.getHits()).fails(entity.getFails()).dictionary(entity.getDictionary().getDictionaryName())
				.dictionaryId(entity.getDictionary().getId()).build();
	}

	public static List<Expression> convertExpressionToModelList(List<ExpressionEntity> entities) {
		return entities.stream().filter(Objects::nonNull)
				.map(entity -> Expression.builder().id(entity.getId()).expression(entity.getExpression())
						.meaning(entity.getMeaning()).hits(entity.getHits()).fails(entity.getFails())
						.dictionary(entity.getDictionary().getDictionaryName())
						.dictionaryId(entity.getDictionary().getId()).build())
				.collect(Collectors.toList());
	}

	public static User convertUserEntityToModel(UserEntity entity) {
		return User.builder().id(entity.getId()).username(entity.getUserName()).email(entity.getEmail())
				.isLogged(entity.getIsLogged()).dictionaries(convertDictionaryToModelList(entity.getDictionaries()))
				.build();
	}

	public static List<User> convertUserEntityToModelList(List<UserEntity> entities) {
		return entities.stream().filter(Objects::nonNull)
				.map(entity -> User.builder().id(entity.getId()).username(entity.getUserName()).email(entity.getEmail())
						.isLogged(entity.getIsLogged())
						.dictionaries(convertDictionaryToModelList(entity.getDictionaries())).build())
				.collect(Collectors.toList());
	}

}
