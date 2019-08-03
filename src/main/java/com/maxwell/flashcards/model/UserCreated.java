package com.maxwell.flashcards.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Builder
public class UserCreated {

	private Long id;
	private String username;
	private Boolean isLogged;

}
