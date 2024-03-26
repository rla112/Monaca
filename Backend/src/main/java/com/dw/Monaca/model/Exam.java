package com.dw.Monaca.model;


import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

// 한 과목의 시험 한 문항임(id당 문항 1개) (같은 lecture를 가진 문항이 모여있어야 시험지가 됨)
// 시험 문제는 객관식
@Entity
@Table(name = "exam")
public class Exam {

	@Id // ID라는 것을 인식시켜주고 id값을 넣지 않아도 오류가 나지 않음!!
	@Column(name = "exam_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY) // databases마다 만드는 방법이 달라서 표기해줘야 함!
	private Long id;

	// 어떤 lecture의 시험문제인지
	@ManyToOne
	private Lecture lecture;
	
	// 시험 문제의 제목
	@Column(name = "examTitle", length = 50)
	private String examTitle;

	// 시험 문제
	@Column(name = "examText", length = 100)
	private String examText;

	// 시험 문제에 사용되는 Image
	@Column(name = "examImage", length = 2000)
	private String examImage;

	// 시험 문제의 보기(선지)
	@Column(name = "multipleChoice")
	private String multipleChoice;
	
	// 시험 문제의 정답 (채점의 기준이 되는 정답)
	@Column(name = "correctAnswer")
	private int correctAnswer;
	
	// 시험문제 생성 날짜
	// @CreationTimestamp 애노테이션이 있는 필드에는 Hibernate가 자동으로 현재 날짜와 시간을 할당함.
	// 이 어노테이션을 사용하면, Entity가 데이터베이스에 처음 저장될 때의 시간을 자동으로 기록할 수 있음.  
	@CreationTimestamp 
	@Column(nullable = false, updatable = false)
	private LocalDateTime createAt;

	public Exam() {
		super();
	}

	public Exam(Long id, Lecture lecture, String examTitle, String examText, String examImage, String multipleChoice,
			int correctAnswer) {
		super();
		this.id = id;
		this.lecture = lecture;
		this.examTitle = examTitle;
		this.examText = examText;
		this.examImage = examImage;
		this.multipleChoice = multipleChoice;
		this.correctAnswer = correctAnswer;
//		this.createAt = createAt; //@CreationTimestamp 어노테이션을 사용하고 있어서 Hibernate가 자동으로 현재 날짜와 시간을 할당해줌.객체를 생성할 때 createAt 필드를 따로 초기화해줄 필요가 없음. 
		// 객체를 생성할 때 createAt 필드를 따로 설정하지 않아도, 데이터가 데이터베이스에 저장될 때 현재 시간으로 자동 설정
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

	public String getExamTitle() {
		return examTitle;
	}

	public void setExamTitle(String examTitle) {
		this.examTitle = examTitle;
	}

	public String getExamText() {
		return examText;
	}

	public void setExamText(String examText) {
		this.examText = examText;
	}

	public String getExamImage() {
		return examImage;
	}

	public void setExamImage(String examImage) {
		this.examImage = examImage;
	}

	public String getMultipleChoice() {
		return multipleChoice;
	}

	public void setMultipleChoice(String multipleChoice) {
		this.multipleChoice = multipleChoice;
	}

	public int getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(int correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}
	
	
	
}
