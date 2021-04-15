package com.ezen.question_bank.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="question")
@Data  // lombot
public class QuestionEntity {

	@Id
	@Column(name="uuid", length=36)
	private String uuid;
	
	@Column(name="title", length=100)
	private String title;
	
	@Column(name="choice", length=200)
	private String choice;
	
	@Column(name="correct_answer")
	private int correctAnswer;
	
	@Column(name="created_date", length=19)
	private String createdDate;
}
