package com.dw.Monaca.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "rating")
public class Rating {

	
	@Id
	@Column(name = "candy_grade")
	private String candyGrade;

	@Column(name = "image")
	private String image;

	public Rating() {
		super();
	}

	public Rating(String candyGrade, String image) {
		super();
		this.candyGrade = candyGrade;
		this.image = image;
	}

	public String getCandyGrade() {
		return candyGrade;
	}

	public void setCandyGrade(String candyGrade) {
		this.candyGrade = candyGrade;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
