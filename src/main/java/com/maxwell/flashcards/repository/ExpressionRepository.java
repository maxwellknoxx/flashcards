package com.maxwell.flashcards.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maxwell.flashcards.entity.ExpressionEntity;

public interface ExpressionRepository extends JpaRepository<ExpressionEntity, Long> {

	List<ExpressionEntity> findAll();

	ExpressionEntity findByExpression(String expression);

}
