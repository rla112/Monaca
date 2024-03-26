package com.dw.Monaca.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AdminNoticeDto {

	
	private String author;


	@NotNull
	private String title;


	@NotNull
	private String text;

	public AdminNoticeDto() {
		super();
	}

	public AdminNoticeDto(String author, @NotBlank @NotNull String title, @NotBlank @NotNull String text) {
		super();
		this.author = author;
		this.title = title;
		this.text = text;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
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
