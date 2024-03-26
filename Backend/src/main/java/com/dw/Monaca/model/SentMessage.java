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
@Table(name = "sent_message")
public class SentMessage {

	@Id
	@Column(name = "sent_message_id")
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

	// SentMessage 클래스에서 is_hide 필드를 true로 설정하면,
	// 해당 메시지는 발신자의 화면에서 삭제되지만 수신자는 여전히 메시지를 볼 수 있음.
	@Column(name = "is_hide")
	private boolean is_hide;

	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime createAt;

    @OneToOne
    @JoinColumn(name = "message_id")
    private Message message;

	
	// 기본 생성자
	 public SentMessage() {
	 
	 }
	// 복사 생성자
	public SentMessage(SentMessage sentMessage) {
		  this.id = sentMessage.getId();
	        this.sender = sentMessage.getSender();
	        this.receiver = sentMessage.getReceiver();
	        this.title = sentMessage.getTitle();
	        this.text = sentMessage.getText();
	        this.is_hide = sentMessage.isIs_hide();
	        this.createAt = sentMessage.getCreateAt();;
	}

	public SentMessage(Long id, User sender, User receiver, String title, String text, boolean is_hide,
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
