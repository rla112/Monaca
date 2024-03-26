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

// lectureCategory => 4개(키오스크, 모바일, 웹사이트, 국가복지 ), total lectures => 20개 ( 각 카테고리별 5개씩 )

@Entity
@Table(name = "lecture")
public class Lecture {

	@Id // ID라는 것을 인식시켜주고 id값을 넣지 않아도 오류가 나지 않음!!
	@Column(name = "lecture_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY) // databases마다 만드는 방법이 달라서 표기해줘야 함!
	private Long id;

	// 작성자가 professor나 admin 둘 다가 될 수 있기 때문에 누가 썼는지 식별할 필요가 있음
	@ManyToOne
	private User author;
	
	// 강의 카테고리
	@ManyToOne
	private LectureCategory lectureCategory;

	// 강의 이름
	@Column(name = "lectureName", length = 50, unique = true)
	private String lectureName;

	@ManyToOne
	private User professor;
	
	
	private String subTitle;
	
	// 강의 상세 설명(학습내용 엿보기 전까지)
	@Column
	private String lectureDescription1;

	// 학습내용 엿보기
	@Column
	private String lectureDescription2;
	
	// 수강 전 확인하기
	@Column
	private String lectureDescription3;
	
	// 초록 칠판 강의 의지
	@Column
	private String lectureDescription4;

	// 강의 학습 목표
	@Column
	private String learnigObjectives;

	// 강의 구성
	@Column
	private String lectureStructure;

	// 추천 대상
	@Column
	private String courseTarget;
	
	// 듣기 전 필요한 지식
	@Column
	private String beforeListening;
	
	// 강의 총 재생 시간
	@Column(name = "lecturePlayTime", length = 100)
	private int lecturePlayTime;

	// 강의 사진
	@Column(name = "image", length = 500)
	private String image;

	// 가격
	@Column(name = "price", length = 20)
	private Integer price;

	// 강의 비디오
	@Column(name = "video")
	private String video;

	// 강의 생성 날짜
	@Column(name = "create_at")
	private LocalDateTime createAt;
	
	
	public Lecture() {
		super();
	}


	public Lecture(Long id, User author, LectureCategory lectureCategory, String lectureName, User professor,
			String subTitle, String lectureDescription1, String lectureDescription2, String lectureDescription3,
			String lectureDescription4, String learnigObjectives, String lectureStructure, String courseTarget,
			String beforeListening, int lecturePlayTime, String image, Integer price, String video,
			LocalDateTime createAt) {
		super();
		this.id = id;
		this.author = author;
		this.lectureCategory = lectureCategory;
		this.lectureName = lectureName;
		this.professor = professor;
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


	public User getAuthor() {
		return author;
	}


	public void setAuthor(User author) {
		this.author = author;
	}


	public LectureCategory getLectureCategory() {
		return lectureCategory;
	}


	public void setLectureCategory(LectureCategory lectureCategory) {
		this.lectureCategory = lectureCategory;
	}


	public String getLectureName() {
		return lectureName;
	}


	public void setLectureName(String lectureName) {
		this.lectureName = lectureName;
	}


	public User getProfessor() {
		return professor;
	}


	public void setProfessor(User professor) {
		this.professor = professor;
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


	public LocalDateTime getCreateAt() {
		return createAt;
	}


	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}



}
