package com.dw.Monaca.model;

import com.dw.Monaca.jwtauthority.model.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_lecture_grade")
public class UserLectureGrade {

	@Id
	@Column(name = "user_lecture_grade_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Grade grade;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Lecture lecture;

	// Lecture의 score
	private int score;  // 추가된 점수 필드
	
	public UserLectureGrade() {
		super();
	}

	public UserLectureGrade(User user, Lecture lecture) {
	    this.user = user;
	    this.lecture = lecture;
	}

	public UserLectureGrade(Long id, Grade grade, User user, Lecture lecture, int score) {
		super();
		this.id = id;
		this.grade = grade;
		this.user = user;
		this.lecture = lecture;
		this.score = score;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
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

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	
}
