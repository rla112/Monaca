package com.dw.Monaca.dto;

public class QandADto {

	private String author;
	
	private String lectureName;
	
	private String title;
	
	private String text;
	
	private String createAt;
	
	private boolean newStatus;
	
	private String dusoisablePw;
	

	public QandADto() {
		super();
	}


	public QandADto(String author, String lectureName, String title, String text, String createAt, boolean newStatus,
			String dusoisablePw) {
		super();
		this.author = author;
		this.lectureName = lectureName;
		this.title = title;
		this.text = text;
		this.createAt = createAt;
		this.newStatus = newStatus;
		this.dusoisablePw = dusoisablePw;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public String getLectureName() {
		return lectureName;
	}


	public void setLectureName(String lectureName) {
		this.lectureName = lectureName;
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


	public String getCreateAt() {
		return createAt;
	}


	public void setCreateAt(String createAt) {
		this.createAt = createAt;
	}


	public boolean isNewStatus() {
		return newStatus;
	}


	public void setNewStatus(boolean newStatus) {
		this.newStatus = newStatus;
	}


	public String getDusoisablePw() {
		return dusoisablePw;
	}


	public void setDusoisablePw(String dusoisablePw) {
		this.dusoisablePw = dusoisablePw;
	}

	
	
}
