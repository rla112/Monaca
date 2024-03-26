package com.dw.Monaca.dto;


import jakarta.validation.constraints.NotNull;

// professor가 exam을 create하거나 update할 때 사용되는 DTO
public class ExamDto{
	
	private Long id;
	
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

	@NotNull
	private int correctAnswer;
	
	
	public ExamDto() {
		super();
	}


	public ExamDto(Long id, @NotNull String lectureName, @NotNull String examTitle, @NotNull String examText,
			@NotNull String examImage, @NotNull String multipleChoice, @NotNull int correctAnswer) {
		super();
		this.id = id;
		this.lectureName = lectureName;
		this.examTitle = examTitle;
		this.examText = examText;
		this.examImage = examImage;
		this.multipleChoice = multipleChoice;
		this.correctAnswer = correctAnswer;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
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


	public int getCorrectAnswer() {
		return correctAnswer;
	}


	public void setCorrectAnswer(int correctAnswer) {
		this.correctAnswer = correctAnswer;
	}


}