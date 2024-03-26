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
@Table(name = "material")
public class Material {

	@Id
	@Column(name = "material_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Lecture lecture;
	
	@ManyToOne
	private User professor;
	
	@ManyToOne
	private User auther;
	
	@Column(name = "title", length = 30)
	private String title;

	@Column(name = "text", length = 5000)
	private String text;

	@Column(name = "image")
	private String image;

	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime createAt;

	@Column(name = "is_reservation")
	private boolean isReservation;

	public Material() {
		super();
	}

	public Material(Long id, Lecture lecture, User professor, User auther, String title, String text, String image,
			LocalDateTime createAt, boolean isReservation) {
		super();
		this.id = id;
		this.lecture = lecture;
		this.professor = professor;
		this.auther = auther;
		this.title = title;
		this.text = text;
		this.image = image;
		this.createAt = createAt;
		this.isReservation = isReservation;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Lecture getLecture() {
		return lecture;
	}

	public void setLecture(Lecture lecture) {
		this.lecture = lecture;
	}

	public User getProfessor() {
		return professor;
	}

	public void setProfessor(User professor) {
		this.professor = professor;
	}

	public User getAuther() {
		return auther;
	}

	public void setAuther(User auther) {
		this.auther = auther;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}

	public boolean isReservation() {
		return isReservation;
	}

	public void setReservation(boolean isReservation) {
		this.isReservation = isReservation;
	}


}
