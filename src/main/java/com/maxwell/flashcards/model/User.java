package com.maxwell.flashcards.model;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class User {

	private Long id;
	private String username;
	private String email;
	private Boolean isLogged;
	List<Dictionary> dictionaries;
	
}
