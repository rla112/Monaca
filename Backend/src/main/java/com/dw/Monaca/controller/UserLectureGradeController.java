package com.dw.Monaca.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dw.Monaca.dto.ResponseDto;
import com.dw.Monaca.model.UserLectureGrade;
import com.dw.Monaca.service.impl.UserLectureGradeServiceImpl;

@RestController
@CrossOrigin(origins = "http://localhost:3000", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
		RequestMethod.PUT })
public class UserLectureGradeController {

	private final UserLectureGradeServiceImpl userLectureGradeServiceImpl;

	@Autowired
	public UserLectureGradeController(UserLectureGradeServiceImpl userLectureGradeServiceImpl) {
		super();
		this.userLectureGradeServiceImpl = userLectureGradeServiceImpl;
	}

	// 모든 UserLectureGrade를 User별로 불러오기
	@GetMapping("api/userLectreGrade/all")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<ResponseDto<Map<String, List<UserLectureGrade>>>> getAllUserLectureGrade() {
		return new ResponseEntity<>(userLectureGradeServiceImpl.getAllUserLectureGrade(), HttpStatus.OK);
	}

	// 특정 Lecture의 모든 UserLectureGrade를 user별로 불러오기
	@GetMapping("api/userLectureGrade/all/{lectureName}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<ResponseDto<Map<String, List<UserLectureGrade>>>> getUserLectureGradeByLecture(
			@PathVariable(name = "lectureName") String lectureName) {
		return new ResponseEntity<>(userLectureGradeServiceImpl.getUserLectureGradeByLecture(lectureName),
				HttpStatus.OK);
	}

	// 현재 로그인한 사용자의 특정 Lecture UserLectureGrade 불러오기
	@GetMapping("api/userLectureGrade/{lectrueName}")
	@PreAuthorize("hasAnyRole('ADMIN','PROFESSOR','USER')")
	public ResponseEntity<ResponseDto<UserLectureGrade>> getUserLectureGradeByLectureForUser(
			@PathVariable(name = "lectureName") String lectureName) {
		return new ResponseEntity<>(userLectureGradeServiceImpl.getUserLectureGradeByLectureForUser(lectureName),
				HttpStatus.OK);
	}

	// 현재 로그인한 사용자의 UserLectureGrade 모두 불러오기
	@GetMapping("api/userLectureGrade")
	@PreAuthorize("hasAnyRole('ADMIN','PROFESSOR','USER')")
	public ResponseEntity<ResponseDto<List<UserLectureGrade>>> getUserLectureGradeForUser() {
		return new ResponseEntity<>(userLectureGradeServiceImpl.getUserLectureGradeForUser(), HttpStatus.OK);
	}

}
