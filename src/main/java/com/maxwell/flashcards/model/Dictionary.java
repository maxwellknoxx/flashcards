package com.maxwell.flashcards.model;

import java.util.List;

public class Dictionary {

	private Long id;
	private List<Word> words;
	private int wrongWords;
	private int hitWords;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Word> getWords() {
		return words;
	}

	public void setWords(List<Word> words) {
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
