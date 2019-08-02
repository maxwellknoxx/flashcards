package com.maxwell.flashcards.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "dictionaries")
public class DictionaryEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "dictionary_name", nullable = false)
	private String dictionaryName;

	@Column(name = "fail_words")
	private int failWords;

	@Column(name = "hit_words")
	private int hitWords;

	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonBackReference("user")
	private UserEntity user;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "dictionary")
	private List<ExpressionEntity> expressions;

	public void addHit() {
		this.hitWords = this.hitWords + 1;
	}

	public void addFail() {
		this.failWords = this.failWords + 1;
	}

}
