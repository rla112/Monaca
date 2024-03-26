package com.dw.Monaca.model;

import java.time.LocalDateTime;

import org.springframework.scheduling.annotation.EnableScheduling;

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

@EnableScheduling
@Entity
@Table(name = "reply")
public class Reply {

	@Id
	@Column(name = "reply_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "title", length = 30)
	private String title;

	@Column(name = "text", length = 5000)
	private String text;

	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime createAt;
	
	@ManyToOne
	private User replyAuthor;

	@ManyToOne
	private QandA qandA;
	
	// 상태 표시 (새로운 Reply가 생성되고 생성 시점으로부터 7일 이후부터는 NEW 표시가 사라짐)
	@Column(name = "new_status", nullable = false)
	    private boolean newStatus = true;

	public Reply() {
		super();
	}

	public Reply(Long id, String title, String text, LocalDateTime createAt, User replyAuthor, QandA qandA,
			boolean newStatus) {
		super();
		this.id = id;
		this.title = title;
		this.text = text;
		this.createAt = createAt;
		this.replyAuthor = replyAuthor;
		this.qandA = qandA;
		this.newStatus = newStatus;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public User getReplyAuthor() {
		return replyAuthor;
	}

	public void setReplyAuthor(User replyAuthor) {
		this.replyAuthor = replyAuthor;
	}

	public QandA getQandA() {
		return qandA;
	}

	public void setQandA(QandA qandA) {
		this.qandA = qandA;
	}

	public boolean isNewStatus() {
		return newStatus;
	}

	public void setNewStatus(boolean newStatus) {
		this.newStatus = newStatus;
	}


}
