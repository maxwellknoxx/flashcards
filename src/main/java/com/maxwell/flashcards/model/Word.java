package com.maxwell.flashcards.model;

public class Word {

	private Long id;
	private String word;
	private String meaning;
	private int hits;
	private int fails;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getMeaning() {
		return meaning;
	}

	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}

	public int getHits() {
		return hits;
	}

	public void setHits(int hits) {
		this.hits = hits;
	}

	public int getFails() {
		return fails;
	}

	public void setFails(int fails) {
		this.fails = fails;
	}

	public int addFail() {
		return this.fails += 1;
	}

	public int addHit() {
		return this.hits += 1;
	}

}
