package com.dw.Monaca.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.dw.Monaca.jwtauthority.model.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


// 학생의 답안지
@Entity
@Table(name = "examination")
public class Examination {
	
	@Id 
	@Column(name = "examination_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//시험 본 학생
	@ManyToOne
	private User user;
	
	// 문제
	@ManyToOne
	private Exam exam;

	// 학생이 제출한 문제의 답안
	@Column(name = "answerText", length = 1)
	private int answerText;
	
	// 정답인지 오답인지 채점 됨
	@Column(name = "isCorrect")
	private boolean isCorrect;

	// 시험 응시날짜
	@CreationTimestamp
	@Column(nullable = false, updatable = false)
	private LocalDateTime date;
	
	public Examination() {
		super();
	}

	public Examination(Long id, User user, Exam exam, int answerText, boolean isCorrect, LocalDateTime date) {
		super();
		this.id = id;
		this.user = user;
		this.exam = exam;
		this.answerText = answerText;
		this.isCorrect = isCorrect;
		this.date = date;
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

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public int getAnswerText() {
		return answerText;
	}

	public void setAnswerText(int answerText) {
		this.answerText = answerText;
	}

	public boolean isCorrect() {
		return isCorrect;
	}

	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

}
