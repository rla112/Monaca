package com.dw.Monaca.service;

import java.util.List;

import com.dw.Monaca.dto.ExaminationDto;
import com.dw.Monaca.dto.ResponseDto;
import com.dw.Monaca.jwtauthority.model.User;
import com.dw.Monaca.model.Examination;
import com.dw.Monaca.model.Lecture;

//인터페이스의 모든 메서드는 기본적으로 public이다.
public interface ExaminationService {
	// 기능 구현
	
	
	// 전체 Examination 불러오기
	ResponseDto<List<Examination>> getAllExamination();

	// Lecture별 Examination 불러오기
	ResponseDto<List<Examination>> getExaminationByLecture(String lectureName);

	// User별 Examination 불러오기
	ResponseDto<List<Examination>> getExaminationByUserLoginId(String loginId);
	
	// 현재 로그인한 유저의 Examination 불러오기
	ResponseDto<List<Examination>> getExaminationByUser();
	
	// 특정 User의 특정한 Lecture의 Examination 불러오기
	ResponseDto<List<Examination>> getExaminationByLectureForUserLoginId(String loginId, String lectureName);
	
	// 현재 로그인한 유저의 특정한 Lecture의 Examination 불러오기
	ResponseDto<List<Examination>> getExaminationByLectureForUser(String lectureName);
	
	// examination 생성(학생이 답안지 입력) // 생성되는 순간 채점 결과 저장도 같이 이루어짐.
	ResponseDto<Examination> createExamination(Long ExamId,ExaminationDto examinationDto);

	// 시험 점수를 계산함.(User와 Lecture을 파라미터로 받아와 해당 User의 해당 Lecture에 대한 점수를 계산) 
	int calculateScore(User user, Lecture lecture);



}