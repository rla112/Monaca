package com.dw.Monaca.model;

import java.time.LocalDateTime;

import com.dw.Monaca.jwtauthority.model.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "received_message")
public class ReceivedMessage {

	@Id
	@Column(name = "received_message_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// 수신자
	@ManyToOne
	private User sender;

	// 발신자
	@ManyToOne
	private User receiver;
	// 이름만 reciever이지만 user임

	@Column(name = "title", length = 20)
	private String title;

	@Column(name = "text", length = 150)
	private String text;

	@Column(name = "is_hide")
	private boolean is_hide;

	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime createAt;

	// Message 클래스를 참조하는 필드( 읽음, 안읽음 상태 표시)
	@OneToOne
	@JoinColumn(name = "message_id")
	private Message message;

	
	public ReceivedMessage() {
		super();
	}

	// ReceivedMessage(SentMessage sentMessage) 생성자 
	public ReceivedMessage(SentMessage sentMessage) {
		super();
        this.sender = sentMessage.getSender();
        this.receiver = sentMessage.getReceiver();
        this.title = sentMessage.getTitle();
        this.text = sentMessage.getText();
        this.is_hide = sentMessage.isIs_hide();
        // boolean 타입의 getter 메서드는 is를 접두사로 사용하지만, 필드 이름 자체가 is로 시작하는 경우에는 get을 사용
        this.createAt = sentMessage.getCreateAt();
	}

	// SentMessage의 모든 필드가 ReceivedMessage로 복사됩니다. 
	public ReceivedMessage(Long id, User sender, User receiver, String title, String text, boolean is_hide,
			LocalDateTime createAt) {
		super();
		this.id = id;
		this.sender = sender;
		this.receiver = receiver;
		this.title = title;
		this.text = text;
		this.is_hide = is_hide;
		this.createAt = createAt;
	}

	public Message getMessage() {
	  return this.message;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
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

	public boolean isIs_hide() {
		return is_hide;
	}

	public void setIs_hide(boolean is_hide) {
		this.is_hide = is_hide;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}

	
}
