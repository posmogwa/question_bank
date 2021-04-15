package com.ezen.question_bank.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@ConfigurationProperties(prefix= "question.list")
@Configuration("pagingModel")
public class PagingModel {
	
	private String pageGubun;		
	private int pageNumber;			
	private int totalPages;			
	private int pageSize;				
	private int[] pageSizes;	
}