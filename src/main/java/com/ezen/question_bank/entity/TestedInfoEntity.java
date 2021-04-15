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
@Table(name="tested_info")
@Data 
public class TestedInfoEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="user_id", length=20)
	private String userId;  // 이메일
	
	@Column(name="gubun", columnDefinition="char(9)")
	private String gubun;
	
	@Column(name="correct_answer_counts")
	private int correct_answer_counts;
	
	@Column(name="question_counts")
	private int question_counts;
	
	@Column(name="tested_date", length=19)
	private String testedDate;
}
