package com.maxwell.flashcards.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "expressions")
public class ExpressionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "expression", nullable = false)
	private String expression;

	@Column(name = "meaning", nullable = false)
	private String meaning;

	@Column(name = "hits")
	private int hits;

	@Column(name = "fails")
	private int fails;
	
	@ManyToOne
	@JoinColumn(name = "dictionary_id")
	@JsonIgnore
	private DictionaryEntity dictionary;
	
	public void addHit() {
		this.hits = this.hits + 1;
	}
	
	public void addFail() {
		this.fails = this.fails + 1;
	}

}
