package com.dw.Monaca.dto;

import jakarta.validation.constraints.NotNull;

public class LectureCartDto {

	private Long id;
	
	private String user;
	
	 @NotNull(message = "강의 ID는 필수입니다.")
	private Long lectureId; // User가 LectureCart에 추가하고자 하는 Lecture의 ID

	public LectureCartDto() {
		super();
	}

	public LectureCartDto(Long id, String user, @NotNull(message = "강의 ID는 필수입니다.") Long lectureId) {
		super();
		this.id = id;
		this.user = user;
		this.lectureId = lectureId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Long getLectureId() {
		return lectureId;
	}

	public void setLectureId(Long lectureId) {
		this.lectureId = lectureId;
	}

	@Override
	public String toString() {
		return "LectureCartDto [id=" + id + ", user=" + user + ", lectureId=" + lectureId + "]";
	}
	
	

	

	
}
