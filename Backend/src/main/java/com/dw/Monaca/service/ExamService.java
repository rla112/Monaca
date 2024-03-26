package com.dw.Monaca.service;


import java.util.List;

import com.dw.Monaca.dto.ExamDto;
import com.dw.Monaca.dto.ExamStudentDto;
import com.dw.Monaca.dto.ResponseDto;
import com.dw.Monaca.model.Exam;

// 시험 문항
public interface ExamService {

	
	// 전체 Exam 가져오기
	ResponseDto<List<Exam>> getAllExam();

	// Exam로ID 특정 Exam만 가져오기
	ResponseDto<Exam> getExamByExamId(Long examId);
	
	//같은 lecture에 속해있는 Exam 가져오기
	ResponseDto<List<Exam>> getExamByLectureName(String lectureName);
	
	// 학생이 시험 볼 때 Exam 가져오기
	ResponseDto<List<ExamStudentDto>> getExamByLectureNameForStudent(String lectureName);
	
	// Exam 생성
	ResponseDto<Exam> addExam( ExamDto examDto);

	// Exam 수정
	ResponseDto<Exam> updateExam(Long examId, ExamDto updateExam);
	
	// ExamID로 삭제 
	ResponseDto<String> deleteExamByExamId(Long examId);
	
	// 같은 Lecture의 Exam 삭제
	ResponseDto<String> deleteExamByLectureName(String lectureName);


}