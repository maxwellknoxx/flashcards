package com.maxwell.flashcards.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.NaturalId;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = { "username" }) })
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NaturalId
	@Column(name = "username", nullable = false)
	private String userName;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "answer", nullable = false)
	private String answer;

	@Column(name = "is_logged", nullable = true)
	private Boolean isLogged;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private List<DictionaryEntity> dictionaries;

}
