package com.dw.Monaca.dto;

public class LectureHistoryDto {

	private Long id;
	
	private String userName;
	
	private String image;
		
	private String lectureName;

	private String subTitle;
	
	private int progressRate;
	
	private String lastDate;

	public LectureHistoryDto() {
		super();
	}

	public LectureHistoryDto(Long id, String userName, String image, String lectureName, String subTitle,
			int progressRate, String lastDate) {
		super();
		this.id = id;
		this.userName = userName;
		this.image = image;
		this.lectureName = lectureName;
		this.subTitle = subTitle;
		this.progressRate = progressRate;
		this.lastDate = lastDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public int getProgressRate() {
		return progressRate;
	}

	public void setProgressRate(int progressRate) {
		this.progressRate = progressRate;
	}

	public String getLastDate() {
		return lastDate;
	}

	public void setLastDate(String lastDate) {
		this.lastDate = lastDate;
	}

	
}
