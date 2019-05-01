package com.maxwell.flashcards.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maxwell.flashcards.entity.UserDictionaryEntity;

@Repository
public interface UserDictionaryRepository extends JpaRepository<UserDictionaryEntity, Long> {

	UserDictionaryEntity findByUserId(Long id);
	
	List<UserDictionaryEntity> findAllDictionaryByUserId(Long id);
	
	UserDictionaryEntity findByDictionaryId(Long id);
	
	List<UserDictionaryEntity> findAll();
	
}
