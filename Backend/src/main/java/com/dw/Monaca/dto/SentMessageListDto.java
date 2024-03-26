package com.dw.Monaca.dto;

public class SentMessageListDto {

	
	private String sender;
	
	private String receiver;
	
	private String title;
	
	private String createAt;
	
	private boolean is_hide;

	public SentMessageListDto() {
		super();
	}

	public SentMessageListDto(String sender, String receiver, String title, String createAt, boolean is_hide) {
		super();
		this.sender = sender;
		this.receiver = receiver;
		this.title = title;
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
