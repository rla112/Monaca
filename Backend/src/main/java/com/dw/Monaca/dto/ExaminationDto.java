package com.dw.Monaca.dto;

import jakarta.validation.constraints.NotBlank;

public class ExaminationDto {

	private Long id;
	
	private String user;
	
	@NotBlank
	private Long examId;
	
	@NotBlank
	private int answerText;

	
	
	public ExaminationDto() {
		super();
	}



	public ExaminationDto(Long id, String user, @NotBlank Long examId, @NotBlank int answerText) {
		super();
		this.id = id;
		this.user = user;
		this.examId = examId;
		this.answerText = answerText;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getUser() {
		return user;
	}



	public void setUser(String user) {
		this.user = user;
	}



	public Long getExamId() {
		return examId;
	}



	public void setExamId(Long examId) {
		this.examId = examId;
	}



	public int getAnswerText() {
		return answerText;
	}



	public void setAnswerText(int answerText) {
		this.answerText = answerText;
	}

	
}
