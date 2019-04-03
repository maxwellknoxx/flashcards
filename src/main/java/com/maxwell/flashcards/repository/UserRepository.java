package com.maxwell.flashcards.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maxwell.flashcards.entity.UserEntity;

public interface UserRepository  extends JpaRepository<UserEntity, Long> {
	
	UserEntity findByUserName(String userName);

}
