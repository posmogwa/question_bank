package com.ezen.question_bank.model;

import lombok.Data;

@Data
public class TestedModel {
	private long id;
	private String uuid;
	private String title;
	private String choice;
	private int correctAnswer;
	private int selectedAnswer;
}
