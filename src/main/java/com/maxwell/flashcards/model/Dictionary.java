package com.maxwell.flashcards.model;

public class Dictionary {

	private Long id;
	private String dictionaryName;
	private int failWords;
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

	public int getFailWords() {
		return failWords;
	}

	public void setFailWords(int failWords) {
		this.failWords = failWords;
	}

	public int getHitWords() {
		return hitWords;
	}

	public void setHitWords(int hitWords) {
		this.hitWords = hitWords;
	}

	public int addHitWords() {
		return this.hitWords = this.hitWords + 1;
	}

	public int addFailWords() {
		return this.failWords = this.failWords + 1;
	}

}
