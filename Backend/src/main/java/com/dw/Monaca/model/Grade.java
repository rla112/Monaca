package com.dw.Monaca.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "grade")
public class Grade {
	
	@Id // ID라는 것을 인식시켜주고 id값을 넣지 않아도 오류가 나지 않음!!
	@Column(name = "grade_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY) // databases마다 만드는 방법이 달라서 표기해줘야 함!
	private Long id;
	
	@Column(name = "gradeName", length = 3) // A, B, C, D
	private String gradeName;
	
	
	// A등급은 90점 이상부터 100점 이하까지
	// B등급은 75점 이상부터 90점 미만까지
	// C등급은 60점 이상부터 75점 미만까지
	// D등급은 60미만
	
	// 최소 점수
	@Column(name = "minScore", length = 10)
    private int minScore;
	
    // 최대 점수
	@Column(name = "maxScore", length = 10)
	private int maxScore;

	
	public Grade() {
		super();
	}


	public Grade(Long id, String gradeName, int minScore, int maxScore) {
		super();
		this.id = id;
		this.gradeName = gradeName;
		this.minScore = minScore;
		this.maxScore = maxScore;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getGradeName() {
		return gradeName;
	}


	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}


	public int getMinScore() {
		return minScore;
	}


	public void setMinScore(int minScore) {
		this.minScore = minScore;
	}


	public int getMaxScore() {
		return maxScore;
	}


	public void setMaxScore(int maxScore) {
		this.maxScore = maxScore;
	}

	

}
