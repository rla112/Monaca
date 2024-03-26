package com.dw.Monaca.service;

import java.util.List;

import com.dw.Monaca.dto.LectureHistoryDto;
import com.dw.Monaca.dto.ResponseDto;
//import com.dw.Monaca.model.LectureHistory;

// 강의 이력
public interface LectureHistoryService {

	// 기능 구현 예정
	// ClassRoom에서 진도율 80%가 된 강의들만 추가됨.
	// get 메소드 사용
	
	
	
	// 현재 로그인한 유저의 LectureHistory를 전체 조회
	ResponseDto<List<LectureHistoryDto>> getAllLectureHistory();
	
	// 현재 로그인한 유저의 LectureHistory ID로 조회
	ResponseDto<LectureHistoryDto> getLectureHistoryById(Long id);

	
	
	
}
