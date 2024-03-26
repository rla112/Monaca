package com.dw.Monaca.dto;

import jakarta.validation.constraints.NotNull;

public class MaterialListDto {


	private String lectureName;
	
	private String author;
	

	private String professor;

	@NotNull
	private String title;
	
	private String createAt;
	
	private boolean isReservation;

	public MaterialListDto() {
		super();
	}

	public String getLectureName() {
		return lectureName;
	}

	public void setLectureName(String lectureName) {
		this.lectureName = lectureName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getProfessor() {
		return professor;
	}

	public void setProfessor(String professor) {
		this.professor = professor;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCreateAt() {
		return createAt;
	}

	public void setCreateAt(String createAt) {
		this.createAt = createAt;
	}

	public boolean isReservation() {
		return isReservation;
	}

	public void setReservation(boolean isReservation) {
		this.isReservation = isReservation;
	}
	
	
}

