package com.dw.Monaca.dto;

public class SentMessageDto {

private String sender;
	
	private String receiver;
	
	private String title;
	
	private String text;
	
	private String createAt;
	
	private boolean is_hide;


	public SentMessageDto() {
		super();
	}

	public SentMessageDto(String sender, String receiver, String title, String text, String createAt, boolean is_hide) {
		super();
		this.sender = sender;
		this.receiver = receiver;
		this.title = title;
		this.text = text;
		this.createAt = createAt;
		this.is_hide = is_hide;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
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

	public boolean isIs_hide() {
		return is_hide;
	}

	public void setIs_hide(boolean is_hide) {
		this.is_hide = is_hide;
	}
	
	
	
	
}
