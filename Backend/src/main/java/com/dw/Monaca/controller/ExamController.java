package com.dw.Monaca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dw.Monaca.dto.ExamDto;
import com.dw.Monaca.dto.ExamStudentDto;
import com.dw.Monaca.dto.ResponseDto;
import com.dw.Monaca.model.Exam;
import com.dw.Monaca.service.impl.ExamServiceImpl;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:3000", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
public class ExamController {

	private final ExamServiceImpl examServiceImpl;

	@Autowired
	public ExamController(ExamServiceImpl examServiceImpl) {
		this.examServiceImpl = examServiceImpl;
	}

// 전체 Exam 가져오기
	@GetMapping("api/Exam/all")
	@PreAuthorize("hasAnyRole('ADMIN')")
	ResponseEntity<ResponseDto<List<Exam>>> getAllExam() {

		return new ResponseEntity<>(examServiceImpl.getAllExam(), HttpStatus.OK);

	}

// Exam로ID 특정 Exam만 가져오기
	@GetMapping("api/exam/{examId}")
	@PreAuthorize("hasAnyRole('ADMIN','PROFESSOR')")
	ResponseEntity<ResponseDto<Exam>> getExamByExamId(@PathVariable(name = "examId") Long examId) {

		return new ResponseEntity<>(examServiceImpl.getExamByExamId(examId), HttpStatus.OK);

	}

//같은 lecture에 속해있는 Exam 가져오기
	@GetMapping("api/exam/{lectureName}")
	@PreAuthorize("hasAnyRole('ADMIN','PROFESSOR')")
	ResponseEntity<ResponseDto<List<Exam>>> getExamByLectureName(
			@PathVariable(name = "lectureName") String lectureName) {

		return new ResponseEntity<>(examServiceImpl.getExamByLectureName(lectureName), HttpStatus.OK);

	}

// 학생이 시험 볼 때 Exam 가져오기
	@GetMapping("api/exam/take/{lectureName}")
	@PreAuthorize("hasAnyRole('ADMIN','PROFESSOR','USER')")
	ResponseEntity<ResponseDto<List<ExamStudentDto>>> getExamByLectureNameForStudent(
			@PathVariable(name = "lectureName") String lectureName) {

		return new ResponseEntity<>(examServiceImpl.getExamByLectureNameForStudent(lectureName), HttpStatus.OK);

	}

// Exam 생성
	@PostMapping("api/exam")
	@PreAuthorize("hasAnyRole('ADMIN','PROFESSOR')")
	ResponseEntity<ResponseDto<Exam>> addExam(@RequestBody @Valid ExamDto examDto) {

		return new ResponseEntity<>(examServiceImpl.addExam(examDto), HttpStatus.OK);

	}

// Exam 수정
	@PutMapping("api/exam/update/{examId}")
	@PreAuthorize("hasAnyRole('ADMIN','PROFESSOR')")
	ResponseEntity<ResponseDto<Exam>> updateExam(@PathVariable(name = "examId") Long examId,
			@RequestBody @Valid ExamDto updateExam) {

		return new ResponseEntity<>(examServiceImpl.updateExam(examId, updateExam), HttpStatus.OK);

	}

// ExamID로 삭제
	@DeleteMapping("api/exam/delete/{examId}")
	@PreAuthorize("hasAnyRole('ADMIN,'PROFESSOR')")
	ResponseEntity<ResponseDto<String>> deleteExamByExamId(@PathVariable(name = "examId") Long examId) {

		return new ResponseEntity<>(examServiceImpl.deleteExamByExamId(examId), HttpStatus.OK);

	}

// 같은 Lecture의 Exam 삭제
	@PostMapping("api/exam/delete/{lectureName}")
	@PreAuthorize("hasAnyRole('ADMIN','PROFESSOR')")
	ResponseEntity<ResponseDto<String>> deleteExamByLectureName(
			@PathVariable(name = "lectureName") String lectureName) {

		return new ResponseEntity<>(examServiceImpl.deleteExamByLectureName(lectureName), HttpStatus.OK);

	}

}
