package com.maxwell.flashcards.model;

public class Expression {

	private Long id;
	private String expression;
	private String meaning;
	private Dictionary dictionary;
	private String dictionaryIdentityKey;
	private int hits;
	private int fails;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	public String getMeaning() {
		return meaning;
	}

	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}

	public Dictionary getDictionary() {
		return dictionary;
	}

	public void setDictionary(Dictionary dictionary) {
		this.dictionary = dictionary;
	}

	public String getDictionaryIdentityKey() {
		return dictionaryIdentityKey;
	}

	public void setDictionaryIdentityKey(String dictionaryIdentityKey) {
		this.dictionaryIdentityKey = dictionaryIdentityKey;
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
