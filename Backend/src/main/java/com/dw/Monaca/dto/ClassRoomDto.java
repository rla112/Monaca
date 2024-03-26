package com.dw.Monaca.dto;

import java.time.LocalDateTime;

public class ClassRoomDto {

	private Long id;
	
	private Long lecture_id;
	
	private String user;
	
	private String image;
	
	private String lectureName;
	
	private String subTitle;
	
	private String video;
	
	private int progressRate;
	
	private int viewingRecord;
	
	private LocalDateTime recentVewing;

	public ClassRoomDto() {
		super();
	}

	public ClassRoomDto(Long id, Long lecture_id, String user, String image, String lectureName, String subTitle,
			String video, int progressRate, int viewingRecord, LocalDateTime recentVewing) {
		super();
		this.id = id;
		this.lecture_id = lecture_id;
		this.user = user;
		this.image = image;
		this.lectureName = lectureName;
		this.subTitle = subTitle;
		this.video = video;
		this.progressRate = progressRate;
		this.viewingRecord = viewingRecord;
		this.recentVewing = recentVewing;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getLecture_id() {
		return lecture_id;
	}

	public void setLecture_id(Long lecture_id) {
		this.lecture_id = lecture_id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public int getProgressRate() {
		return progressRate;
	}

	public void setProgressRate(int progressRate) {
		this.progressRate = progressRate;
	}

	public int getViewingRecord() {
		return viewingRecord;
	}

	public void setViewingRecord(int viewingRecord) {
		this.viewingRecord = viewingRecord;
	}

	public LocalDateTime getRecentVewing() {
		return recentVewing;
	}

	public void setRecentVewing(LocalDateTime recentVewing) {
		this.recentVewing = recentVewing;
	}

	
	
}