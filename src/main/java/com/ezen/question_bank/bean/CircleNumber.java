package com.ezen.question_bank.bean;

public class CircleNumber {
	private String circleNumber = "①②③④⑤⑥⑦";
	
	public String getOneCirleNumber(int index) {
		
		return circleNumber.substring(index, index + 1);
	}
}
