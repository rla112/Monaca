package com.dw.Monaca.dto;

public class LectureCategoryDto {
	private Long id;
	private String categoryName;
	private String categoryDescription1;
	private String categoryDescription2;
	
	public LectureCategoryDto() {
		super();
	}

	public LectureCategoryDto(Long id, String categoryName, String categoryDescription1, String categoryDescription2) {
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
