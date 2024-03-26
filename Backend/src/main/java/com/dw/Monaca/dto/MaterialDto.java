package com.dw.Monaca.dto;

import jakarta.validation.constraints.NotNull;

public class MaterialDto {

	private String lectureName;
	
	private String author;
	
	private String professor;

	@NotNull
	private String title;
	
	@NotNull
	private String text;
	
	private String image;

	private String createAt;

	private boolean isReservation;
	
	public MaterialDto() {
		super();
	}

	public MaterialDto(@NotNull String lectureName, String author, @NotNull String professor, @NotNull String title,
			@NotNull String text, @NotNull String image, String createAt, boolean isReservation) {
		super();
		this.lectureName = lectureName;
		this.author = author;
		this.professor = professor;
		this.title = title;
		this.text = text;
		this.image = image;
		this.createAt = createAt;
		this.isReservation = isReservation;
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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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
