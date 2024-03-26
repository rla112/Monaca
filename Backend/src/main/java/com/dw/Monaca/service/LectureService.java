package com.dw.Monaca.service;
// 강의

import java.util.List;

import com.dw.Monaca.dto.LectureDto;
import com.dw.Monaca.dto.LectureListDto;
import com.dw.Monaca.dto.ProfessorDto;
import com.dw.Monaca.dto.ResponseDto;
import com.dw.Monaca.model.Lecture;

// 인터페이스의 모든 메서드는 기본적으로 public이다.
public interface LectureService {
	
	// 모든 Lecture 불러오기
	ResponseDto<List<LectureListDto>> getAllLecture();
	
	// Lecture 카테고리 별 불러오기
	ResponseDto<List<LectureListDto>> getAllLectureByCategoryName(String categoryName);
	
	// professor 별 불러오기
	ResponseDto<List<LectureListDto>> getAllLectureByProfessor(String professorName);
	
	// 유료강의만 불러오기
	ResponseDto<List<LectureListDto>> getAllLectureByPaidLectures();

	//무료 강의만 불러오기
	ResponseDto<List<LectureListDto>> getAllLectureByFreeLectures();
	
	// LecureID로 특정 강의 불러오기
	ResponseDto<LectureDto> getLectureById(Long id);
	
	// Lecture 업로드
	ResponseDto<Lecture> createLecture(LectureDto lectureDto);
	
	// LectrueID로 특정 강의 삭제
	ResponseDto<String> deleteLectureById(Long id);
	
	// LectureID로 특정 강의의 교수 불러오기
	ResponseDto<ProfessorDto> getProfessorById(Long id);
	
	// LectureCategory로 특정 교수 리스트 불러오기
	ResponseDto<List<ProfessorDto>> getProfessorByLectureCategoryName(String lectureCategoryName);
	
//	// LectureID로 특정 강의 수정
//	public ResponseDto<Lecture> updateLectureById(Lecture updateLecture, Long id);


	
}
