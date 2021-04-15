package com.ezen.question_bank.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.ezen.question_bank.entity.QuestionEntity;

public interface QuestionRepository 
	extends PagingAndSortingRepository<QuestionEntity, String> {

	@Query(value = "SELECT uuid FROM question", 
			   nativeQuery = true)
		List<String> findUUidNative();
		
	@Query(value = "SELECT * FROM question q WHERE q.uuid = :uuid", 
		   nativeQuery = true)
	QuestionEntity findByUuidNamedParamsNative(
			  @Param("uuid") String uuid);
}
