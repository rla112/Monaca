package com.dw.Monaca.dto;

import jakarta.validation.constraints.NotNull;

public class LectureDto {

	private Long id;
	
	private String author;

	@NotNull
	private String lectureCategory;
	
	@NotNull
	private String lectureName;
	

	private String professor;
	
	private String professorLoginId;

	private String subTitle;
	
	// 강의 상세 설명(학습내용 엿보기 전까지)
	private String lectureDescription1;

	// 학습내용 엿보기
	private String lectureDescription2;
		
	// 수강 전 확인하기
	private String lectureDescription3;
	
	// 초록 칠판 강의 의지
	private String lectureDescription4;

	// 강의 학습 목표
	private String learnigObjectives;

	// 강의 구성
	private String lectureStructure;

	// 추천 대상
	private String courseTarget;
		
	// 듣기 전 필요한 지식
	private String beforeListening;
	
	// 강의 총 재생 시간(초단위)
	@NotNull
	private int lecturePlayTime;
	
	// 강의 사진
	@NotNull
	private String image;
	
	// 가격
	@NotNull
	private Integer price;
	
	// 강의 비디오
	@NotNull
	private String video;
	
	// 강의 생성 날짜
	private String createAt;

	
	public LectureDto() {
		super();
	}


	public LectureDto(Long id, String author, @NotNull String lectureCategory, @NotNull String lectureName,
			String professor, String professorLoginId, String subTitle, String lectureDescription1,
			String lectureDescription2, String lectureDescription3, String lectureDescription4,
			String learnigObjectives, String lectureStructure, String courseTarget, String beforeListening,
			@NotNull int lecturePlayTime, @NotNull String image, @NotNull Integer price, @NotNull String video,
			String createAt) {
		super();
		this.id = id;
		this.author = author;
		this.lectureCategory = lectureCategory;
		this.lectureName = lectureName;
		this.professor = professor;
		this.professorLoginId = professorLoginId;
		this.subTitle = subTitle;
		this.lectureDescription1 = lectureDescription1;
		this.lectureDescription2 = lectureDescription2;
		this.lectureDescription3 = lectureDescription3;
		this.lectureDescription4 = lectureDescription4;
		this.learnigObjectives = learnigObjectives;
		this.lectureStructure = lectureStructure;
		this.courseTarget = courseTarget;
		this.beforeListening = beforeListening;
		this.lecturePlayTime = lecturePlayTime;
		this.image = image;
		this.price = price;
		this.video = video;
		this.createAt = createAt;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
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


	public String getProfessor() {
		return professor;
	}


	public void setProfessor(String professor) {
		this.professor = professor;
	}


	public String getProfessorLoginId() {
		return professorLoginId;
	}


	public void setProfessorLoginId(String professorLoginId) {
		this.professorLoginId = professorLoginId;
	}


	public String getSubTitle() {
		return subTitle;
	}


	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}


	public String getLectureDescription1() {
		return lectureDescription1;
	}


	public void setLectureDescription1(String lectureDescription1) {
		this.lectureDescription1 = lectureDescription1;
	}


	public String getLectureDescription2() {
		return lectureDescription2;
	}


	public void setLectureDescription2(String lectureDescription2) {
		this.lectureDescription2 = lectureDescription2;
	}


	public String getLectureDescription3() {
		return lectureDescription3;
	}


	public void setLectureDescription3(String lectureDescription3) {
		this.lectureDescription3 = lectureDescription3;
	}


	public String getLectureDescription4() {
		return lectureDescription4;
	}


	public void setLectureDescription4(String lectureDescription4) {
		this.lectureDescription4 = lectureDescription4;
	}


	public String getLearnigObjectives() {
		return learnigObjectives;
	}


	public void setLearnigObjectives(String learnigObjectives) {
		this.learnigObjectives = learnigObjectives;
	}


	public String getLectureStructure() {
		return lectureStructure;
	}


	public void setLectureStructure(String lectureStructure) {
		this.lectureStructure = lectureStructure;
	}


	public String getCourseTarget() {
		return courseTarget;
	}


	public void setCourseTarget(String courseTarget) {
		this.courseTarget = courseTarget;
	}


	public String getBeforeListening() {
		return beforeListening;
	}


	public void setBeforeListening(String beforeListening) {
		this.beforeListening = beforeListening;
	}


	public int getLecturePlayTime() {
		return lecturePlayTime;
	}


	public void setLecturePlayTime(int lecturePlayTime) {
		this.lecturePlayTime = lecturePlayTime;
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


	public String getVideo() {
		return video;
	}


	public void setVideo(String video) {
		this.video = video;
	}


	public String getCreateAt() {
		return createAt;
	}


	public void setCreateAt(String createAt) {
		this.createAt = createAt;
	}


	
}
