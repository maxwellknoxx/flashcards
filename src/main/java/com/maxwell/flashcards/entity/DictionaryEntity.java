package com.maxwell.flashcards.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dictionary")
public class DictionaryEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "dictionary_name", nullable = false)
	private String dictionaryName;

	@Column(name = "fail_words")
	private int failWords;

	@Column(name = "hit_words")
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

	public int getfailWords() {
		return failWords;
	}

	public void setfailWords(int failWords) {
		this.failWords = failWords;
	}

	public int getHitWords() {
		return hitWords;
	}

	public void setHitWords(int hitWords) {
		this.hitWords = hitWords;
	}
	
}
