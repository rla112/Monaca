package com.dw.Monaca.dto;

import jakarta.validation.constraints.NotNull;

public class MessageDto {

	private Long id;

	private String sender; 

	private String title;


	private String text;

	@NotNull
	private String receiver; 

	public MessageDto() {
		super();
	}

	public MessageDto(Long id, String sender, String title, String text, @NotNull String receiver) {
		super();
		this.id = id;
		this.sender = sender;
		this.title = title;
		this.text = text;
		this.receiver = receiver;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
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

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	
}
