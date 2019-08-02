package com.maxwell.flashcards.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class Dictionary {

	private Long id;
	private String dictionaryName;
	private int failWords;
	private int hitWords;
	private Long idUser;
	private String username;
	//private List<Expression> expressions;

}
