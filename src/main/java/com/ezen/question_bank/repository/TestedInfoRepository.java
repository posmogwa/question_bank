package com.ezen.question_bank.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.ezen.question_bank.entity.QuestionEntity;
import com.ezen.question_bank.entity.TestedEntity;
import com.ezen.question_bank.entity.TestedInfoEntity;

public interface TestedInfoRepository 
	extends PagingAndSortingRepository<TestedInfoEntity, Long> {

}
