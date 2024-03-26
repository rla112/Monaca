package com.dw.Monaca.model;

import com.dw.Monaca.jwtauthority.model.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

// 일시적으로 사용되는 entity
@Entity 
@Table(name = "`lecture_cart`")
public class LectureCart {

	@Id
	@Column(name = "lecture_cart_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Lecture lecture;

	public LectureCart() {
		super();
	}

	public LectureCart(Long id, User user, Lecture lecture) {
		super();
		this.id = id;
		this.user = user;
		this.lecture = lecture;
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
	
	
}
