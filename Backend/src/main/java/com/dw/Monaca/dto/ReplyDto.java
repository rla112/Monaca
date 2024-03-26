package com.dw.Monaca.dto;

import jakarta.validation.constraints.NotNull;

public class ReplyDto {

	private String ReplyAuthor;
	
	private Long QandA;
	
	@NotNull
	private String title;
	
	@NotNull
	private String text;

	
	public ReplyDto() {
		super();
	}


	public ReplyDto(String replyAuthor, Long qandA, @NotNull String title, @NotNull String text) {
		super();
		ReplyAuthor = replyAuthor;
		QandA = qandA;
		this.title = title;
		this.text = text;
	}


	public String getReplyAuthor() {
		return ReplyAuthor;
	}


	public void setReplyAuthor(String replyAuthor) {
		ReplyAuthor = replyAuthor;
	}


	public Long getQandA() {
		return QandA;
	}


	public void setQandA(Long qandA) {
		QandA = qandA;
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
