package com.maxwell.flashcards.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "expression")
public class ExpressionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "dictionary", nullable = false)
	private DictionaryEntity dictionary;

	@Column(name = "expression", nullable = false)
	private String expression;

	@Column(name = "meaning", nullable = false)
	private String meaning;

	@Column(name = "hits")
	private int hits;

	@Column(name = "fails")
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

}
