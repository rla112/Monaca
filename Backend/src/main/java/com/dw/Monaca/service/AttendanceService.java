package com.dw.Monaca.service;

import java.util.List;

import com.dw.Monaca.dto.ResponseDto;
import com.dw.Monaca.model.Attendance;

// 출석률 관리 
public interface AttendanceService {
//	// 기능 구현 예정
//	// get 메소드, Attendance get으로 모두 가져오기

	// 현재 로그인한 유저의 출석이력 생성
	ResponseDto<Attendance> saveAttendance(Attendance attendance);
	
	//모든 사용자의 출석 목록을 가져오기
	ResponseDto<List<Attendance>> getAllAttendance();
	
	// 모든 사용자의 년도별 출석 리스트 조회
	ResponseDto<List<Attendance>> getAttendanceByTimeStampOfYear(int year);
	
	// 모든 사용자의 월별 출석 리스트 조회
	ResponseDto<List<Attendance>> getAttendanceByTimeStampOfMonth(int year, int month);
	
	// 모든 사용자의 주별 출석 리스트 조회
	ResponseDto<List<Attendance>> getAttendanceByTimeStampOfWeek(int year, int month, int week);
	
	// 모든 사용자의 일별 출석 리스트 조회
	ResponseDto<List<Attendance>> getAttendanceByTimeStampOfDay(int year, int month, int day);

	// 현재 로그인한 유저의 출석 목록을 가져오기
	ResponseDto<List<Attendance>> getAttendanceByUser();

	// 현재 로그인한 유저의 년도별 출석 리스트 조회
	ResponseDto<List<Attendance>> getAttendanceByUserIdAndTimeStampOfYear(int year);

	// 현재 로그인한 유저의 월별 출석 리스트 조회
	ResponseDto<List<Attendance>> getAttendanceByUserIdAndTimeStampOfMonth(int year, int month);

	// 현재 로그인한 유저의 주별 출석 리스트 조회
	ResponseDto<List<Attendance>> getAttendanceByUserIdAndTimeStampOfWeek(int year, int month, int week);

	// 현재 로그인한 유저의 일별 출석 리스트 조회
	ResponseDto<List<Attendance>> getAttendanceByUserIdAndTimeStampOfDay(int year, int month, int day);


	// 특정 유저의 LoginId로 출석 목록을 가져오기
	ResponseDto<List<Attendance>> getAttendanceByLoginId(String loginId);

	// 특정 유저의 LoginId로 년도별 출석 리스트 조회
	ResponseDto<List<Attendance>> getAttendanceByLoginIdIdAndTimeStampOfYear(String loginId, int year);

	// 특정 유저의 LoginId로 월별 출석 리스트 조회
	ResponseDto<List<Attendance>> getAttendanceByLoginIdIdAndTimeStampOfMonth(String loginId, int year, int month);

	// 특정 유저의 LoginId로 주별 출석 리스트 조회
	ResponseDto<List<Attendance>> getAttendanceByLoginIdIdAndTimeStampOfWeek(String loginId, int year, int month, int week);

	// 특정 유저의 LoginId로 일별 출석 리스트 조회
	ResponseDto<List<Attendance>> getAttendanceByLoginIdIdAndTimeStampOfDay(String loginId, int year, int month, int day);
	

}