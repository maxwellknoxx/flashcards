package com.maxwell.flashcards.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maxwell.flashcards.entity.ExpressionEntity;

@Repository
public interface ExpressionRepository extends JpaRepository<ExpressionEntity, Long> {

	List<ExpressionEntity> findAll();

	ExpressionEntity findByExpression(String expression);
	
	List<ExpressionEntity> findByDictionaryId(Long dictionariId);

}
