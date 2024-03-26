package com.dw.Monaca.dto;

import jakarta.validation.constraints.NotNull;

public class ProfessorNoticeDto {

	private String author;

	private String lecture;
	
	@NotNull
	private String title;


	@NotNull
	private String text;

	public ProfessorNoticeDto() {
		super();
	}

	public ProfessorNoticeDto(String author, String lecture, @NotNull String title, @NotNull String text) {
		super();
		this.author = author;
		this.lecture = lecture;
		this.title = title;
		this.text = text;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getLecture() {
		return lecture;
	}

	public void setLecture(String lecture) {
		this.lecture = lecture;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}


}
