package com.dw.Monaca.model;

import java.time.LocalDateTime;

import com.dw.Monaca.dto.ClassRoomUpdateDto;
import com.dw.Monaca.jwtauthority.model.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "class_room")
public class ClassRoom {
	
	@Id
	@Column(name = "class_room_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	
	// 강의를 듣는 user
	@ManyToOne
	private User user;
	
	// 강의 정보
	@ManyToOne
	private Lecture lecture;
	
	// 진도율
	@Column(name = "progressRate")
	private int progressRate;

	// 강의 시청 기록( 강의영상 시청 시간 기록(영상을 보고 정지한 마지막 구간) )
	@Column(name = "viewingRecord")
	private int viewingRecord;
	
	// 최근 시청 기록
	@Column(name = "recentViewing")
	private LocalDateTime recentViewing;

	
	public ClassRoom() {
		super();
	}


	public ClassRoom(Long id, User user, Lecture lecture, int progressRate, int viewingRecord,
			LocalDateTime recentViewing) {
		super();
		this.id = id;
		this.user = user;
		this.lecture = lecture;
		this.progressRate = progressRate;
		this.viewingRecord = viewingRecord;
		this.recentViewing = recentViewing;
	}


	// updateClassRoom 객체를 받아 필드를 업데이트하는 메서드를 추가 => 코드의 가독성과 유지 관리성을 높임
	// 이 메서드는 ClassRoomUpdateDto 객체를 인자로 받아 ClassRoom 객체의 필드를 업데이트하는 역할
	public void update(ClassRoomUpdateDto updateClassRoom) {
	    if (updateClassRoom.getProgressRate() > this.progressRate) { 
	    	// ClassRoomUpdateDto의 진도율이 현재 ClassRoom의 진도율보다 클 경우에만 진도율을 업데이트하도록 하기 위함
	        this.progressRate = updateClassRoom.getProgressRate();
	    }
        this.viewingRecord = updateClassRoom.getViewingRecord();
        this.recentViewing = LocalDateTime.now();
    }


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Lecture getLecture() {
		return lecture;
	}


	public void setLecture(Lecture lecture) {
		this.lecture = lecture;
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
