package com.ezen.question_bank.viewmodel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class QuestionForm {
	
	/* 필드 */
	@NotBlank(message= "# 필수입력사항입니다.")
	@Size(max= 50, message= "# 50자를 초과할 수 없습니다.")
	private String title;
	
	@NotBlank(message= "# 필수입력사항입니다.")
	private String choice1;
	
	@NotBlank(message= "# 필수입력사항입니다.")
	private String choice2;
	
	@NotBlank(message= "# 필수입력사항입니다.")
	private String choice3;
	
	@NotBlank(message= "# 필수입력사항입니다.")
	private String choice4;
	
	private int correct_answer;								
	
	
	
}