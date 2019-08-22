package com.maxwell.flashcards.util;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.maxwell.flashcards.entity.DictionaryEntity;
import com.maxwell.flashcards.model.Dictionary;

@Component
public class DictionaryMapper {
	
	public static Dictionary convertEntityToModel(DictionaryEntity entity) {
		return Dictionary.builder().id(entity.getId()).dictionaryName(entity.getDictionaryName())
				.idUser(entity.getUser().getId()).username(entity.getUser().getUserName())
				.failWords(entity.getFailWords()).hitWords(entity.getHitWords()).build();
	}

	public static List<Dictionary> convertEntityToModelList(List<DictionaryEntity> entities) {
		return entities.stream().filter(Objects::nonNull)
				.map(entity -> Dictionary.builder().id(entity.getId()).dictionaryName(entity.getDictionaryName())
						.idUser(entity.getUser().getId()).username(entity.getUser().getUserName())
						.failWords(entity.getFailWords()).hitWords(entity.getHitWords()).build())
				.collect(Collectors.toList());
	}

}
