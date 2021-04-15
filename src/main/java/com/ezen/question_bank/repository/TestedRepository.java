package com.ezen.question_bank.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.ezen.question_bank.entity.QuestionEntity;
import com.ezen.question_bank.entity.TestedEntity;

public interface TestedRepository 
	extends PagingAndSortingRepository<TestedEntity, Long> {

}
