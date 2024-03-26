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
@Table(name = "q_and_a")
public class QandA {

	@Id // ID라는 것을 인식시켜주고 id값을 넣지 않아도 오류가 나지 않음!!
	@Column(name = "q_and_a_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY) // databases마다 만드는 방법이 달라서 표기해줘야 함!
	private Long id;

	@ManyToOne
	private User author;

	@ManyToOne
	private Lecture lecture;

	@Column(name = "title", length = 50)
	private String title;   

	@Column(name = "text", length = 1000)
	private String text;

	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime createAt;

	@Column(name = "disposablePw", length = 10)
	private String disposablePw;

	// 상태 표시 (새로운 Q&A가 생성이되고 해당 Q&A에 Reply가 달리기 전까지 표시)
	@Column(name = "new_status", nullable = false)
	    private boolean newStatus = true;
	
	public QandA() {
		super();
	}

	public QandA(Long id, User author, Lecture lecture, String title, String text, LocalDateTime createAt,
			String disposablePw, boolean newStatus) {
		super();
		this.id = id;
		this.author = author;
		this.lecture = lecture;
		this.title = title;
		this.text = text;
		this.createAt = createAt;
		this.disposablePw = disposablePw;
		this.newStatus = newStatus;
	}

	// 읽음 표시 상태( New 해제) // 캡슐화 // Reply 생성 시에 이 메소드를 호출하면 됨
    public void markAsRead() {
        this.newStatus = false;
    }
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public Lecture getLecture() {
		return lecture;
	}

	public void setLecture(Lecture lecture) {
		this.lecture = lecture;
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

	public String getDisposablePw() {
		return disposablePw;
	}

	public void setDisposablePw(String disposablePw) {
		this.disposablePw = disposablePw;
	}

	public boolean isNewStatus() {
		return newStatus;
	}

	public void setNewStatus(boolean newStatus) {
		this.newStatus = newStatus;
	}

	

}
