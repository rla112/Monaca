package com.dw.Monaca.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "lecture_category")
public class LectureCategory {
	
	@Id // ID라는 것을 인식시켜주고 id값을 넣지 않아도 오류가 나지 않음!!
	@Column(name = "lecture_category_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY) // databases마다 만드는 방법이 달라서 표기해줘야 함!
	private Long id;

	@Column(name = "categoryName", length = 50)
	private String categoryName;
	
	@Column
	private String categoryDescription1;
	
	@Column
	private String categoryDescription2;
	
	public LectureCategory() {
		super();
	}

	public LectureCategory(Long id, String categoryName, String categoryDescription1, String categoryDescription2) {
		super();
		this.id = id;
		this.categoryName = categoryName;
		this.categoryDescription1 = categoryDescription1;
		this.categoryDescription2 = categoryDescription2;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryDescription1() {
		return categoryDescription1;
	}

	public void setCategoryDescription1(String categoryDescription1) {
		this.categoryDescription1 = categoryDescription1;
	}

	public String getCategoryDescription2() {
		return categoryDescription2;
	}

	public void setCategoryDescription2(String categoryDescription2) {
		this.categoryDescription2 = categoryDescription2;
	}

	
}
