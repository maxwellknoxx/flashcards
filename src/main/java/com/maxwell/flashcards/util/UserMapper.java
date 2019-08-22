package com.maxwell.flashcards.util;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.maxwell.flashcards.entity.UserEntity;
import com.maxwell.flashcards.model.User;

@Component
public class UserMapper {

	public static User convertEntityToModel(UserEntity entity) {
		return User.builder().id(entity.getId()).username(entity.getUserName()).email(entity.getEmail())
				.isLogged(entity.getIsLogged())
				.dictionaries(DictionaryMapper.convertEntityToModelList(entity.getDictionaries())).build();
	}

	public static User convertEntityToModelCreated(UserEntity entity) {
		return User.builder().id(entity.getId()).username(entity.getUserName()).email(entity.getEmail())
				.isLogged(entity.getIsLogged()).build();
	}

	public static List<User> convertEntityToModelList(List<UserEntity> entities) {
		return entities.stream().filter(Objects::nonNull)
				.map(entity -> User.builder().id(entity.getId()).username(entity.getUserName()).email(entity.getEmail())
						.isLogged(entity.getIsLogged())
						.dictionaries(DictionaryMapper.convertEntityToModelList(entity.getDictionaries())).build())
				.collect(Collectors.toList());
	}

}
