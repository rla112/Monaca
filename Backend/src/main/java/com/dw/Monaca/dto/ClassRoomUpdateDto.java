package com.dw.Monaca.dto;

import java.time.LocalDateTime;

// classRoom의 진도율, 최근 강의 시청 시간, 최근 강의 시청 기록을 업데이트 하기 위해 필요함! 
public class ClassRoomUpdateDto {

	
	private int progressRate;
	
	private int viewingRecord;
	
	private LocalDateTime recentViewing;

	
	public ClassRoomUpdateDto() {
		super();
	}


	public ClassRoomUpdateDto(int progressRate, int viewingRecord, LocalDateTime recentViewing) {
		super();
		this.progressRate = progressRate;
		this.viewingRecord = viewingRecord;
		this.recentViewing = recentViewing;
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


	public LocalDateTime getRecentViewing() {
		return recentViewing;
	}


	public void setRecentViewing(LocalDateTime recentViewing) {
		this.recentViewing = recentViewing;
	}



}
