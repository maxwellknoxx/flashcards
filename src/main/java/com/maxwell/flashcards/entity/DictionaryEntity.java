package com.maxwell.flashcards.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "dictionary")
public class DictionaryEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "dictionary_name", nullable = false)
	private String dictionaryName;

	@OneToMany(mappedBy = "dictionary")
	@Column(name = "words")
	private List<ExpressionEntity> words;

	@Column(name = "wrong_words")
	private int wrongWords;

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

	public List<ExpressionEntity> getWords() {
		return words;
	}

	public void setWords(List<ExpressionEntity> words) {
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
