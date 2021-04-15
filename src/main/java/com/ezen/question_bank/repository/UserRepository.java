package com.ezen.question_bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ezen.question_bank.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	
	@Query("SELECT user_id FROM UserEntity u WHERE u.user_id= ?1")
	UserEntity getOne(String user_id);
	
}
