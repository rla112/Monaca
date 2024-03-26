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

@Entity
@Table(name = "lecture_history")
public class LectureHistory {

	@Id 
	@Column(name = "lecture_history_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY) // databases마다 만드는 방법이 달라서 표기해줘야 함!
	private Long id;

	@ManyToOne
	private User user;

	@ManyToOne 
	private Lecture lecture; 

	// 진도율( 여기서는 모두 다 100% 임 )
	@Column
	private int progressRate;
	
	// 최근 시청 날짜
	@Column(name = "lastDate", length = 50)
	private LocalDateTime lastDate;
	
	public LectureHistory() {
		super();
	}

	public LectureHistory(Long id, User user, Lecture lecture, int progressRate, LocalDateTime lastDate) {
		super();
		this.id = id;
		this.user = user;
		this.lecture = lecture;
		this.progressRate = progressRate;
		this.lastDate = lastDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Lecture getLecture() {
		return lecture;
	}

	public void setLecture(Lecture lecture) {
		this.lecture = lecture;
	}

	public int getProgressRate() {
		return progressRate;
	}

	public void setProgressRate(int progressRate) {
		this.progressRate = progressRate;
	}

	public LocalDateTime getLastDate() {
		return lastDate;
	}

	public void setLastDate(LocalDateTime lastDate) {
		this.lastDate = lastDate;
	}


}
