package com.ezen.question_bank.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="tested")
@Data 
public class TestedEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
//	@Column(name="user_id", length=20)
//	private String userId;  // 이메일
	
	@Column(name="gubun", columnDefinition="char(9)")
	private String gubun;
	
	@Column(name="seq") // 문제번호
	private int seq;
	
	@OneToOne
	@JoinColumn(name="question_uuid", 
					insertable=false, updatable=false)
	private QuestionEntity question;
	
	@Column(name="question_uuid", length=36)
	private String questionUuid;
	
	@Column(name="selected_answer")
	private int selectedAnswer;
	
	
	//@Column(name="tested_date", length=19)
	//private String testedDate;
}
