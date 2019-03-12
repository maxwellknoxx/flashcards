package com.maxwell.flashcards.model;

import java.util.List;

public class Dictionary {

	private Long id;
	private String dictionaryName;
	private List<Expression> words;
	private int wrongWords;
	private int hitWords;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDictionaryName() {
		return dictionaryName;
	}

	public void setDictionaryName(String dictionaryName) {
		this.dictionaryName = dictionaryName;
	}

	public List<Expression> getWords() {
		return words;
	}

	public void setWords(List<Expression> words) {
		this.words = words;
	}

	public int getWrongWords() {
		return wrongWords;
	}

	public void setWrongWords(int wrongWords) {
		this.wrongWords = wrongWords;
	}

	public int getHitWords() {
		return hitWords;
	}

	public void setHitWords(int hitWords) {
		this.hitWords = hitWords;
	}

}
