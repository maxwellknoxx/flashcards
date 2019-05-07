package com.maxwell.flashcards.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maxwell.flashcards.entity.UserEntity;

@Repository
public interface UserRepository  extends JpaRepository<UserEntity, Long> {
	
	UserEntity findByUserName(String userName);
	
	UserEntity findUserByEmail(String email);

}
