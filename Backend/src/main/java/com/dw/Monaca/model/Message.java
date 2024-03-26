package com.dw.Monaca.model;

import java.time.LocalDateTime;

import com.dw.Monaca.jwtauthority.model.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "message")
public class Message {

	@Id
	@Column(name = "message_id")
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

//	// Message 클래스에서 is_hide 필드를 true로 설정하면, 메시지는 발신자와 수신자 모두의 화면에서 삭제
//	@Column(name = "is_hide")
//	private boolean is_hide;

	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime createAt;

    @Column(name = "read_status", nullable = false)
    private boolean readStatus = false;
	
	public Message() {
		super();
	}

	  public Message(Long id, User sender, User receiver, String title, String text, LocalDateTime createAt,
			boolean readStatus) {
		super();
		this.id = id;
		this.sender = sender;
		this.receiver = receiver;
		this.title = title;
		this.text = text;
		this.createAt = createAt;
		this.readStatus = readStatus;
	}

	// 복사 생성자
    public Message(Message message) {
        this.createAt = message.createAt;
        this.sender = message.sender;
        this.receiver = message.receiver;
        this.text = message.text;
        this.title = message.title;
    }
        
    // 읽음 표시를 true로 바꿔주는 메소드   
    public void markAsRead() {
        this.readStatus = true;
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

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}

	public boolean isReadStatus() {
		return readStatus;
	}

	public void setReadStatus(boolean readStatus) {
		this.readStatus = readStatus;
	}
    
	
}
