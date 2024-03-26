package com.dw.Monaca.dto;

public class LectureSummaryDto {

	private String category;
	
	private String lectureName;
	
	private int totalPayment;
	
    private long participantCount; // 참가자 수를 저장하는 필드를 추가

	
	public LectureSummaryDto() {
		super();
	}


	public LectureSummaryDto(String category, String lectureName, int totalPayment, long participantCount) {
		super();
		this.category = category;
		this.lectureName = lectureName;
		this.totalPayment = totalPayment;
		this.participantCount = participantCount;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public String getLectureName() {
		return lectureName;
	}


	public void setLectureName(String lectureName) {
		this.lectureName = lectureName;
	}


	public int getTotalPayment() {
		return totalPayment;
	}


	public void setTotalPayment(int totalPayment) {
		this.totalPayment = totalPayment;
	}


	public long getParticipantCount() {
		return participantCount;
	}


	public void setParticipantCount(long participantCount) {
		this.participantCount = participantCount;
	}


	
}
