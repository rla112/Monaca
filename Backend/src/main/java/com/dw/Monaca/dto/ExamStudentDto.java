package com.dw.Monaca.dto;

import jakarta.validation.constraints.NotNull;

// 학생들이 시험 문제를 풀 때 사용하는 DTO(correctAnswer는 없음!)
public class ExamStudentDto {

	
	@NotNull
	private String lectureName;
	
	@NotNull
	private String examTitle;
	
	@NotNull
	private String examText;
	
	@NotNull
	private String examImage;
	
	@NotNull
	private String multipleChoice;

	
	public ExamStudentDto() {
		super();
	}


	public ExamStudentDto(@NotNull String lectureName, @NotNull String examTitle, @NotNull String examText,
			@NotNull String examImage, @NotNull String multipleChoice) {
		super();
		this.lectureName = lectureName;
		this.examTitle = examTitle;
		this.examText = examText;
		this.examImage = examImage;
		this.multipleChoice = multipleChoice;
	}


	public String getLectureName() {
		return lectureName;
	}


	public void setLectureName(String lectureName) {
		this.lectureName = lectureName;
	}


	public String getExamTitle() {
		return examTitle;
	}


	public void setExamTitle(String examTitle) {
		this.examTitle = examTitle;
	}


	public String getExamText() {
		return examText;
	}


	public void setExamText(String examText) {
		this.examText = examText;
	}


	public String getExamImage() {
		return examImage;
	}


	public void setExamImage(String examImage) {
		this.examImage = examImage;
	}


	public String getMultipleChoice() {
		return multipleChoice;
	}


	public void setMultipleChoice(String multipleChoice) {
		this.multipleChoice = multipleChoice;
	}

	
}
