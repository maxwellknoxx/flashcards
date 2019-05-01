package com.maxwell.flashcards.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maxwell.flashcards.entity.DictionaryEntity;

@Repository
public interface DictionaryRepository extends JpaRepository<DictionaryEntity, Long> {

	List<DictionaryEntity> findAll();
	
	DictionaryEntity findByDictionaryName(String dictionaryName);
	
	DictionaryEntity findDictionaryById(Long id);

}
