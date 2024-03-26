package com.dw.Monaca.dto;

import java.util.List;

import jakarta.validation.constraints.NotNull;

public class LectureListDto {

private Long id;
	
	// 강의 카테고리
	@NotNull
	private String lectureCategory;
	
	// 강의 제목
	@NotNull
	private String lectureName;
	
	// 강의 상세 설명 (부제목)
	private String subTitle;
	
	// 과목 담당 교수
	private String professor;
			
	// 강의 메인 이미지
	@NotNull
	private String image;

	// 강의 가격
	private Integer price;
	
	private List<LectureCategoryDto> lectureCategoryList;
	
	public LectureListDto() {
		super();
	}

	public LectureListDto(Long id, @NotNull String lectureCategory, @NotNull String lectureName, String subTitle,
			String professor, @NotNull String image, Integer price, List<LectureCategoryDto> lectureCategoryList) {
		super();
		this.id = id;
		this.lectureCategory = lectureCategory;
		this.lectureName = lectureName;
		this.subTitle = subTitle;
		this.professor = professor;
		this.image = image;
		this.price = price;
		this.lectureCategoryList = lectureCategoryList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLectureCategory() {
		return lectureCategory;
	}

	public void setLectureCategory(String lectureCategory) {
		this.lectureCategory = lectureCategory;
	}

	public String getLectureName() {
		return lectureName;
	}

	public void setLectureName(String lectureName) {
		this.lectureName = lectureName;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public String getProfessor() {
		return professor;
	}

	public void setProfessor(String professor) {
		this.professor = professor;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}
	
	public List<LectureCategoryDto> getLectureCategoryList(){
		return lectureCategoryList;
	}

	public void setLectureCategoryList(List<LectureCategoryDto> lectureCategoryList) {
		this.lectureCategoryList = lectureCategoryList;
	}


}
