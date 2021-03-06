package com.maxwell.flashcards.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class Expression {

	private Long id;
	private String expression;
	private String meaning;
	private String dictionary;
	private Long dictionaryId;
	private int hits;
	private int fails;

}
