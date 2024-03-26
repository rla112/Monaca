package com.dw.Monaca.service;

import java.util.List;
import java.util.Map;

import com.dw.Monaca.dto.ClassRoomDto;
import com.dw.Monaca.dto.ClassRoomUpdateDto;
import com.dw.Monaca.dto.LectureSummaryDto;
import com.dw.Monaca.dto.ResponseDto;
import com.dw.Monaca.dto.StudentProgressDto;
//import com.dw.Monaca.model.ClassRoom;
//import com.dw.Monaca.model.LectureCategory;

// 강의실, 현재 수강중인 강의만 보여지는 것
public interface ClassRoomService {

	// 기능 구현
	
	// progressRate(진도율)가(이)80% 가 된 강의들은 LectureHistory에 저장
	// ProgressRate(진도율)는(은) 한번 100%를 달성하면 다시 강의를 시청하더라도 ProgressRate는 더 이상은 변하지 않고 RecentViewing(강의 시청시간)만 0초로 초기화.)
	// Recent Viewing(최근 시청기록)은 매번 강의를 들을 때마다 새로 업데이트 됨. (User가 최근에 강의를 시청한 날짜와 시간이 기록으로 업데이트) 
	// 진도율이 80%가 되지 않은 강의를 삭제하고 다시 듣고 싶다면 결제를 해야 함. 이 부분은 사용자에게 명확하게 안내해주기!!
	
	// 현재 로그인한 유저의 ClassRoom을 전체 조회
	ResponseDto<List<ClassRoomDto>> getAllClassRoom();
	
	// 현재 로그인한 유저의 ClassRoom을 ClassRoom ID로 조회
	ResponseDto<ClassRoomDto> getClassRoomById(Long id);
		
	// 현재 로그인한 유저의 ClassRoom 삭제
	ResponseDto<String> deleteClassRoomById(Long id);
	
	// 현재 로그인한 유저의 ClassRoom의 진도율과 최근 시청기록을 업데이트
	ResponseDto<ClassRoomDto> updateClassRoom(Long id, ClassRoomUpdateDto classRoomDto);
	
	// 진도율100%로 업데이트 후 classRoom에서는 삭제, lectureHistory에 생성
	ResponseDto<ClassRoomDto> updateClassRoomAndAddLectureToClassRoom(Long id, ClassRoomUpdateDto classRoomUpdateDto) ;

//	// 현재 로그인한 유저의 진도율(progressRate)이 80%에 도달한 ClassRoom을 LectureHistory에 생성
//	ResponseDto<ClassRoom> createToLectureHistory(Long id);
	
	// LectureHistory에서 선택한 강의를 ClassRoom에 다시 추가
	ResponseDto<ClassRoomDto> addLectureToClassRoom(Long lectureHistoryId);

	// 교수가 강의하는 과목의 카테고리별로 학생들의 진도율을 조회하는 기능
	ResponseDto<Map<String, List<StudentProgressDto>>> getStudentProgressByProfessor();
	
	 // 특정 강의를 수강 중인 사람들의 수를 조회
	ResponseDto<Long> getParticipantCountByLectureId(Long lectureId);
	
	// 유/무료 강의 전체에서 카테고리별 + 강의명별 로 결제가 얼마나 되었는지 가져오기
	ResponseDto<List<LectureSummaryDto>> getLectureSummary();

	//유료 강의 전체에서 카테고리별 + 강의명별 결제가 얼마나 되었는지
	ResponseDto<List<LectureSummaryDto>> getLectureSummaryByPaid();
	
	// 무료 강의 전체에서 카테고리별 + 강의명별 결제가 얼마나 되었는지 
	ResponseDto<List<LectureSummaryDto>> getLectureSummaryByFree();

	
	

}
