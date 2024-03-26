package com.dw.Monaca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dw.Monaca.dto.ExaminationDto;
import com.dw.Monaca.dto.ResponseDto;
import com.dw.Monaca.model.Examination;
import com.dw.Monaca.service.impl.ExaminationServiceImpl;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:3000", methods = { RequestMethod.GET, RequestMethod.POST })
public class ExaminationController {
	private final ExaminationServiceImpl examinationServiceImpl;

	@Autowired
	public ExaminationController(ExaminationServiceImpl examinationServiceImpl) {
		this.examinationServiceImpl = examinationServiceImpl;
	}

	// 전체 Examination 불러오기
	@GetMapping("/api/examination")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<ResponseDto<List<Examination>>> getAllExamination() {
		return new ResponseEntity<>(examinationServiceImpl.getAllExamination(), HttpStatus.OK);
	}

	// Lecture별 Examination 불러오기
	@GetMapping("/api/examination/{lectureName}")
	@PreAuthorize("hasAnyRole('ADMIN','PROFESSOR')")
	ResponseEntity<ResponseDto<List<Examination>>> getExaminationByLecture(
			@PathVariable(name = "lectureName") String lectureName) {
		return new ResponseEntity<>(examinationServiceImpl.getExaminationByLecture(lectureName), HttpStatus.OK);
	}

	// User별 Examination 불러오기
	@GetMapping("/api/examination/{loginId}")
	@PreAuthorize("hasAnyRole('ADMIN','PROFESSOR','USER')")
	ResponseEntity<ResponseDto<List<Examination>>> getExaminationByUserLoginId(
			@PathVariable(name = "loginId") String loginId) {
		return new ResponseEntity<>(examinationServiceImpl.getExaminationByUserLoginId(loginId), HttpStatus.OK);
	}

	// 현재 로그인한 유저의 Examination 불러오기
	@GetMapping("/api/examination/my")
	@PreAuthorize("hasAnyRole('ADMIN','PROFESSOR','USER')")
	ResponseEntity<ResponseDto<List<Examination>>> getExaminationByUser() {
		return new ResponseEntity<>(examinationServiceImpl.getExaminationByUser(), HttpStatus.OK);
	}

	// 특정 User의 특정한 Lecture의 Examination 불러오기
	@GetMapping("/api/examination/{loginId}/{lectureName}")
	@PreAuthorize("hasAnyRole('ADMIN','PROFESSOR')")
	ResponseEntity<ResponseDto<List<Examination>>> getExaminationByLectureForUserLoginId(
			@PathVariable(name = "loginId") String loginId, @PathVariable(name = "lectureName") String lectureName) {
		return new ResponseEntity<>(examinationServiceImpl.getExaminationByLectureForUserLoginId(loginId, lectureName),
				HttpStatus.OK);
	}

	// 현재 로그인한 유저의 특정한 Lecture의 Examination 불러오기
	@GetMapping("/api/examination/my/{lectureName}")
	@PreAuthorize("hasAnyRole('ADMIN','PROFESSOR','USER')")
	ResponseEntity<ResponseDto<List<Examination>>> getExaminationByLectureForUser(
			@PathVariable(name = "lectureName") String lectureName) {
		return new ResponseEntity<>(examinationServiceImpl.getExaminationByLectureForUser(lectureName), HttpStatus.OK);
	}

	// examination 생성(학생이 답안지 입력) // 생성되는 순간 채점 결과 저장도 같이 이루어짐.
	@PostMapping("/api/examination/{ExamId}")
	@PreAuthorize("hasAnyRole('USER')")
	public ResponseEntity<ResponseDto<Examination>> createExamination(@PathVariable(name = "ExamId") Long ExamId,
			@RequestBody @Valid ExaminationDto examinationDto) {
		return new ResponseEntity<>(examinationServiceImpl.createExamination(ExamId, examinationDto),
				HttpStatus.CREATED);
	}
}
