package com.dw.Monaca.service;

import java.util.List;
import java.util.Map;

import com.dw.Monaca.dto.ResponseDto;
import com.dw.Monaca.model.UserLectureGrade;

public interface UserLectureGradeService{

	// 검색 기능으로 원하는 강의 성적만 불러올 수 있게 함
	// exam의 Grade가 A이면 candy_point 생성 되도록 하는 코드 작성!!! => GradeServiceImpl에 작성완료
	
	
	// 모든 UserLectureGrade를 User별로 불러오기
	ResponseDto<Map<String, List<UserLectureGrade>>> getAllUserLectureGrade();
	
	// 특정 Lecture의 모든 UserLectureGrade를 user별로 불러오기
	ResponseDto<Map<String, List<UserLectureGrade>>> getUserLectureGradeByLecture(String lectureName);
	
	// 현재 로그인한 사용자의 특정 Lecture UserLectureGrade 불러오기
	ResponseDto<UserLectureGrade> getUserLectureGradeByLectureForUser(String lectureName);
	
	// 현재 로그인한 사용자의 UserLectureGrade 모두 불러오기
	ResponseDto<List<UserLectureGrade>> getUserLectureGradeForUser();
	
}
